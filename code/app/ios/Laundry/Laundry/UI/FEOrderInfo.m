//
//  FEOrderInfo.m
//  Laundry
//
//  Created by Seven on 15-1-19.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEOrderInfo.h"

@implementation FEOrderInfo
-(id)init{
    self = [super init];
    if (self) {
        _delivery_addr = @"";
        _take_addr = @"";
        _delivery_time = @"";
        _take_time = @"";
        _contact_name = @"";
        _phone = @"";
        _payType = @"在线支付";
        _remark = @"";
    }
    return self;
}

@end
