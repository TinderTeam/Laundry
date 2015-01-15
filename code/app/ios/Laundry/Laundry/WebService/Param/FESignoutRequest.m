//
//  FESignoutRequest.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FESignoutRequest.h"

@implementation FESignoutRequest
-(id)initWithUser:(FEUser *)user{
    self = [super initWithMethod:__METHOD_Logout];
    if (self) {
        _obj = user;
    }
    return self;
}
@end
