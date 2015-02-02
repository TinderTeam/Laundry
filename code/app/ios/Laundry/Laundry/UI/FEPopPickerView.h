//
//  FEPopPickerView.h
//  Laundry
//
//  Created by Seven on 15-2-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import <UIKit/UIKit.h>

@class FEPopPickerView;

@protocol FEPopPickerViewDataSource <NSObject>

@required
-(NSInteger)numberInPicker:(FEPopPickerView *)picker;
-(NSString *)picker:(FEPopPickerView *)picker titleAtIndex:(NSInteger)index;

@optional
-(void)picker:(FEPopPickerView *)picker didSelectAtIndex:(NSInteger)index;

@end

@interface FEPopPickerView : UIView
@property (nonatomic, strong) UILabel *tlabel;
@property (nonatomic, weak) id<FEPopPickerViewDataSource> dataSource;
@property (nonatomic, assign) NSInteger selectIndex;

- (id)initFromView:(UIView *)view;
- (void)show;

@end
