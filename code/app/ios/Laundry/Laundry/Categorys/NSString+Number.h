//
//  NSString+Number.h
//  EShoping
//
//  Created by Seven on 15-1-6.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSString (Number)

+(NSString *)stringWithNumber:(NSNumber *)number;
+(NSString *)priceStringWithNumber:(NSNumber *)number;
@end
