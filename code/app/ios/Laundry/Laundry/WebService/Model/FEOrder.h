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
@property (nonatomic, strong) NSString *order_name;
@property (nonatomic, strong, readonly) NSNumber *company_id;
@property (nonatomic, strong) NSNumber *user_id;
@property (nonatomic, strong) NSString *user_name;
@property (nonatomic, strong, readonly) NSString *create_time;
@property (nonatomic, strong, readonly) NSString *confirm_time;
@property (nonatomic, strong, readonly) NSString *end_time;
@property (nonatomic, strong) NSString *pay_option; //必填
@property (nonatomic, strong) NSString *order_status;
@property (nonatomic, strong, readonly) NSNumber *handler_id;
@property (nonatomic, strong, readonly) NSString *operater_name;
@property (nonatomic, strong) NSString *order_type; //必填
@property (nonatomic, strong) NSString *order_note;

@property (nonatomic, strong) NSString *price_type;
@property (nonatomic, strong) NSString *total_price; //必填
@property (nonatomic, strong) NSNumber *total_count; //必填

@property (nonatomic, strong) NSString *delivery_addr;
@property (nonatomic, strong) NSString *take_addr;
@property (nonatomic, strong) NSString *delivery_time;
@property (nonatomic, strong) NSString *take_time;
@property (nonatomic, strong) NSString *contact_name;
@property (nonatomic, strong) NSString *phone;


@end
