//
//  FEGetCurrentCity.m
//  EShoping
//
//  Created by Seven on 14-12-24.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEGetCurrentCity.h"
#import <CoreLocation/CoreLocation.h>

#define SYSTEM_VERSION_EQUAL_TO(v)                  ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] == NSOrderedSame)
#define SYSTEM_VERSION_GREATER_THAN(v)              ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] == NSOrderedDescending)
#define SYSTEM_VERSION_GREATER_THAN_OR_EQUAL_TO(v)  ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] != NSOrderedAscending)
#define SYSTEM_VERSION_LESS_THAN(v)                 ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] == NSOrderedAscending)
#define SYSTEM_VERSION_LESS_THAN_OR_EQUAL_TO(v)     ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] != NSOrderedDescending)

@interface FEGetCurrentCity ()<CLLocationManagerDelegate>
@property (nonatomic, strong) CLGeocoder *geocoder;

@end

@implementation FEGetCurrentCity

-(id)init{
    self = [super init];
    if (self) {
        _manager = [[CLLocationManager alloc] init];
        _manager.delegate = self;
        _geocoder = [[CLGeocoder alloc] init];
    }
    return self;
}

-(void)getCity:(getCityComplete)complete{
    self.complete = complete;
    if (SYSTEM_VERSION_LESS_THAN(@"8.0")) {
        [_manager startUpdatingLocation];
    }else{
        if ([CLLocationManager authorizationStatus] == kCLAuthorizationStatusNotDetermined) {
            [_manager requestWhenInUseAuthorization];
        }else{
            [_manager startUpdatingLocation];
        }
    }
}

#pragma mark - CLLocationManagerDelegate
-(void)locationManager:(CLLocationManager *)manager didUpdateLocations:(NSArray *)locations{
    [manager stopUpdatingLocation];
    CLLocation *location = [locations firstObject];
    [self.geocoder reverseGeocodeLocation:location completionHandler:^(NSArray *placemarks, NSError *error) {
        if (!error) {
            CLPlacemark *mark = [placemarks firstObject];
//            NSString *locality = mark.locality;
            self.complete(nil,mark.name);
        }else{
            self.complete(error,nil);
        }
    }];
}

-(void)locationManager:(CLLocationManager *)manager didChangeAuthorizationStatus:(CLAuthorizationStatus)status{
    if (status == kCLAuthorizationStatusNotDetermined) {
        if (!SYSTEM_VERSION_LESS_THAN(@"8.0")) {
            [manager requestWhenInUseAuthorization];
        }
    }else if (status == kCLAuthorizationStatusAuthorizedWhenInUse) {
        [manager startUpdatingLocation];
    }
}

-(void)locationManager:(CLLocationManager *)manager didFailWithError:(NSError *)error{
    self.complete(error,nil);
}

-(void)cacel{
    
    self.complete = nil;
    [_manager stopUpdatingLocation];
    [_geocoder cancelGeocode];
}

@end
