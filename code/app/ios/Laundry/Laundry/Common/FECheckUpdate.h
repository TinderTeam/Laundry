//
//  FECheckUpdate.h
//  Laundry
//
//  Created by Seven on 15-2-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface FECheckUpdate : NSObject
+(FECheckUpdate *)sharedInstance;

-(void)chechUpdate:(void (^)(NSError *error,id response))complete;

@end
