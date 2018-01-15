//
//  geolocation_Delegate.m
//  Forge
//
//  Created by Connor Dunn on 14/03/2012.
//  Copyright (c) 2012 Trigger Corp. All rights reserved.
//

#import "geolocation_Delegate.h"

@implementation geolocation_Delegate 

- (geolocation_Delegate*) initWithTask:(ForgeTask *)initTask andMgr:(CLLocationManager *)mgr {
    if (self = [super init]) {
        task = initTask;
        locMgr = mgr;
        returned = NO;
        // "retain"
        me = self;
    }
    return self;
}

- (void) locationManager:(CLLocationManager *)manager didUpdateToLocation:(CLLocation *)newLocation fromLocation:(CLLocation *)oldLocation {
    if (returned) {
        return;
    }
    returned = YES;

    [locMgr stopUpdatingLocation];
    
    NSDictionary *coords = [NSDictionary dictionaryWithObjectsAndKeys:
                            [NSNumber numberWithDouble:[newLocation coordinate].latitude],  @"latitude",
                            [NSNumber numberWithDouble:[newLocation coordinate].longitude], @"longitude",
                            [NSNull null],                                                  @"altitude",
                            [NSNumber numberWithDouble:newLocation.horizontalAccuracy],     @"accuracy",
                            [NSNull null],                                                  @"altitudeAccuracy",
                            [NSNull null],                                                  @"heading",
                            [NSNull null],                                                  @"speed",
                            nil];

    NSDictionary *returnObj = [NSDictionary dictionaryWithObjectsAndKeys:
                               [[newLocation timestamp] description], @"timestamp",
                               coords,                                @"coords",
                               nil];


    [task success:returnObj];

    // No other way to avoid both leaks and bad access?
    [self performSelector:@selector(arcRelease:) withObject:nil afterDelay:10];
}

- (void) arcRelease:(id) nothing {
    me = nil;
}

- (void) locationManager:(CLLocationManager *)manager didFailWithError:(NSError *)error {
    if (returned) {
        return;
    }
    returned = YES;

    [task error:error];

    [locMgr stopUpdatingLocation];
    locMgr.delegate = nil;
    // "release"
    me = nil;
}

@end
