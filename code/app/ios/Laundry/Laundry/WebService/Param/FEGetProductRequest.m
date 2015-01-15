//
//  FEGetProductRequest.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEGetProductRequest.h"

@implementation FEGetProductRequest

-(id)initWithRid:(NSNumber *)rid keyword:(NSString *)key{
    self = [super initWithMethod:__METHOD_ProductGetList];
    if (self) {
        _typeRoot = rid;
        _keyWord = key;
    }
    return self;
}

@end
