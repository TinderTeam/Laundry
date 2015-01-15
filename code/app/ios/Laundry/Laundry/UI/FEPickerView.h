//
//  FEPickerView.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol FEPickerViewDelegate <NSObject>

@optional
-(void)pickerDidSelected:(NSInteger)number;

@end

@interface FEPickerView : UIView

@property (nonatomic, weak) id<FEPickerViewDelegate> delegate;
- (id)initFromView:(UIView *)view;
- (void)show;
@end


