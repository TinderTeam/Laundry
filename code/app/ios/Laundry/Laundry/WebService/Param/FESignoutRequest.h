//
//  FESignoutRequest.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseRequest.h"
#import "FEUser.h"

@interface FESignoutRequest : FEBaseRequest
@property (nonatomic, strong, readonly) FEUser *obj;
-(id)initWithUser:(FEUser *)user;
@end
