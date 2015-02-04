//
//  FEClientVersionResponse.m
//  Laundry
//
//  Created by Seven on 15-2-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEClientVersionResponse.h"

@implementation FEClientVersionResponse

-(id)initWithResponse:(id)response{
    self = [super initWithResponse:response];
    if (self) {
        _obj = [[FEClientVersion alloc] initWithDictionary:response[@"obj"]];
    }
    return self;
}

@end
