//
//  FEOrder.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FEOrder : SSObject

@property (nonatomic, strong, readonly) NSNumber *order_id;
@property (nonatomic, strong, readonly) NSString *order_code;
@property (nonatomic, strong, readonly) NSString *order_name;
@property (nonatomic, strong, readonly) NSNumber *company_id;
@property (nonatomic, strong, readonly) NSNumber *user_id;
@property (nonatomic, strong, readonly) NSString *user_name;
@property (nonatomic, strong, readonly) NSString *create_time;
@property (nonatomic, strong, readonly) NSString *confirm_time;
@property (nonatomic, strong, readonly) NSString *end_time;
@property (nonatomic, strong, readonly) NSString *pay_option;
@property (nonatomic, strong, readonly) NSString *order_status;
@property (nonatomic, strong, readonly) NSNumber *handler_id;
@property (nonatomic, strong, readonly) NSString *operater_name;
@property (nonatomic, strong, readonly) NSString *order_type;
@property (nonatomic, strong, readonly) NSString *order_note;
@property (nonatomic, strong, readonly) NSNumber *total_price;
@property (nonatomic, strong, readonly) NSNumber *total_count;


@end
