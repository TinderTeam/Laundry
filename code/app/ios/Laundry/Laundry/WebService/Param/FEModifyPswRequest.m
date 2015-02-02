//
//  FEModifyPswRequest.m
//  Laundry
//
//  Created by Seven on 15-2-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEModifyPswRequest.h"

@implementation FEModifyPswRequest
-(id)initWithUname:(NSString *)uname oldPsw:(NSString *)opsw newPsw:(NSString *)npsw{
    self = [super initWithMethod:__METHOD_ModifyPassword];
    if (self) {
        _user_name = uname;
        _pwdNew = npsw;
        _pwdOld = opsw;
    }
    return self;
}
@end
