//
//  FEOrderCancelRequest.h
//  Laundry
//
//  Created by Seven on 15-2-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseRequest.h"

@interface FEOrderCancelRequest : FEBaseRequest
@property (nonatomic, strong, readonly) NSNumber *obj;
-(id)initWithOrderID:(NSNumber *)oid;

@end
