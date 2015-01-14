//
//  FEGetADResponse.m
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEGetADResponse.h"

@implementation FEGetADResponse

-(id)initWithResponse:(id)response{
    self = [super initWithResponse:response];
    if (self) {
        _obj = [self getListFromObject:response[@"obj"] class:[FEAD class]];
    }
    return self;
}

@end
