//
//  FEAD.h
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FEAD : SSObject
@property (nonatomic, strong, readonly) NSNumber *company_id;
@property (nonatomic, strong, readonly) NSNumber *ad_id;
@property (nonatomic, strong, readonly) NSString *ad_name;
@property (nonatomic, strong, readonly) NSString *ad_info;
@property (nonatomic, strong, readonly) NSString *ad_url;
@property (nonatomic, strong, readonly) NSString *ad_img;

@end
