//
//  THud.h
//  Taxation
//
//  Created by Seven on 15-2-8.
//  Copyright (c) 2015年 Allgateways. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface THud : NSObject
+(THud *)sharedInstance;
-(void)disPlayMessage:(NSString *)message;

@end
