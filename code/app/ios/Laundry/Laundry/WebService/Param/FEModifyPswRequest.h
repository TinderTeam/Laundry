//
//  FEModifyPswRequest.h
//  Laundry
//
//  Created by Seven on 15-2-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseRequest.h"

@interface FEModifyPswRequest : FEBaseRequest
@property (nonatomic, strong, readonly) NSString *user_name;
@property (nonatomic, strong, readonly) NSString *pwdOld;
@property (nonatomic, strong, readonly) NSString *pwdNew;

-(id)initWithUname:(NSString *)uname oldPsw:(NSString *)opsw newPsw:(NSString *)npsw;

@end
