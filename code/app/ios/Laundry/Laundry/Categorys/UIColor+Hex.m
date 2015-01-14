//
//  UIColor+Hex.m
//  Taxation
//
//  Created by Seven on 15-1-5.
//  Copyright (c) 2015年 Allgateways. All rights reserved.
//

#import "UIColor+Hex.h"

@implementation UIColor (Hex)

+(UIColor *)colorWithHex:(NSInteger)hex{
    return [UIColor colorWithRed:((hex&0xff0000)>>16) / 255.0f green:((hex&0xff00)>>8) / 255.0f blue:((hex&0xff)>>0) / 255.0f alpha:1.0f];
}

@end
