//
//  FEGetCustomerRequest.m
//  Laundry
//
//  Created by Seven on 15-2-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEGetCustomerRequest.h"

@implementation FEGetCustomerRequest

-(id)initWithCid:(NSNumber *)obj{
    self = [super initWithMethod:__METHOD_GetCustomer];
    if (self) {
        _obj = obj;
    }
    return self;
}

@end
