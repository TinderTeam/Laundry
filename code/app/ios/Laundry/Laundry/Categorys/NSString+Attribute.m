//
//  NSString+Attribute.m
//  EShoping
//
//  Created by Seven on 14-12-1.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "NSString+Attribute.h"

@implementation NSString (Attribute)

-(NSString *)attribute{
//    @"T@\"(*)\""
//    NSRegularExpression *regex  = [NSRegularExpression regularExpressionWithPattern:@"T@\"(*)\"" options:NSRegularExpressionCaseInsensitive error:nil];
//    NSString *encodedPoints;
//    NSArray *result = [regex matchesInString:self options:NSMatchingReportProgress range:NSMakeRange(0, self.length)];
//    for (NSTextCheckingResult* b in result){
//        encodedPoints = [apiResponse substringWithRange:b.range];
//    }
    NSArray *array = [self componentsSeparatedByString:@","];
    NSArray *attrs = [[array firstObject] componentsSeparatedByString:@"\""];
    NSString *attr = [attrs objectAtIndex:1];
    return attr;
}

@end
