//
//  FEOrderInfoItemCell.h
//  Laundry
//
//  Created by Seven on 15-2-1.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "FEButton.h"

@interface FEOrderInfoItemCell : UITableViewCell
@property (strong, nonatomic) IBOutlet FEButton *titleButton;
@property (strong, nonatomic) IBOutlet UILabel *titleLabel;

@end
