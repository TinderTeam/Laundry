//
//  FEBaseRequest.m
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseRequest.h"

@implementation FEBaseRequest

-(id)initWithMethod:(NSString *)method{
    self = [super init];
    if (self) {
        _token = @(0);
        _app_id = @(1);
        _clientType = @"IOS";
        _clientVersion = kAppVersion;
        _method = method;
    }
    return self;
}

@end
