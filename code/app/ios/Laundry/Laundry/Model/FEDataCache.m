//
//  FEDataCache.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEDataCache.h"
#import "FELaundryWebService.h"
#import "FEGetProductRequest.h"
#import "FEGetProductResponse.h"

@interface FEDataCache ()
@property (nonatomic, strong) NSArray *allProduct;
@property (nonatomic, strong) NSMutableSet *selectProduct;
@property (nonatomic, strong) NSMutableDictionary *selectedProuductCount;

@end

@implementation FEDataCache

+(FEDataCache *)sharedInstance{
    static FEDataCache *instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = (FEDataCache *)[[self alloc] init];
    });
    return instance;
}

-(void)getProductForID:(NSNumber *)rid block:(void (^)(NSArray *list))block{
    NSPredicate *pre = [NSPredicate predicateWithFormat:@"SELF.type_id == %@",rid];
    if (!self.allProduct) {
        [[FELaundryWebService sharedInstance] request:[[FEGetProductRequest alloc] initWithRid:@(3) keyword:@""] responseClass:[FEGetProductResponse class] response:^(NSError *error, id response) {
            FEGetProductResponse *rsp = response;
            if (!error && rsp.errorCode.integerValue == 0) {
                self.allProduct = rsp.obj;
            }
            block([self.allProduct filteredArrayUsingPredicate:pre]);
        }];
    }else{
        block([self.allProduct filteredArrayUsingPredicate:pre]);
    }
}

-(NSArray *)selectProducts{
    return self.selectProduct.allObjects;
}

-(void)addSelectProduct:(FEProduct *)product{
    if (!_selectProduct) {
        _selectProduct = [NSMutableSet new];
    }
    [_selectProduct addObject:product];
    [self setProduct:product number:@(1)];
}

-(void)removeSelectProduct:(FEProduct *)product{
    [_selectProduct removeObject:product];
    [_selectedProuductCount removeObjectForKey:product.product_id];
}

-(void)setProduct:(FEProduct *)product number:(NSNumber *)number{
    if (!_selectedProuductCount) {
        _selectedProuductCount = [NSMutableDictionary new];
    }
    [_selectedProuductCount setObject:number forKey:product.product_id];
}

-(void)clearSelectProduct{
    for (id item in self.selectProducts) {
        [self removeSelectProduct:item];
    }
}


-(NSNumber *)productNumber:(FEProduct *)product{
    return [_selectedProuductCount objectForKey:product.product_id];
}



@end
