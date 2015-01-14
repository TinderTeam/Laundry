//
//  UIImage+LogN.m
//
//  Created by Rex Sheng on 8/12/13.
//  Copyright (c) 2013 Log(N). All rights reserved.
//

#import "UIImage+LogN.h"
#import <AVFoundation/AVFoundation.h>

@implementation UIImage (Blending)

- (UIImage *)operateOn:(void (^)(CGContextRef context, CGRect rect))block
{
	CGColorSpaceRef colourSpace = CGColorSpaceCreateDeviceRGB();
	CGSize size = CGSizeMake(self.size.width * self.scale, self.size.height * self.scale);
	CGRect rect = CGRectMake(0, 0, size.width, size.height);
	CGContextRef context = CGBitmapContextCreate(NULL, size.width, size.height, CGImageGetBitsPerComponent(self.CGImage), 0, colourSpace, CGImageGetBitmapInfo(self.CGImage));
	CGColorSpaceRelease(colourSpace);
	
	block(context, rect);
	
	CGImageRef cgImage = CGBitmapContextCreateImage(context);
    CGContextRelease(context);
    UIImage *image = [UIImage imageWithCGImage:cgImage scale:[UIScreen mainScreen].scale orientation:UIImageOrientationUp];
    CGImageRelease(cgImage);
	return image;
}

- (UIImage *)blendMode:(CGBlendMode)blendMode color:(CGColorRef)color reverse:(BOOL)reverse
{
	return [self operateOn:^(CGContextRef context, CGRect rect) {
		CGContextClipToMask(context, rect, self.CGImage);
		CGContextDrawImage(context, rect, self.CGImage);
		CGContextSetBlendMode(context, blendMode);
		CGContextSetFillColorWithColor(context, color);
		CGContextFillRect(context, rect);
		if (reverse) {
			CGContextSetBlendMode(context, kCGBlendModeDifference);
			CGContextSetFillColorWithColor(context, [UIColor whiteColor].CGColor);
			CGContextFillRect(context, rect);
		}
	}];
}

- (UIImage *)blendMode:(CGBlendMode)blendMode color:(CGColorRef)color
{
	return [self blendMode:blendMode color:color reverse:NO];
}

- (UIImage *)blendMode:(CGBlendMode)blendMode image:(CGImageRef)image
{
	return [self operateOn:^(CGContextRef context, CGRect rect) {
		CGImageRef mask = CGImageMaskCreate(CGImageGetWidth(image),
											CGImageGetHeight(image),
											CGImageGetBitsPerComponent(image),
											CGImageGetBitsPerPixel(image),
											CGImageGetBytesPerRow(image),
											CGImageGetDataProvider(image), NULL, false);
		CGContextClipToMask(context, rect, mask);
		CGImageRelease(mask);
		CGContextSetFillColorWithColor(context, [UIColor whiteColor].CGColor);
		CGContextFillRect(context, rect);
		
		CGContextDrawImage(context, rect, self.CGImage);
		CGContextSetBlendMode(context, blendMode);
		CGContextDrawImage(context, rect, image);
	}];
}

@end

@implementation UIImage (Colors)

+ (UIImage *)imageFromColor:(UIColor *)color
{
	return [self imageFromSize:CGSizeMake(1, 1) block:^(CGContextRef context) {
		CGRect rect = CGRectMake(0, 0, 1, 1);
		CGContextSetFillColorWithColor(context, [color CGColor]);
		CGContextFillRect(context, rect);
	}];
}

+ (UIImage *)imageFromSize:(CGSize)size block:(void(^)(CGContextRef))block
{
	UIGraphicsBeginImageContextWithOptions(size, NO, [UIScreen mainScreen].scale);
	CGContextRef context = UIGraphicsGetCurrentContext();
	block(context);
	UIImage *img = UIGraphicsGetImageFromCurrentImageContext();
	UIGraphicsEndImageContext();
	return img;
}

+ (UIImage *)imageFromColors:(NSArray *)colors verticalLocations:(NSArray *)locationsObjects size:(CGSize)size cornerRadius:(CGFloat)cornerRadius
{
	return [self imageFromSize:size block:^(CGContextRef context) {
		CGRect rect = (CGRect){.size = size};
		UIBezierPath *path = [UIBezierPath bezierPathWithRoundedRect:rect cornerRadius:cornerRadius];
		CGContextAddPath(context, path.CGPath);
		
		NSMutableArray *convertedColorArray = [NSMutableArray arrayWithCapacity:colors.count];
		for (UIColor *color in colors) {
			[convertedColorArray addObject:(__bridge id)[color CGColor]];
		}
		
		CGFloat locations[locationsObjects.count];
		for (int i = 0; i < locationsObjects.count; i++) {
			locations[i] = [locationsObjects[i] floatValue];
		}
		CGColorSpaceRef colorSpace = CGColorSpaceCreateDeviceRGB();
		CGGradientRef gradient = CGGradientCreateWithColors(colorSpace, (__bridge CFArrayRef)convertedColorArray, locations);
		CFRelease(colorSpace);
		CGContextClip(context);
		CGContextDrawLinearGradient(context, gradient, CGPointMake(0.5, 0), CGPointMake(0.5, size.height), 0);
		CFRelease(gradient);
	}];
}

+ (UIImage *)imageFromColors:(NSArray *)colors horizontalLocations:(NSArray *)locationsObjects size:(CGSize)size cornerRadius:(CGFloat)cornerRadius
{
	return [self imageFromSize:size block:^(CGContextRef context) {
		CGRect rect = (CGRect){.size = size};
		UIBezierPath *path = [UIBezierPath bezierPathWithRoundedRect:rect cornerRadius:cornerRadius];
		CGContextAddPath(context, path.CGPath);
		
		NSMutableArray *convertedColorArray = [NSMutableArray arrayWithCapacity:colors.count];
		for (UIColor *color in colors) {
			[convertedColorArray addObject:(__bridge id)[color CGColor]];
		}
		
		CGFloat locations[locationsObjects.count];
		for (int i = 0; i < locationsObjects.count; i++) {
			locations[i] = [locationsObjects[i] floatValue];
		}
		CGColorSpaceRef colorSpace = CGColorSpaceCreateDeviceRGB();
		CGGradientRef gradient = CGGradientCreateWithColors(colorSpace, (__bridge CFArrayRef)convertedColorArray, locations);
		CFRelease(colorSpace);
		CGContextClip(context);
		CGContextDrawLinearGradient(context, gradient, CGPointMake(0, 0.5), CGPointMake(size.width, 0.5), 0);
		CFRelease(gradient);
	}];
}

+ (UIImage *)imageFromColor:(UIColor *)color toColor:(UIColor *)toColor size:(CGSize)size cornerRadius:(CGFloat)cornerRadius
{
	return [self imageFromColors:@[color, toColor] verticalLocations:@[@0, @1] size:size cornerRadius:cornerRadius];
}

@end

@implementation UIImage (Mask)

- (UIImage *)maskAndOverlayWithMaskName:(NSString *)maskName
{
	UIImage *overlay = [UIImage imageNamed:[NSString stringWithFormat:@"%@_overlay", maskName]];
	UIImage *mask = [UIImage imageNamed:[NSString stringWithFormat:@"%@_mask", maskName]];
	
	CGSize size;
	if (overlay) {
		size = CGSizeMake(overlay.size.width * overlay.scale, overlay.size.height * overlay.scale);
	} else {
		size = CGSizeMake(mask.size.width * mask.scale, mask.size.height * mask.scale);
	}
	CGRect rect = CGRectMake(0, 0, size.width, size.height);
	
	CGRect maskRect = CGRectMake(0, 0, mask.size.width * mask.scale, mask.size.height * mask.scale);
	maskRect = CGRectOffset(maskRect, (rect.size.width - maskRect.size.width) / 2, ceilf((rect.size.height - maskRect.size.height) / 2));
	
	CGColorSpaceRef colourSpace = CGColorSpaceCreateDeviceRGB();
	CGContextRef context = CGBitmapContextCreate(NULL, size.width, size.height, CGImageGetBitsPerComponent(self.CGImage), 0, colourSpace, (CGBitmapInfo) kCGImageAlphaPremultipliedLast);
	CGColorSpaceRelease(colourSpace);
	
	CGContextSaveGState(context);
	CGContextClipToMask(context, maskRect, mask.CGImage);
	CGContextDrawImage(context, maskRect, self.CGImage);
	CGContextRestoreGState(context);
	if (overlay) {
		CGContextDrawImage(context, rect, overlay.CGImage);
	}
	CGImageRef cgImage = CGBitmapContextCreateImage(context);
	CGContextRelease(context);
	UIImage *image = [UIImage imageWithCGImage:cgImage scale:[UIScreen mainScreen].scale orientation:UIImageOrientationUp];
	CGImageRelease(cgImage);
	return image;
}

- (UIImage *)maskedWithMaskName:(NSString *)maskName
{
	return [self maskedWithImage:[UIImage imageNamed:[NSString stringWithFormat:@"%@_mask", maskName]]];
}

- (UIImage *)maskedWithImage:(UIImage *)mask
{
	CGSize size = CGSizeMake(mask.size.width * mask.scale, mask.size.height * mask.scale);
	CGRect rect = CGRectMake(0, 0, size.width, size.height);
	CGRect maskRect = CGRectMake(0, 0, mask.size.width * mask.scale, mask.size.height * mask.scale);
	maskRect = CGRectOffset(maskRect, (rect.size.width - maskRect.size.width) / 2, (rect.size.height - maskRect.size.height) / 2);
	
	CGColorSpaceRef colourSpace = CGColorSpaceCreateDeviceRGB();
	CGContextRef context = CGBitmapContextCreate(NULL, size.width, size.height, CGImageGetBitsPerComponent(self.CGImage), 0, colourSpace, (CGBitmapInfo) kCGImageAlphaPremultipliedLast);
	CGColorSpaceRelease(colourSpace);
	
	CGContextClipToMask(context, maskRect, mask.CGImage);
	CGContextDrawImage(context, maskRect, self.CGImage);
	
	CGImageRef cgImage = CGBitmapContextCreateImage(context);
	CGContextRelease(context);
	UIImage *image = [UIImage imageWithCGImage:cgImage scale:[UIScreen mainScreen].scale orientation:UIImageOrientationUp];
	CGImageRelease(cgImage);
	return image;
}

- (UIImage *)maskWithMask:(UIImage *)maskImage
{
	CGImageRef maskRef = maskImage.CGImage;
	
	CGImageRef mask = CGImageMaskCreate(CGImageGetWidth(maskRef),
										CGImageGetHeight(maskRef),
										CGImageGetBitsPerComponent(maskRef),
										CGImageGetBitsPerPixel(maskRef),
										CGImageGetBytesPerRow(maskRef),
										CGImageGetDataProvider(maskRef), NULL, false);
	
	CGImageRef masked = CGImageCreateWithMask(self.CGImage, mask);
	CGImageRelease(mask);
	UIImage *image = [UIImage imageWithCGImage:masked];
	CGImageRelease(masked);
	return image;
}

- (UIImage *)clippingMask:(CGColorRef)clippingMask
{
	return [self operateOn:^(CGContextRef context, CGRect rect) {
		CGImageRef image = self.CGImage;
		CGImageRef mask = CGImageMaskCreate(CGImageGetWidth(image),
											CGImageGetHeight(image),
											CGImageGetBitsPerComponent(image),
											CGImageGetBitsPerPixel(image),
											CGImageGetBytesPerRow(image),
											CGImageGetDataProvider(image), NULL, YES);
		CGContextClipToMask(context, rect, mask);
		CGImageRelease(mask);
		CGContextSetFillColorWithColor(context, clippingMask);
		CGContextFillRect(context, rect);
	}];
}

@end

@implementation UIImage (Resizing)

- (UIImage *)wrap:(UIImage *)newImage
{
	return [self operateOn:^(CGContextRef context, CGRect rect) {
		CGImageRef image = self.CGImage;
		CGImageRef mask = CGImageMaskCreate(CGImageGetWidth(image),
											CGImageGetHeight(image),
											CGImageGetBitsPerComponent(image),
											CGImageGetBitsPerPixel(image),
											CGImageGetBytesPerRow(image),
											CGImageGetDataProvider(image), NULL, false);
		CGContextClipToMask(context, rect, mask);
		CGImageRelease(mask);
		CGContextSetFillColorWithColor(context, [UIColor whiteColor].CGColor);
		CGContextFillRect(context, rect);
		CGFloat myRatio = rect.size.width / rect.size.height;
		CGSize newSize = newImage.size;
		CGFloat newRatio = newSize.width / newSize.height;
		CGFloat insetY = 0, insetX = 0;
		if (newRatio > myRatio) {
			insetY = (rect.size.height - rect.size.width / newRatio) / 2;
		} else {
			insetX = (rect.size.width - rect.size.height * newRatio) / 2;
		}
		CGRect newRect = CGRectInset(rect, insetX, insetY);
		CGContextDrawImage(context, newRect, newImage.CGImage);
		CGContextSetBlendMode(context, kCGBlendModeNormal);
		CGContextDrawImage(context, rect, image);
	}];
}

- (CGImageRef)imageWithAlphaChannel
{
	size_t width = self.size.width;
	size_t height = self.size.height;
	
	NSMutableData *data = [NSMutableData dataWithLength:width*height];
	
	CGContextRef context = CGBitmapContextCreate([data mutableBytes], width, height, 8, width, NULL, kCGBitmapAlphaInfoMask);
	
	// Set the blend mode to copy to avoid any alteration of the source data
	CGContextSetBlendMode(context, kCGBlendModeCopy);
	
	// Draw the image to extract the alpha channel
	CGContextDrawImage(context, CGRectMake(0.0, 0.0, width, height), self.CGImage);
	CGContextRelease(context);
	
	CGDataProviderRef dataProvider = CGDataProviderCreateWithCFData((__bridge CFMutableDataRef)data);
	
	CGImageRef maskingImage = CGImageMaskCreate(width, height, 8, 8, width, dataProvider, NULL, TRUE);
	CGDataProviderRelease(dataProvider);
	CGImageRelease(maskingImage);
	return maskingImage;
}

- (UIImage *)resizedImageFitSize:(CGSize)frameSize edgeInsets:(UIEdgeInsets)insets
{
	CGSize imageSize = self.size;
	CGFloat ratio = 1;
	CGFloat targetHeight = imageSize.height, targetWidth = imageSize.width;
	if (self.imageOrientation != UIImageOrientationUp && self.imageOrientation != UIImageOrientationDown) {
		CGFloat i = targetHeight;
		targetHeight = targetWidth;
		targetWidth = i;
	}
	CGFloat height = frameSize.height, width = frameSize.width;
	height += insets.top + insets.bottom;
	width += insets.left + insets.right;
	
	CGFloat frameRatio = width / height;
	CGFloat imageRatio = targetWidth / targetHeight;
	if (imageRatio < frameRatio) {
		width = height * imageRatio;
	}
	ratio = width / targetHeight;
	return [self resizedImage:ratio edgeInsets:insets];
}

- (UIImage *)resizedImageFitSize:(CGSize)frameSize
{
	return [self resizedImageFitSize:frameSize edgeInsets:UIEdgeInsetsZero];
}

- (UIImage *)resizedImage:(CGFloat)ratio
{
	return [self resizedImage:ratio edgeInsets:UIEdgeInsetsZero];
}

- (UIImage *)resizedImage:(CGFloat)ratio edgeInsets:(UIEdgeInsets)insets
{
	CGFloat scale = [UIScreen mainScreen].scale;
	UIImage *sourceImage = self;
	
	CGImageRef imageRef = [sourceImage CGImage];
	CGColorSpaceRef colorSpaceInfo = CGColorSpaceCreateDeviceRGB();
	
	CGFloat targetWidth = (sourceImage.size.width * ratio) * scale;
	CGFloat targetHeight = (sourceImage.size.height * ratio) * scale;
	//	NSLog(@"%f x %f", targetWidth, targetHeight);
	CGFloat width = targetWidth, height = targetHeight;
	if (sourceImage.imageOrientation != UIImageOrientationUp && sourceImage.imageOrientation != UIImageOrientationDown) {
		CGFloat i = targetHeight;
		targetHeight = targetWidth;
		targetWidth = i;
		width -= (insets.left + insets.right) * scale;
		height -= (insets.top + insets.bottom) * scale;
	} else {
		height -= (insets.left + insets.right) * scale;
		width -= (insets.top + insets.bottom) * scale;
	}
	
	CGContextRef bitmap = CGBitmapContextCreate(NULL, width, height, CGImageGetBitsPerComponent(imageRef), 0, colorSpaceInfo, (CGBitmapInfo) kCGImageAlphaPremultipliedFirst);
	CGColorSpaceRelease(colorSpaceInfo);
	
	CGContextSetInterpolationQuality(bitmap, kCGInterpolationHigh);
	
	if (sourceImage.imageOrientation == UIImageOrientationLeft) {
		CGContextRotateCTM (bitmap, M_PI / 2);
		CGContextTranslateCTM (bitmap, 0 - insets.top * scale, -targetHeight + insets.right * scale);
	} else if (sourceImage.imageOrientation == UIImageOrientationRight) {
		CGContextRotateCTM (bitmap, -M_PI / 2);
		//bottom, left
		CGContextTranslateCTM (bitmap, - targetWidth + insets.bottom * scale, -insets.left * scale);
	} else if (sourceImage.imageOrientation == UIImageOrientationUp) {
		CGContextTranslateCTM (bitmap, -insets.top * scale, -insets.right * scale);
	} else if (sourceImage.imageOrientation == UIImageOrientationDown) {
		CGContextTranslateCTM (bitmap, targetWidth - insets.bottom * scale, targetHeight - insets.left * scale);
		CGContextRotateCTM (bitmap, -M_PI);
	}
	
	CGContextDrawImage(bitmap, CGRectMake(0, 0, targetWidth, targetHeight), imageRef);
	CGImageRef ref = CGBitmapContextCreateImage(bitmap);
	UIImage *image = [UIImage imageWithCGImage:ref scale:scale orientation:UIImageOrientationUp];
	
	CGContextRelease(bitmap);
	CGImageRelease(ref);
	
	return image;
}

@end

@implementation UIImage (Shadow)

- (UIImage *)addShadowSize:(CGSize)shadowSize shadowBlur:(CGFloat)blur shadowColor:(CGColorRef)shadowColor
{
	return [self operateOn:^(CGContextRef context, CGRect rect) {
		CGContextSetShadowWithColor(context, shadowSize, blur, shadowColor);
		CGContextDrawImage(context, rect, self.CGImage);
	}];
}

- (UIImage *)addShadowSize:(CGSize)shadowSize shadowBlur:(CGFloat)blur shadowColor:(CGColorRef)shadowColor clippingMask:(CGColorRef)clippingMask
{
	return [self operateOn:^(CGContextRef context, CGRect rect) {
		CGContextSetShadowWithColor(context, shadowSize, blur, shadowColor);
		CGContextDrawImage(context, rect, self.CGImage);
		if (clippingMask) {
			CGContextClipToMask(context, rect, self.CGImage);
			CGContextSetFillColorWithColor(context, clippingMask);
			CGContextFillRect(context, rect);
		}
	}];
}

@end

