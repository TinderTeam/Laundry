//
//  FEOrderRequest.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseRequest.h"

@interface FEOrderRequest : FEBaseRequest
@property (nonatomic, strong, readonly) NSNumber *user_id;
-(id)initWithUserID:(NSNumber *)uid;
@end
