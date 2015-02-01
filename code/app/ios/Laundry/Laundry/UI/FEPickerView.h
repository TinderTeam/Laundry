//
//  FEPickerView.h
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>

@class FEPickerView;

@protocol FEPickerViewDelegate <NSObject>

@required
-(NSInteger)pickerNumber:(FEPickerView *)picker;
-(NSString *)pickerStringAtIndex:(NSInteger)index;

@optional
-(void)pickerDidSelected:(NSInteger)number;

@end

@interface FEPickerView : UIView

@property (nonatomic, weak) id<FEPickerViewDelegate> delegate;
- (id)initFromView:(UIView *)view;
- (void)show;
@end


