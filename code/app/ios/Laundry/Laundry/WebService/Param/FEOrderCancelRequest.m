//
//  FEOrderCancelRequest.m
//  Laundry
//
//  Created by Seven on 15-2-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEOrderCancelRequest.h"

@implementation FEOrderCancelRequest
-(id)initWithOrderID:(NSNumber *)oid{
    self = [super initWithMethod:__METHOD_OrederCancel];
    if (self) {
        _obj = oid;
    }
    return self;
}
@end
