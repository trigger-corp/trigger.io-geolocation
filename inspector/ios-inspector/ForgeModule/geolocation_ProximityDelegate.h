//
//  geolocation_ProximityDelegate.h
//  Forge
//
//  Created by Connor Dunn on 19/07/2012.
//  Copyright (c) 2012 Trigger Corp. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>

@interface geolocation_ProximityDelegate : NSObject<CLLocationManagerDelegate> {
	ForgeTask *task;
	geolocation_ProximityDelegate *me;
	CLLocationManager *locMgr;
    BOOL returned;
}

- (geolocation_ProximityDelegate*) initWithTask:(ForgeTask*)initTask andMgr:(CLLocationManager *)mgr;

@end
