//
//  FEOrderDetailRequest.m
//  Laundry
//
//  Created by Seven on 15-1-19.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEOrderDetailRequest.h"

@implementation FEOrderDetailRequest

-(id)initWithOrderID:(NSNumber *)oid{
    self = [super initWithMethod:__METHOD_OrderDetail];
    if (self) {
        _obj = oid;
    }
    return self;
}

@end
