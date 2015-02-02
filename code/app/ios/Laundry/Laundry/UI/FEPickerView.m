//
//  FEPickerView.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEPickerView.h"
#import "FEButton.h"

#define _DefaultTime_ 3
#define _MineTime_    2
#define _DateFormatter_ @"yyyy/MM/dd HH:mm:00"

@interface FEPickerView ()<UIPickerViewDataSource,UIPickerViewDelegate>{
    __unsafe_unretained UIView *_view;
}
@property (nonatomic, strong) UIPickerView *pview;
@property (nonatomic, strong) UIView *toolbar;
@property (nonatomic, strong) UIControl *maskview;


@end

@implementation FEPickerView

- (id)initFromView:(UIView *)view
{
    self = [super initWithFrame:CGRectMake(0, view.bounds.size.height, view.bounds.size.width, 230)];
    if (self) {
        // Initialization code
        _view = view;
//        _view = [[AppDelegate sharedDelegate].window viewWithTag:0];
        self.backgroundColor = kThemeColor;
        UIControl *mask = [[UIControl alloc] initWithFrame:_view.bounds];
        [mask addTarget:self action:@selector(dismiss) forControlEvents:UIControlEventTouchUpInside];
        self.maskview = mask;
        mask.backgroundColor = [UIColor blackColor];
        mask.alpha = 0.5;
        
        UIView *toolbar = [[UIView alloc] initWithFrame:CGRectMake(0, 0, self.bounds.size.width, 44)];
//        UIToolbar *toolbar = [[UIToolbar alloc]initWithFrame:CGRectMake(0, 0, self.bounds.size.width, 44)];
        toolbar.backgroundColor = [UIColor whiteColor];
        self.toolbar = toolbar;
        
        FEButton *item0 = [FEButton buttonWithType:UIButtonTypeCustom];
        [item0 setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        item0.frame = CGRectMake(toolbar.bounds.size.width - 60 - 10, (self.toolbar.bounds.size.height - 35) / 2.0, 60, 35);
        [item0 setTitle:@"确定" forState:UIControlStateNormal];
        [item0 addTarget:self action:@selector(dismiss:) forControlEvents:UIControlEventTouchUpInside];
        [toolbar addSubview:item0];
        
        UILabel *titleLabel = [[UILabel alloc] initWithFrame:CGRectMake(3, 0, item0.frame.origin.x - 3, toolbar.bounds.size.height)];
        self.titleLabel = titleLabel;
        titleLabel.textColor = [UIColor blackColor];
        titleLabel.text = @"所选衣物";
        [toolbar addSubview:titleLabel];
        
        [self addSubview:toolbar];
        [self initWithPickerView];
    }
    return self;
}


- (void)initWithPickerView{
    UIView *e = [[UIView alloc] initWithFrame:CGRectMake(0, self.toolbar.bounds.size.height + 2, self.bounds.size.width, self.bounds.size.height - self.toolbar.bounds.size.height - 2)];
    e.backgroundColor = [UIColor whiteColor];
    [self addSubview:e];
    
    UIPickerView *p = [[UIPickerView alloc]initWithFrame:CGRectMake(0, self.toolbar.bounds.size.height + 2, self.bounds.size.width, self.bounds.size.height - self.toolbar.bounds.size.height - 2)];
    p.backgroundColor = [UIColor whiteColor];
    p.showsSelectionIndicator = YES;
    p.dataSource = self;
    p.delegate = self;
    self.pview = p;
    [self addSubview:p];
}

- (void)setSelectAtIndex:(NSInteger)index{
    [self.pview selectRow:index inComponent:0 animated:NO];
}



- (void)dismiss:(UIBarButtonItem *)item{
    [self dismiss];
    if ([self.delegate respondsToSelector:@selector(pickerDidSelected:)]) {
        [self.delegate pickerDidSelected:[self.pview selectedRowInComponent:0]];
    }
}

- (void)dismiss{
    
    [self.maskview removeFromSuperview];
    
    [UIView animateWithDuration:0.2f
                          delay:0.f
                        options:UIViewAnimationOptionCurveEaseOut
                     animations:^{

                         self.frame = CGRectMake(0, self.frame.origin.y + self.frame.size.height, self.frame.size.width, self.frame.size.height);
                     }
                     completion:^(BOOL finished){
                         [self.maskview removeFromSuperview];
                         [self removeFromSuperview];
                     }];
}

- (void)show{
    [_view addSubview:self.maskview];
    [_view addSubview:self];
    [UIView animateWithDuration:0.2f
                          delay:0.f
                        options:UIViewAnimationOptionCurveEaseOut
                     animations:^{
                         
                         self.frame = CGRectMake(0, _view.bounds.size.height - self.bounds.size.height, self.frame.size.width, self.frame.size.height);
                     }
                     completion:^(BOOL finished){
                         
                     }];
}

#pragma mark - UIPickerViewDataSource
- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView{
    return 1;
}

// returns the # of rows in each component..
- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component{
//    return 20;
    return [self.delegate pickerNumber:self];
}

- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component
{
//    return @(row + 1).stringValue;
    return [self.delegate pickerStringAtIndex:row];
}

#pragma mark - UIPickerViewDelegate
- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component{
}

@end;