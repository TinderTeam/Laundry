//
//  FEMeShouldSigninVC.m
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEMeShouldSigninVC.h"

@interface FEMeShouldSigninVC ()

@end

@implementation FEMeShouldSigninVC

-(id)initWithCoder:(NSCoder *)aDecoder{
    self = [super initWithCoder:aDecoder];
    if (self) {
        self.title = kString(@"个人中心");
        UITabBarItem *tabitem = [[UITabBarItem alloc] initWithTitle:kString(@"个人中心") image:[UIImage imageNamed:@"tab_icon_user_normal"] selectedImage:[UIImage imageNamed:@"tab_icon_user_pressed"]];
        self.tabBarItem = tabitem;
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
//    if (kLoginUser) {
//        self.navigationController.topViewController
//    }
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
