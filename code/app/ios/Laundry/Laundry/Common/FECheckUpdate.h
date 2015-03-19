//
//  FECheckUpdate.h
//  Laundry
//
//  Created by Seven on 15-2-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface FECheckUpdate : NSObject
@property (nonatomic, strong) NSString *lastVersion;
//@property (nonatomic, strong) NSString *buildVersion;
@property (nonatomic, strong) NSString *versionURLString;
+(FECheckUpdate *)sharedInstance;

-(void)chechUpdate:(void (^)(NSError *error,NSString *version,NSString *versionURL))complete;

@end
