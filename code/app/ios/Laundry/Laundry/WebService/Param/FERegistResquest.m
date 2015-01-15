//
//  FERegistResquest.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERegistResquest.h"

@implementation FERegistResquest

-(id)initWithUserName:(NSString *)uname password:(NSString *)pword addr:(NSString *)addr{
    self = [super initWithMethod:__METHOD_Register];
    if (self) {
        _user_name = uname;
        _password = pword;
        _addr = addr;
    }
    return self;
}
@end
