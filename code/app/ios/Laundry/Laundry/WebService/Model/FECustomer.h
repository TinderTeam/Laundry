//
//  FECustomer.h
//  Laundry
//
//  Created by Seven on 15-2-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FECustomer : SSObject

@property (nonatomic, strong) NSNumber *user_id;
@property (nonatomic, strong) NSNumber *company_id;
@property (nonatomic, strong) NSString *user_name;
@property (nonatomic, strong) NSString *customer_name;
@property (nonatomic, strong) NSString *customer_email;
@property (nonatomic, strong) NSString *card_number;
@property (nonatomic, strong) NSString *customer_sex;
@property (nonatomic, strong) NSString *customer_img;
@property (nonatomic, strong) NSString *phone;
@property (nonatomic, strong) NSString *addr;
@property (nonatomic, strong) NSString *birthday;
@property (nonatomic, strong) NSNumber *score;
@property (nonatomic, strong) NSString *level;
@property (nonatomic, strong) NSString *nickname;
@property (nonatomic, strong) NSString *status;
@property (nonatomic, strong) NSNumber *role_id;
@property (nonatomic, strong) NSString *role_name;

@end
