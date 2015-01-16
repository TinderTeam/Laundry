//
//  FEOrderSubmitVC.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEOrderSubmitVC.h"
#import "FEOrderCreateRequest.h"
#import "FEOrderCreateResponse.h"
#import "FELaundryWebService.h"
#import "FEDataCache.h"
#import "FEUser.h"
#import "FEProfileVC.h"

@interface FEOrderSubmitVC ()

@end

@implementation FEOrderSubmitVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = kString(@"订单");
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)submitOrder:(id)sender {
    __weak typeof(self) weakself = self;
    NSArray *products = [FEDataCache sharedInstance].selectProducts;
    NSMutableArray *marray = [NSMutableArray new];
    for (FEProduct *product in products) {
        FEOrderDetail *detail = [[FEOrderDetail alloc] initWithDictionary:@{@"product_id":product.product_id}];
        [marray addObject:detail];
    }
    
    float totalPrice = 0;
    NSInteger totalNumber = 0;
    for (FEProduct *product in products) {
        NSInteger number = [[FEDataCache sharedInstance] productNumber:product].integerValue;
        totalNumber += number;
        totalPrice += product.price.floatValue * number;
    }
    
    FEUser *user = [[FEUser alloc] initWithDictionary:kLoginUser];
    FEOrder *order = [[FEOrder alloc] initWithDictionary:@{@"user_id":user.user_id,@"total_price":@(totalPrice),@"total_count":@(totalNumber)}];
    [[FELaundryWebService sharedInstance] request:[[FEOrderCreateRequest alloc] initWithOrder:order orderDetails:marray] responseClass:[FEBaseResponse class] response:^(NSError *error, id response) {
        FEBaseResponse *rsp = response;
        if (!error && rsp.errorCode.integerValue == 0) {
            NSLog(@"order success!");
            [weakself toOrder];
            [[FEDataCache sharedInstance] clearSelectProduct];
        }
    }];
    
}

-(void)toOrder{
    UIViewController *controller = [[[self.tabBarController viewControllers] objectAtIndex:2] topViewController];
    if ([controller isKindOfClass:[FEProfileVC class]]) {
        [self.tabBarController setSelectedIndex:2];
        [self.navigationController popViewControllerAnimated:NO];
        [((FEProfileVC *)controller) automaticToOrder];
    }
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
