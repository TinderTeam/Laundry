//
//  NSString+DateFromNow.h
//  EShoping
//
//  Created by Seven on 14-12-10.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSString (DateFromNow)

-(NSInteger)dateFromNow;
-(NSString *)dateString;
-(NSDate *)dateFromStringFormat:(NSString *)format;
@end
