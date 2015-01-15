//
//  FEBasketItemCell.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "FEProduct.h"


@interface FEBasketItemCell : UITableViewCell
@property (strong, nonatomic) IBOutlet UILabel *titleLabel;
@property (strong, nonatomic) IBOutlet UILabel *priceLabel;
@property (strong, nonatomic) IBOutlet UITextField *numberTextField;
@property (strong, nonatomic) FEProduct *product;

-(void)configWithProduct:(FEProduct *)product number:(NSNumber *)number;

@end
