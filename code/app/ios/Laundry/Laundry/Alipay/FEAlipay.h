//
//  FEAlipay.h
//  Laundry
//
//  Created by Seven on 15-1-28.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>

@class FEOrder;

@interface FEAlipay : NSObject
+(FEAlipay *)sharedInstance;
-(void)payWithOrder:(FEOrder *)forder complete:(void (^)(NSDictionary *result))complete;
@end
