//
//  geolocation_ProximityDelegate.m
//  Forge
//
//  Created by Connor Dunn on 19/07/2012.
//  Copyright (c) 2012 Trigger Corp. All rights reserved.
//

#import "geolocation_ProximityDelegate.h"

@implementation geolocation_ProximityDelegate

- (geolocation_ProximityDelegate*) initWithTask:(ForgeTask*)initTask andMgr:(CLLocationManager *)mgr {
	if (self = [super init]) {
		task = initTask;
		locMgr = mgr;
		// "retain"
		me = self;
	}	
	return self;
}

- (void) locationManager:(CLLocationManager *)manager didEnterRegion:(CLRegion *)region {
	NSLog(@"in");
}

- (void) locationManager:(CLLocationManager *)manager didExitRegion:(CLRegion *)region {
	NSLog(@"out");
}

- (void) locationManager:(CLLocationManager *)manager monitoringDidFailForRegion:(CLRegion *)region withError:(NSError *)error {
	NSLog(@"Fail");
}

- (void) locationManager:(CLLocationManager *)manager didStartMonitoringForRegion:(CLRegion *)region {
	NSLog(@"Monitoring");
}

- (void) locationManager:(CLLocationManager *)manager didFailWithError:(NSError *)error {
	NSLog(@"Fail2");
}

@end
