//
//  FEGetCustomerResponse.h
//  Laundry
//
//  Created by Seven on 15-2-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FECustomer.h"
@interface FEGetCustomerResponse : FEBaseResponse
@property (nonatomic, strong, readonly) FECustomer *obj;
@end
