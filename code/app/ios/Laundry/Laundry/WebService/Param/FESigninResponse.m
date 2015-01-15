//
//  FESigninResponse.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FESigninResponse.h"

@implementation FESigninResponse

-(id)initWithResponse:(id)response{
    self = [super initWithResponse:response];
    if (self) {
        _user = [[FEUser alloc] initWithDictionary:response[@"user"]];
        id token = response[@"token"];
        if (token && ![token isKindOfClass:[NSNull class]]) {
            _token = token;
        }
    }
    return self;
}

@end
