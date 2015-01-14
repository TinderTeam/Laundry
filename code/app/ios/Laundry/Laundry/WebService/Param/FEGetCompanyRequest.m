//
//  FEGetCompanyRequest.m
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEGetCompanyRequest.h"

@implementation FEGetCompanyRequest

-(id)initWithCid:(NSNumber *)cid{
    self = [super initWithMethod:__METHOD_GetCompany];
    if (self) {
        _obj = cid;
    }
    return self;
}

@end
