//
//  FEInputAlertView.h
//  Laundry
//
//  Created by Seven on 15-1-18.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "CustomIOS7AlertView.h"

@interface FEInputAlertView : CustomIOS7AlertView
@property (nonatomic, strong) UITextField *inputView;

-(id)initWithTitle:(NSString *)title Titles:(NSArray *)titlearray;
@end
