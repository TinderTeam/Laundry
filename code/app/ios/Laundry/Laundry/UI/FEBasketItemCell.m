//
//  FEBasketItemCell.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBasketItemCell.h"

@interface FEBasketItemCell ()

@end

@implementation FEBasketItemCell

- (void)awakeFromNib {
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}
-(void)configWithProduct:(FEProduct *)product number:(NSNumber *)number{
    _product = product;
    self.priceLabel.text = [NSString stringWithFormat:@"%.2f",(product.price.floatValue * number.integerValue)];
    self.titleLabel.text = product.product_name;
    if ([product.price_type isEqualToString:@"面议"]) {
        self.perPriceLabel.text = [NSString stringWithFormat:@"面议"];
        self.priceLabel.text = [NSString stringWithFormat:@"面议"];
    }else{
        self.perPriceLabel.text = [NSString stringWithFormat:@"%@",product.price];
        self.priceLabel.text = [NSString stringWithFormat:@"%.2f",(product.price.floatValue * number.integerValue)];
    }
    self.numberTextField.text = number.stringValue;

}


@end
