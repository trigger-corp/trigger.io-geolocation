//
//  geolocation_Delegate.h
//  Forge
//
//  Created by Connor Dunn on 14/03/2012.
//  Copyright (c) 2012 Trigger Corp. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>

@interface geolocation_Delegate : NSObject <CLLocationManagerDelegate> {
	ForgeTask *task;
	geolocation_Delegate *me;
	CLLocationManager *locMgr;
    BOOL returned;
}

- (geolocation_Delegate*) initWithTask:(ForgeTask*)initTask andMgr:(CLLocationManager *)mgr;


@end
