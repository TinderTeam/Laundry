//
//  FEOrderRequest.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEOrderRequest.h"

@implementation FEOrderRequest
-(id)initWithUserID:(NSNumber *)uid{
    self = [super initWithMethod:__METHOD_OrderList];
    if (self) {
        _user_id = uid;
    }
    return self;
}
@end
