//
//  NSDate+Formatter.m
//  SmartHome
//
//  Created by Seven on 14-11-4.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "NSDate+Formatter.h"

@implementation NSDate (Formatter)

-(NSString *)defaultFormat{
    return [self stringForDateWithFormatterString:@"MM/dd HH:mm"];
}

- (NSString *)stringForDateWithFormatterString:(NSString *)dateFormatterString {
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:dateFormatterString];
    
    [formatter setLocale:[NSLocale currentLocale]];
    
    return [formatter stringFromDate:self];
}

@end
