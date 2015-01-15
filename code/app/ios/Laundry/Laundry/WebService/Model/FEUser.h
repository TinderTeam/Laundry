//
//  FEUser.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FEUser : SSObject

@property (nonatomic, strong, readonly) NSNumber *user_id;
@property (nonatomic, strong) NSString *password;
@property (nonatomic, strong) NSString *user_name;
@property (nonatomic, strong, readonly) NSNumber *role_id;
@property (nonatomic, strong, readonly) NSNumber *company_id;
@property (nonatomic, strong, readonly) NSString *reg_date;

@end
