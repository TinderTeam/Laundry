//
//  FEOrderCreateRequest.m
//  Laundry
//
//  Created by Seven on 15-1-16.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEOrderCreateRequest.h"

@implementation FEOrderCreateRequest

-(id)initWithOrder:(FEOrder *)order orderDetails:(NSArray *)odetail{
    self = [super initWithMethod:__METHOD_OrderCreate];
    if (self) {
        _order = order;
        _orderDetailList = odetail;
    }
    return self;
}
@end
