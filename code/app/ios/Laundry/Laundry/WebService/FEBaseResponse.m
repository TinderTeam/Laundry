//
//  FEBaseResponse.m
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"

@implementation FEBaseResponse

-(id)initWithResponse:(id)response{
    self = [super init];
    if (self) {
        id errorCode = response[@"errorCode"];
        if (errorCode && ![errorCode isKindOfClass:[NSNull class]]) {
            _errorCode = errorCode;
        }
        id errorMsg = response[@"errorMsg"];
        if (errorMsg && ![errorMsg isKindOfClass:[NSNull class]]) {
            _errorMsg = errorMsg;
        }
    }
    return self;
}

-(NSArray *)getListFromObject:(id)obj class:(Class)cls{
    NSArray *objects = obj;
    NSMutableArray *toObjectList = NULL;
    if (objects && ![objects isKindOfClass:[NSNull class]]) {
        toObjectList = [NSMutableArray new];
        for (id item in objects) {
            [toObjectList addObject:[[cls alloc] initWithDictionary:item]];
        }
    }
    return toObjectList;
}

@end
