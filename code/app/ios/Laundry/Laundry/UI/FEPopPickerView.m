//
//  FEPopPickerView.m
//  Laundry
//
//  Created by Seven on 15-2-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEPopPickerView.h"


@interface FEPopPickerView ()<UITableViewDataSource,UITableViewDelegate>{
    CGFloat _maxHeight;
}

@property (nonatomic, strong) UITableView *tableView;
@property (nonatomic, strong) UIView *tview;

@property (nonatomic, strong) UIControl *maskview;
@property (nonatomic, weak) UIView *pview;

@end

@implementation FEPopPickerView

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

- (id)initFromView:(UIView *)view
{
    self = [super initWithFrame:CGRectMake(0,0,20,20)];
    if (self) {
        // Initialization code
        self.backgroundColor = [UIColor whiteColor];
        self.center = CGPointMake(view.bounds.size.width / 2, view.bounds.size.height / 2);
        UIControl *mask = [[UIControl alloc] initWithFrame:view.bounds];
        mask.backgroundColor = [UIColor blackColor];
        mask.alpha = 0.5;
        [mask addTarget:self action:@selector(dismiss) forControlEvents:UIControlEventTouchUpInside];
        self.maskview = mask;
        self.pview = view;
        
        [self setUP];
        _maxHeight = self.pview.bounds.size.height - 150;
        
    }
    return self;
}


-(void)setUP{
    UIView *tview = [[UIView alloc] initWithFrame:CGRectMake(0, 0, self.bounds.size.width, 40)];
    tview.backgroundColor = kThemeColor;
    [self addSubview:tview];
    self.tview = tview;
    
    UILabel *titleLabel = [[UILabel alloc] initWithFrame:tview.bounds];
    titleLabel.textColor = [UIColor whiteColor];
    titleLabel.text = @"   分类";
    [tview addSubview:titleLabel];
    self.tlabel = titleLabel;
    
    UITableView *tableView = [[UITableView alloc] initWithFrame:CGRectMake(0, tview.bounds.size.height, self.bounds.size.width, self.bounds.size.height - tview.bounds.size.height)];
    tableView.dataSource = self;
    tableView.delegate = self;
    [self addSubview:tableView];
    self.tableView = tableView;
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static NSString *identifier = @"cell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    if (!cell) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.accessoryType = UITableViewCellAccessoryCheckmark;
    }
    
    if (self.selectIndex == indexPath.row) {
        cell.accessoryType = UITableViewCellAccessoryCheckmark;
    }else{
        cell.accessoryType = UITableViewCellAccessoryNone;
    }
    
    cell.textLabel.text = [self.dataSource picker:self titleAtIndex:indexPath.row];
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [self.dataSource numberInPicker:self];
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    if ([self.dataSource respondsToSelector:@selector(picker:didSelectAtIndex:)]) {
        [self.dataSource picker:self didSelectAtIndex:indexPath.row];
    }
    [self dismiss];
}


- (void)dismiss:(UIBarButtonItem *)item{
    [self dismiss];
}

- (void)dismiss{
    
    [UIView animateWithDuration:0.2f
                          delay:0.f
                        options:UIViewAnimationOptionCurveEaseOut
                     animations:^{
                         
                     }
                     completion:^(BOOL finished){
                         [self.maskview removeFromSuperview];
                         [self removeFromSuperview];
                     }];
}

- (void)show{
    NSInteger number = [self.dataSource numberInPicker:self];
    CGFloat height = number * 44.0f + 40;
    if (height > _maxHeight) {
        height = _maxHeight;
    }
    [UIView animateWithDuration:0.2f
                          delay:0.f
                        options:UIViewAnimationOptionCurveEaseOut
                     animations:^{
                        [self.pview addSubview:self.maskview];
                         [self.pview addSubview:self];
                        self.frame = CGRectMake(30, (self.pview.bounds.size.height - height) / 2.0, self.pview.bounds.size.width - 60, height);
                     }
                     completion:^(BOOL finished){
                         
                     }];
}

-(void)layoutSubviews{
    self.tview.frame = CGRectMake(0, 0, self.bounds.size.width, 40);
    self.tlabel.frame = self.tview.bounds;
    self.tableView.frame = CGRectMake(0, self.tview.bounds.size.height, self.bounds.size.width, self.bounds.size.height - self.tview.bounds.size.height);
    
}


@end
