//
//  FEVerifyCodeResponse.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEVerifyCodeResponse.h"

@implementation FEVerifyCodeResponse
-(id)initWithResponse:(id)response{
    self = [super initWithResponse:response];
    if (self) {
        NSString *obj = response[@"obj"];
        if (obj && ![obj isKindOfClass:[NSNull class]]) {
            _obj = obj;
        }
    }
    return self;
}
@end
