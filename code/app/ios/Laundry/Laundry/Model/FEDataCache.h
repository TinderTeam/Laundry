//
//  FEDataCache.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "FEProduct.h"
#import "FECompany.h"

#define __KEY_TITLE @"title"
#define __KEY_PNG   @"png"
#define __KEY_NUMBER    @"number"

@interface FEDataCache : NSObject
+(FEDataCache *)sharedInstance;
@property (nonatomic, strong, readonly) NSArray *selectProducts;
@property (nonatomic, strong) FECompany *company;
@property (nonatomic, strong) NSArray *cateGoryList;

-(void)getProductForID:(NSNumber *)rid block:(void (^)(NSArray *list))block;

-(void)addSelectProduct:(FEProduct *)product;
-(void)removeSelectProduct:(FEProduct *)product;
-(void)clearSelectProduct;

-(void)setProduct:(FEProduct *)product number:(NSNumber *)number;
-(NSNumber *)productNumber:(FEProduct *)product;



@end
