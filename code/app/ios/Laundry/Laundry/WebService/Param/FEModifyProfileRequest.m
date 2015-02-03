//
//  FEModifyProfileRequest.m
//  Laundry
//
//  Created by Seven on 15-2-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEModifyProfileRequest.h"

@implementation FEModifyProfileRequest
-(id)initWithCustomer:(FECustomer *)customer{
    self = [super initWithMethod:__METHOD_ModifyProfile];
    if (self) {
        _obj = customer;
    }
    return self;
}
@end
