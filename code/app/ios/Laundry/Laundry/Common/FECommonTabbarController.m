//
//  FECommonTabbarController.m
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECommonTabbarController.h"
#import "UIImage+LogN.h"
#import "UIImage+Resize.h"

@interface FECommonTabbarController ()

@end

@implementation FECommonTabbarController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.tabBar.backgroundImage = [[UIImage imageFromColor:kThemeColor] imageScaledToSize:CGSizeMake(self.view.bounds.size.width, 49)];//[[UIImage imageNamed:@"tabbar_background"] imageScaledToSize:CGSizeMake(self.view.bounds.size.width, 49)];
//    self.tabBar.tintColor = [UIColor colorWithHex:0x009cff];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
