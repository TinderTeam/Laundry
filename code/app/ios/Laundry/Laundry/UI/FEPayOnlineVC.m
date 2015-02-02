//
//  FEPayOnlineVC.m
//  Laundry
//
//  Created by Seven on 15-2-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEPayOnlineVC.h"
#import "FEOrder.h"
#import "FEButton.h"
#import "FEDataCache.h"
#import "FEAlipay.h"
#import "UIImage+LogN.h"
#import "FEProfileVC.h"

@interface FEPayOnlineVC ()
@property (strong, nonatomic) IBOutlet UILabel *titleLabel;
@property (strong, nonatomic) IBOutlet UILabel *descLabel;
@property (strong, nonatomic) IBOutlet UILabel *priceLabel;
@property (strong, nonatomic) IBOutlet FEButton *payButton;

@end

@implementation FEPayOnlineVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self.payButton setBackgroundImage:[UIImage imageFromColor:kThemeGrayColor] forState:UIControlStateNormal];
    self.titleLabel.text = @"洗衣";
    self.descLabel.text = @"洗衣在线支付";
    self.priceLabel.text = [NSString stringWithFormat:@"%@",self.order.total_price];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (IBAction)back:(id)sender {
    [self toOrder];
}

- (IBAction)payOnline:(id)sender {
    __weak typeof(self) weakself = self;
    [[FEAlipay sharedInstance] payWithOrder:self.order complete:^(NSDictionary *result) {
        if ([result[@"resultStatus"] integerValue] == 9000) {
            [weakself toOrder];
        }else{
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"提示" message:@"未支付成功" delegate:weakself cancelButtonTitle:@"确定" otherButtonTitles: nil];
            [alert show];
        }
    }];
}

-(void)toOrder{
    
    UIViewController *controller = [[[self.tabBarController viewControllers] objectAtIndex:2] topViewController];
    if ([controller isKindOfClass:[FEProfileVC class]]) {
        [self.tabBarController setSelectedIndex:2];
        [((FEProfileVC *)controller) automaticToOrder];
        [self.navigationController popToRootViewControllerAnimated:NO];
    }
}

-(void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex{
    [self toOrder];
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
