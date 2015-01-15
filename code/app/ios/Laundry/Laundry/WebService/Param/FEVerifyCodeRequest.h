//
//  FEVerifyCodeRequest.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseRequest.h"

@interface FEVerifyCodeRequest : FEBaseRequest
@property (nonatomic, strong, readonly) NSString *phone_num;
-(id)initWithPhoneNumber:(NSString *)pnumber;
@end
