//
//  FEPickerView.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEPickerView.h"

#define _DefaultTime_ 3
#define _MineTime_    2
#define _DateFormatter_ @"yyyy/MM/dd HH:mm:00"

@interface FEPickerView ()<UIPickerViewDataSource,UIPickerViewDelegate>{
    __unsafe_unretained UIView *_view;
}
@property (nonatomic, strong) UIPickerView *pview;
@property (nonatomic, strong) UIToolbar *toolbar;
@property (nonatomic, strong) UIControl *maskview;

@end

@implementation FEPickerView

- (id)initFromView:(UIView *)view
{
    self = [super initWithFrame:CGRectMake(0, view.bounds.size.height, view.bounds.size.width, 260)];
    if (self) {
        // Initialization code
        _view = view;
//        _view = [[AppDelegate sharedDelegate].window viewWithTag:0];
        UIControl *mask = [[UIControl alloc] initWithFrame:_view.bounds];
        [mask addTarget:self action:@selector(dismiss) forControlEvents:UIControlEventTouchUpInside];
        self.maskview = mask;
        mask.backgroundColor = [UIColor blackColor];
        mask.alpha = 0.5;
        
        self.backgroundColor = [UIColor whiteColor];
        UIToolbar *toolbar = [[UIToolbar alloc]initWithFrame:CGRectMake(0, 0, self.bounds.size.width, 44)];
        self.toolbar = toolbar;
        
        UIBarButtonItem * item0 = [[UIBarButtonItem alloc] initWithTitle:kString(@"取消") style:UIBarButtonItemStyleDone target:self action:@selector(dismiss:)];
        item0.tag = 1;
        
        UIBarButtonItem * item2 = [[UIBarButtonItem alloc] initWithTitle:kString(@"确定") style:UIBarButtonItemStyleDone target:self action:@selector(dismiss:)];
        item2.tag = 2;
        UIBarButtonItem * spaceItem1 = [[UIBarButtonItem alloc] initWithTitle:nil style:UIBarButtonItemStylePlain target:nil action:nil];
        UIBarButtonItem * spaceItem2 = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemFlexibleSpace target:nil action:nil];
        UIBarButtonItem * spaceItem3 = [[UIBarButtonItem alloc] initWithTitle:nil style:UIBarButtonItemStylePlain target:nil action:nil];
        toolbar.items = [NSArray arrayWithObjects:spaceItem1,item0,spaceItem2,item2,spaceItem3, nil];
        
        [self addSubview:toolbar];
        [self initWithPickerView];
    }
    return self;
}


- (void)initWithPickerView{

    UIPickerView *p = [[UIPickerView alloc]initWithFrame:CGRectMake(0, self.toolbar.bounds.size.height, self.bounds.size.width, 216)];
    p.showsSelectionIndicator = YES;
    p.dataSource = self;
    p.delegate = self;
    self.pview = p;
    [self addSubview:p];
}


- (void)dismiss:(UIBarButtonItem *)item{
    [self dismiss];
    if (item.tag == 2) {
        if ([self.delegate respondsToSelector:@selector(pickerDidSelected:)]) {
            [self.delegate pickerDidSelected:[self.pview selectedRowInComponent:0]];
        }
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
                         [self.maskView removeFromSuperview];
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