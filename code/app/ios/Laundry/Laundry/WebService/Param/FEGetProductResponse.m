//
//  FEGetProductResponse.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEGetProductResponse.h"

@implementation FEGetProductResponse

-(id)initWithResponse:(id)response{
    self = [super initWithResponse:response];
    if (self) {
        _obj = [self getListFromObject:response[@"obj"] class:[FEProduct class]];
    }
    return self;
}

@end
