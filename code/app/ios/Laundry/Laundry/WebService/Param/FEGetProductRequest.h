//
//  FEGetProductRequest.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseRequest.h"

@interface FEGetProductRequest : FEBaseRequest

@property (nonatomic, strong, readonly) NSNumber *typeRoot;
@property (nonatomic, strong, readonly) NSString *keyWord;

-(id)initWithRid:(NSNumber *)rid keyword:(NSString *)key;
@end
