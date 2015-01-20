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

@property (nonatomic, strong) NSNumber *quantity;
@property (nonatomic, strong, readonly) NSString *product_name;
@property (nonatomic, strong, readonly) NSString *product_type;
@property (nonatomic, strong, readonly) NSString *product_describe;
@property (nonatomic, strong, readonly) NSString *current_price;
@property (nonatomic, strong, readonly) NSString *original_price;
@property (nonatomic, strong, readonly) NSString *product_img;
@property (nonatomic, strong, readonly) NSString *product_status;
@property (nonatomic, strong, readonly) NSString *product_update_time;
@property (nonatomic, strong, readonly) NSString *product_limit_time;

@end
