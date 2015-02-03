//
//  FEGetCustomerResponse.m
//  Laundry
//
//  Created by Seven on 15-2-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEGetCustomerResponse.h"

@implementation FEGetCustomerResponse

-(id)initWithResponse:(id)response{
    self = [super initWithResponse:response];
    if (self) {
        _obj = [[FECustomer alloc] initWithDictionary:response[@"obj"]];
    }
    return self;
}

@end
