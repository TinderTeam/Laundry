//
//  FEBaseRequest.h
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"
#import "FEWebServiceDefine.h"

@interface FEBaseRequest : SSObject

@property (nonatomic, strong, readonly) NSNumber *token;
@property (nonatomic, strong, readonly) NSNumber *app_id;
@property (nonatomic, strong, readonly) NSString *clientType;
@property (nonatomic, strong, readonly) NSString *clientVersion;
@property (nonatomic, strong, readonly) NSString *method;

-(id)initWithMethod:(NSString *)method;

@end
