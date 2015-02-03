//
//  FEModifyProfileRequest.h
//  Laundry
//
//  Created by Seven on 15-2-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseRequest.h"

@class FECustomer;

@interface FEModifyProfileRequest : FEBaseRequest
@property (nonatomic, strong, readonly) FECustomer *obj;
-(id)initWithCustomer:(FECustomer *)customer;
@end
