//
//  FEInfoInputVC.h
//  Laundry
//
//  Created by Seven on 15-2-1.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//


#import "FEViewController.h"
#import "FEOrderInfo.h"

@interface FEInfoInputVC : FEViewController
@property (nonatomic, strong) FEOrderInfo *orderInfo;
@property (nonatomic, strong) NSString *typeName;
@end
