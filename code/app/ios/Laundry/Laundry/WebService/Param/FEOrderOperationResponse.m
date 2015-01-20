//
//  FEOrderOperationResponse.m
//  Laundry
//
//  Created by Seven on 15-1-20.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEOrderOperationResponse.h"

@implementation FEOrderOperationResponse
-(id)initWithResponse:(id)response{
    self = [super initWithResponse:response];
    if (self) {
        if (response[@"order_id"] && ![response[@"order_id"] isKindOfClass:[NSNull class]]) {
            _order_id = response[@"order_id"];
        }
    }
    return self;
}

@end
