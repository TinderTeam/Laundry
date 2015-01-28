//
//  FEResetPasswordRequest.m
//  Laundry
//
//  Created by Seven on 15-1-28.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEResetPasswordRequest.h"

@implementation FEResetPasswordRequest
-(id)initWithUserName:(NSString *)uname password:(NSString *)pword{
    self = [super initWithMethod:__METHOD_ResetPsw];
    if (self) {
        _user_name = uname;
        _password = pword;
    }
    return self;
}
@end
