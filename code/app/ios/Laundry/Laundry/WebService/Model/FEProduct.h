//
//  FEProduct.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FEProduct : SSObject

@property (nonatomic, strong, readonly) NSNumber *product_id;
@property (nonatomic, strong, readonly) NSNumber *company_id;
@property (nonatomic, strong, readonly) NSString *product_name;
@property (nonatomic, strong, readonly) NSNumber *type_id;
@property (nonatomic, strong, readonly) NSNumber *seller_id;
@property (nonatomic, strong, readonly) NSString *describe;
@property (nonatomic, strong, readonly) NSString *price_type;
@property (nonatomic, strong, readonly) NSString *price;
@property (nonatomic, strong, readonly) NSString *original_price;
@property (nonatomic, strong, readonly) NSString *img;
@property (nonatomic, strong, readonly) NSString *product_status;
@property (nonatomic, strong, readonly) NSString *update_time;
@property (nonatomic, strong, readonly) NSString *end_time;
@property (nonatomic, strong, readonly) NSString *detail_info;
@property (nonatomic, strong, readonly) NSString *type_name;
@property (nonatomic, strong, readonly) NSNumber *parent_type_id;


@end
