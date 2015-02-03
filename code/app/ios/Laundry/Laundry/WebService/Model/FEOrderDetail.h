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
@property (nonatomic, strong) NSString *product_name;
@property (nonatomic, strong) NSString *product_type;
@property (nonatomic, strong) NSString *product_describe;

@property (nonatomic, strong) NSString *current_price;
@property (nonatomic, strong) NSString *original_price;
@property (nonatomic, strong) NSString *price_type;
@property (nonatomic, strong) NSString *product_img;
@property (nonatomic, strong) NSString *product_status;
@property (nonatomic, strong) NSString *product_update_time;
@property (nonatomic, strong) NSString *product_limit_time;

@property (nonatomic, strong) NSString *detail_info;


@end
