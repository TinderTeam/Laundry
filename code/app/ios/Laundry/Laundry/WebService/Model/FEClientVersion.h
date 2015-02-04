//
//  FEClientVersion.h
//  Laundry
//
//  Created by Seven on 15-2-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FEClientVersion : SSObject
@property (nonatomic, strong, readonly) NSNumber *version_id;
@property (nonatomic, strong, readonly) NSNumber *company_id;
@property (nonatomic, strong, readonly) NSString *app_name;
@property (nonatomic, strong, readonly) NSString *apk_name;
@property (nonatomic, strong, readonly) NSString *version_name;
@property (nonatomic, strong, readonly) NSNumber *version_code;
@property (nonatomic, strong, readonly) NSString *client_type;
@property (nonatomic, strong, readonly) NSString *apk_url;
@property (nonatomic, strong, readonly) NSString *qr_code;
@property (nonatomic, strong, readonly) NSString *version_status;
@end
