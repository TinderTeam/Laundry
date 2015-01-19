//
//  FEOrderDetailVC.h
//  Laundry
//
//  Created by Seven on 15-1-19.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FETableViewController.h"

@class FEOrder;

@interface FEOrderDetailVC : FETableViewController
@property (nonatomic, strong) FEOrder *order;
@end
