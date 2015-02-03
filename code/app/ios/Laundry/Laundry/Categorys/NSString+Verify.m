//
//  NSString+Verify.m
//  EShoping
//
//  Created by Seven on 14-11-29.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "NSString+Verify.h"
#import <libPhoneNumber-iOS/NBPhoneNumberUtil.h>

@implementation NSString (Verify)

-(BOOL)isEmailType{
    NSString *emailRegex = @"[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
    NSPredicate *emailTest = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", emailRegex];
    return [emailTest evaluateWithObject:self];
}

-(BOOL)isPhone{
    NSError *error;
    NBPhoneNumber *phone = [[NBPhoneNumberUtil sharedInstance] parse:self defaultRegion:@"CN" error:&error];
    if ([[NBPhoneNumberUtil sharedInstance] isValidNumber:phone]) {
        return YES;
    }else{
        return NO;
    }
}



@end
