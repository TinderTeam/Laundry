//
//  FEGetProductResponse.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FEProduct.h"

@interface FEGetProductResponse : FEBaseResponse
@property (nonatomic, strong, readonly) NSArray *obj;

@end
