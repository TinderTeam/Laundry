//
//  FEOrderInfo.h
//  Laundry
//
//  Created by Seven on 15-1-19.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FEOrderInfo :SSObject
@property (nonatomic, strong) NSString *delivery_addr;
@property (nonatomic, strong) NSString *take_addr;
@property (nonatomic, strong) NSString *delivery_time;
@property (nonatomic, strong) NSString *take_time;
@property (nonatomic, strong) NSString *contact_name;
@property (nonatomic, strong) NSString *phone;
@property (nonatomic, strong) NSString *pay_option;
@property (nonatomic, strong) NSString *order_note;
@end
