//
//  FEAlipay.h
//  Laundry
//
//  Created by Seven on 15-1-28.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface FEAlipay : NSObject
+(FEAlipay *)sharedInstance;
-(void)pay;
@end
