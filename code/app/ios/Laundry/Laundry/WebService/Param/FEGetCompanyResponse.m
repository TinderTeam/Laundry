//
//  FEGetCompanyResponse.m
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEGetCompanyResponse.h"

@implementation FEGetCompanyResponse

-(id)initWithResponse:(id)response{
    self = [super initWithResponse:response];
    if (self) {
        _obj = [[FECompany alloc] initWithDictionary:response[@"obj"]];
    }
    return self;
}

@end
