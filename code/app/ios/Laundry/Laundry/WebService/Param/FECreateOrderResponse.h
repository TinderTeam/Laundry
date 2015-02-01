//
//  FECreateOrderResponse.h
//  Laundry
//
//  Created by Seven on 15-2-1.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FEOrder.h"

@interface FECreateOrderResponse : FEBaseResponse
@property (nonatomic, strong, readonly) FEOrder *obj;

@end
