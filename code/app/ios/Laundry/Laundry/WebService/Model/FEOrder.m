//
//  FEOrder.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEOrder.h"

@implementation FEOrder

-(id)init{
    self = [super init];
    if (self) {
        _order_name = @"洗衣";
    }
    return self;
}

-(id)initWithDictionary:(id)dictionary{
    self = [super initWithDictionary:dictionary];
    if (self) {
        _order_name = @"洗衣";
    }
    return self;
}

@end
