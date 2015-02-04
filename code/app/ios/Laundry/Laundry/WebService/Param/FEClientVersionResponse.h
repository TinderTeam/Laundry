//
//  FEClientVersionResponse.h
//  Laundry
//
//  Created by Seven on 15-2-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FEClientVersion.h"

@interface FEClientVersionResponse : FEBaseResponse

@property (nonatomic, strong, readonly) FEClientVersion *obj;

@end
