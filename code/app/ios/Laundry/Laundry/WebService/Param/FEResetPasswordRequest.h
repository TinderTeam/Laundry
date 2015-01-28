//
//  FEResetPasswordRequest.h
//  Laundry
//
//  Created by Seven on 15-1-28.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseRequest.h"

@interface FEResetPasswordRequest : FEBaseRequest
//private String user_name;
//private String password;
//private String verifyCode;
//private String addr = "未设置";
@property (nonatomic, strong, readonly) NSString *user_name;
@property (nonatomic, strong, readonly) NSString *password;
@property (nonatomic, strong, readonly) NSString *addr;

-(id)initWithUserName:(NSString *)uname password:(NSString *)pword;
@end
