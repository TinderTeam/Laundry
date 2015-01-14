//
//  FEBaseResponse.h
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FEBaseResponse : SSObject
@property (nonatomic, strong, readonly) NSNumber *errorCode;
@property (nonatomic, strong, readonly) NSString *errorMsg;

-(id)initWithResponse:(id)response;
-(NSArray *)getListFromObject:(id)obj class:(Class)cls;
@end
