//
//  FEOrderDetailResponse.m
//  Laundry
//
//  Created by Seven on 15-1-19.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEOrderDetailResponse.h"

@implementation FEOrderDetailResponse

-(id)initWithResponse:(id)response{
    self = [super initWithResponse:response];
    if (self) {
        _obj = [self getListFromObject:response[@"obj"] class:[FEOrderDetail class]];
    }
    return self;
}

@end
