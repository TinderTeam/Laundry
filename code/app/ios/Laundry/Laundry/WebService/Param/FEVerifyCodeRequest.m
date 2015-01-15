//
//  FEVerifyCodeRequest.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEVerifyCodeRequest.h"

@implementation FEVerifyCodeRequest

-(id)initWithPhoneNumber:(NSString *)pnumber{
    self = [super initWithMethod:__METHOD_SendVerifyCode];
    if (self) {
        _phone_num = pnumber;
    }
    return self;
}

@end
