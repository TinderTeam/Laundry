//
//  FECreateOrderResponse.m
//  Laundry
//
//  Created by Seven on 15-2-1.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECreateOrderResponse.h"

@implementation FECreateOrderResponse

-(id)initWithResponse:(id)response{
    self = [super initWithResponse:response];
    if (self) {
        _obj = [[FEOrder alloc] initWithDictionary:response[@"obj"]];
    }
    return self;
}

@end
