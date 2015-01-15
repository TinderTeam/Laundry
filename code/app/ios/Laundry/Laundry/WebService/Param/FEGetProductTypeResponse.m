//
//  FEGetProductTypeResponse.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEGetProductTypeResponse.h"

@implementation FEGetProductTypeResponse

-(id)initWithResponse:(id)response{
    self = [super initWithResponse:response];
    if (self) {
        _typeList = [self getListFromObject:response[@"typeList"] class:[FECategory class]];
    }
    return self;
}


@end
