//
//  FEOrderListResponse.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEOrderListResponse.h"

@implementation FEOrderListResponse

-(id)initWithResponse:(id)response{
    self = [super initWithResponse:response];
    if (self) {
        _obj = [self getListFromObject:response[@"obj"] class:[FEOrder class]];
    }
    return self;
}

@end
