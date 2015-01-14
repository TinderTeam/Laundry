//
//  UIImage+LogN.h
//
//  Created by Rex Sheng on 8/12/13.
//  Copyright (c) 2013 Log(N). All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UIImage (Blending)

- (UIImage *)operateOn:(void (^)(CGContextRef context, CGRect rect))block;
- (UIImage *)blendMode:(CGBlendMode)blendMode color:(CGColorRef)color;
- (UIImage *)blendMode:(CGBlendMode)blendMode image:(CGImageRef)image;
- (UIImage *)blendMode:(CGBlendMode)blendMode color:(CGColorRef)color reverse:(BOOL)reverse;

@end

@interface UIImage (Colors)

+ (UIImage *)imageFromSize:(CGSize)size block:(void(^)(CGContextRef))block;
+ (UIImage *)imageFromColor:(UIColor *)color;
+ (UIImage *)imageFromColor:(UIColor *)color toColor:(UIColor *)toColor size:(CGSize)size cornerRadius:(CGFloat)cornerRadius;
+ (UIImage *)imageFromColors:(NSArray *)colors verticalLocations:(NSArray *)locationsObjects size:(CGSize)size cornerRadius:(CGFloat)cornerRadius;
+ (UIImage *)imageFromColors:(NSArray *)colors horizontalLocations:(NSArray *)locationsObjects size:(CGSize)size cornerRadius:(CGFloat)cornerRadius;

@end

@interface UIImage (Mask)

- (UIImage *)maskedWithImage:(UIImage *)mask;
- (UIImage *)maskedWithMaskName:(NSString *)maskName;
- (UIImage *)maskAndOverlayWithMaskName:(NSString *)maskName;
- (UIImage *)clippingMask:(CGColorRef)clippingMask;

@end

@interface UIImage (Resizing)

- (UIImage *)wrap:(UIImage *)newImage;
- (UIImage *)resizedImageFitSize:(CGSize)frameSize;
- (UIImage *)resizedImageFitSize:(CGSize)frameSize edgeInsets:(UIEdgeInsets)insets;
- (UIImage *)resizedImage:(CGFloat)ratio;
- (UIImage *)resizedImage:(CGFloat)ratio edgeInsets:(UIEdgeInsets)insets;

- (CGImageRef)imageWithAlphaChannel;

@end

@interface UIImage (Shadow)

- (UIImage *)addShadowSize:(CGSize)shadowSize shadowBlur:(CGFloat)blur shadowColor:(CGColorRef)shadowColor;
- (UIImage *)addShadowSize:(CGSize)shadowSize shadowBlur:(CGFloat)blur shadowColor:(CGColorRef)shadowColor clippingMask:(CGColorRef)clippingMask;

@end

