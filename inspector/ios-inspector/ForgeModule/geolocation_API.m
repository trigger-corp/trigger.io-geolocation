//
//  geolocation_API.m
//  ForgeInspector
//
//  Created by Connor Dunn on 27/07/2012.
//  Copyright (c) 2012 Trigger Corp. All rights reserved.
//

#import "geolocation_API.h"
#import "geolocation_Delegate.h"

@implementation geolocation_API


+ (void)getCurrentPosition:(ForgeTask*)task enableHighAccuracy:(NSNumber*)highAccuracy {
	// Check for permission and location availablity
	if (![CLLocationManager locationServicesEnabled]) {
		[task error:@"Location services disabled" type:@"UNAVAILABLE" subtype:nil];
		return;
	}
	if ([CLLocationManager authorizationStatus] == kCLAuthorizationStatusDenied) {
		[task error:@"Permission denied" type:@"UNAVAILABLE" subtype:nil];
		return;
	}
	
	// Setup location manager
	CLLocationManager *locMgr = [[CLLocationManager alloc] init];
    if ([locMgr respondsToSelector:@selector(requestWhenInUseAuthorization)]) { /* iOS 8 */
        [locMgr requestWhenInUseAuthorization];
    }
    
    // Set accuracy
	if ([highAccuracy isEqual:[NSNumber numberWithBool:YES]]) {
		locMgr.desiredAccuracy = kCLLocationAccuracyBest;
	} else {
		locMgr.desiredAccuracy = kCLLocationAccuracyThreeKilometers;
	}
	
	// Setup delegate
	geolocation_Delegate *delegate = [[geolocation_Delegate alloc] initWithTask:task andMgr:locMgr];
	locMgr.delegate = delegate;
	
	// Ask for location update
	[locMgr startUpdatingLocation];
}


@end
