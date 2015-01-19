//
//  FEGetCurrentCity.h
//  EShoping
//
//  Created by Seven on 14-12-24.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>
@class CLLocationManager;
typedef void(^getCityComplete)(NSError *error, NSString *city);

@interface FEGetCurrentCity : NSObject
@property (nonatomic, strong) CLLocationManager *manager;
@property (nonatomic, strong) getCityComplete complete;

-(void)getCity:(getCityComplete)complete;
-(void)cacel;

@end
