//
//  FEOrderDetailVC.h
//  Laundry
//
//  Created by Seven on 15-1-19.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FETableViewController.h"

@class FEOrder;

@protocol FEOrderDetailDelegate <NSObject>

@optional
-(void)orderDidDelete:(FEOrder *)order;

@end

@interface FEOrderDetailVC : FETableViewController
@property (nonatomic, strong) FEOrder *order;

@property (nonatomic, weak) id<FEOrderDetailDelegate> delegate;

@end
