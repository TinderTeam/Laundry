//
//  FECompany.h
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FECompany : SSObject

@property (nonatomic, strong, readonly) NSNumber *company_id;
@property (nonatomic, strong, readonly) NSString *company_name;
@property (nonatomic, strong, readonly) NSString *company_web_site;
@property (nonatomic, strong, readonly) NSString *company_addr;
@property (nonatomic, strong, readonly) NSString *company_desp;
@property (nonatomic, strong, readonly) NSString *service_phone;
@property (nonatomic, strong, readonly) NSNumber *ios_version;
@property (nonatomic, strong, readonly) NSNumber *andriod_version;

@end
