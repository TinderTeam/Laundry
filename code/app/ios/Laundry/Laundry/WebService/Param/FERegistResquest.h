//
//  FERegistResquest.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseRequest.h"

@interface FERegistResquest : FEBaseRequest
@property (nonatomic, strong, readonly) NSString *user_name;
@property (nonatomic, strong, readonly) NSString *password;
@property (nonatomic, strong, readonly) NSString *addr;

-(id)initWithUserName:(NSString *)uname password:(NSString *)pword addr:(NSString *)addr;

@end
