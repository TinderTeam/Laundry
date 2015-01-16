//
//  FEOrderCreateRequest.h
//  Laundry
//
//  Created by Seven on 15-1-16.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseRequest.h"
#import "FEOrder.h"
#import "FEOrderDetail.h"

@interface FEOrderCreateRequest : FEBaseRequest
@property (nonatomic, strong, readonly) FEOrder *order;
@property (nonatomic, strong, readonly) NSArray *orderDetailList;

-(id)initWithOrder:(FEOrder *)order orderDetails:(NSArray *)odetail;

@end
