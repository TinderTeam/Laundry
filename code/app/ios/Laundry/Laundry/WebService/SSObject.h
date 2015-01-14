//
//  SSObject.h
//  SSCommon
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 Allgateways. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SSObject : NSObject
@property (nonatomic, strong, readonly) NSDictionary *dictionary;
-(id)initWithDictionary:(id)dictionary;

@end
