//
//  NSString+Number.m
//  EShoping
//
//  Created by Seven on 15-1-6.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "NSString+Number.h"

@implementation NSString (Number)

+(NSString *)stringWithNumber:(NSNumber *)number{
    return [NSString stringWithFormat:@"%@",number];
}

+(NSString *)priceStringWithNumber:(NSNumber *)number{
    return [NSString stringWithFormat:@"￥ %@",number];
}

@end
