//
//  FEOrderDetailResponse.h
//  Laundry
//
//  Created by Seven on 15-1-19.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FEOrderDetail.h"

@interface FEOrderDetailResponse : FEBaseResponse
@property (nonatomic, strong, readonly) NSArray *obj;
@end
