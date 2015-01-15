//
//  FESigninResponse.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FEUser.h"

@interface FESigninResponse : FEBaseResponse
@property (nonatomic, strong, readonly) FEUser *user;
@property (nonatomic, strong, readonly) NSString *token;

@end
