//
//  FEOrderDetail.h
//  Laundry
//
//  Created by Seven on 15-1-16.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FEOrderDetail : SSObject

@property (nonatomic, strong, readonly) NSNumber *order_detail_id;
@property (nonatomic, strong, readonly) NSString *order_id;
@property (nonatomic, strong) NSNumber *product_id;

@end
