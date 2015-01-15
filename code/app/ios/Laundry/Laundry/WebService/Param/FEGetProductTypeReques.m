//
//  FEGetProductTypeReques.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEGetProductTypeReques.h"

@implementation FEGetProductTypeReques

-(id)initWithTypeRoot:(NSNumber *)type{
    self = [super initWithMethod:__METHOD_ProductType];
    if (self) {
        _typeRoot = type;
    }
    return self;
}

@end
