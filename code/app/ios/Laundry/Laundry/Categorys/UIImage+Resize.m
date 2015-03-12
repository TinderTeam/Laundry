//
//  UIImage+Resize.m
//  SmartHome
//
//  Created by Seven on 14-11-2.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "UIImage+Resize.h"

@implementation UIImage (Resize)

-(UIImage*)imageScaledToSize:(CGSize)newSize{
    //创建一个图片容器
    UIGraphicsBeginImageContext(newSize);
    //将原始图片绘制到当前图片容器中
    [self drawInRect:CGRectMake(0, 0, newSize.width, newSize.height)];
    UIImage *newImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    
    return newImage;
}

-(UIImage *)imageScaledToHeight:(CGFloat)height{
    CGFloat width = (self.size.width / self.size.height) * height;
    return [self imageScaledToSize:CGSizeMake(width, height)];
}

-(UIImage *)imageScaledToWidth:(CGFloat)width{
    CGFloat height = (self.size.height / self.size.width) * width;
    return [self imageScaledToSize:CGSizeMake(width, height)];
}

@end
