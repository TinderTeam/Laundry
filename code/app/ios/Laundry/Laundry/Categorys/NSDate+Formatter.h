//
//  NSDate+Formatter.h
//  SmartHome
//
//  Created by Seven on 14-11-4.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSDate (Formatter)

- (NSString *)stringForDateWithFormatterString:(NSString *)dateFormatterString;
- (NSString *)defaultFormat;

@end
