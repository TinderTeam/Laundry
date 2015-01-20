//
//  FEOrderOperationResponse.h
//  Laundry
//
//  Created by Seven on 15-1-20.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"

@interface FEOrderOperationResponse : FEBaseResponse
@property (nonatomic, strong, readonly) NSString *order_id;
@end
