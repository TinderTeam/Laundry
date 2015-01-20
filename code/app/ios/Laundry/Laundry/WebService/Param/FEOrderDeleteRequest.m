//
//  FEOrderDeleteRequest.m
//  Laundry
//
//  Created by Seven on 15-1-20.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEOrderDeleteRequest.h"

@implementation FEOrderDeleteRequest
-(id)initWithOrderID:(NSNumber *)oid{
    self = [super initWithMethod:__METHOD_OrderDelete];
    if (self) {
        _obj = oid;
    }
    return self;
}
@end
