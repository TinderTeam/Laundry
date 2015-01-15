//
//  FEGetProductTypeReques.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseRequest.h"

@interface FEGetProductTypeReques : FEBaseRequest
@property (nonatomic, strong, readonly) NSNumber *typeRoot;
-(id)initWithTypeRoot:(NSNumber *)type;
@end
