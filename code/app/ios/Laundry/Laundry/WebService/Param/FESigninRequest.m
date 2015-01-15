//
//  FESigninRequest.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FESigninRequest.h"


@implementation FESigninRequest
-(id)initWithUser:(FEUser *)user{
    self = [super initWithMethod:__METHOD_Login];
    if (self) {
        _obj = user;
    }
    return self;
}
@end
