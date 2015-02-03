//
//  FEGetCustomerRequest.h
//  Laundry
//
//  Created by Seven on 15-2-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseRequest.h"

@interface FEGetCustomerRequest : FEBaseRequest
@property (nonatomic, strong) NSNumber *obj;

-(id)initWithCid:(NSNumber *)obj;

@end
