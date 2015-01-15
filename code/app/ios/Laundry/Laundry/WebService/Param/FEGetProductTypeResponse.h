//
//  FEGetProductTypeResponse.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FECategory.h"

@interface FEGetProductTypeResponse : FEBaseResponse
@property (nonatomic, strong, readonly) NSArray *typeList;
@end
