//
//  FEOrderDetailVC.m
//  Laundry
//
//  Created by Seven on 15-1-19.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEOrderDetailVC.h"
#import "FELaundryWebService.h"
#import "FEOrderDetailRequest.h"
#import "FEOrderDetailResponse.h"
#import "FEOrder.h"

@interface FEOrderDetailVC ()

@end

@implementation FEOrderDetailVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self requestOrderDetail];
}

-(void)requestOrderDetail{
    FEOrderDetailRequest *rdata = [[FEOrderDetailRequest alloc] initWithOrderID:self.order.order_id];

    [[FELaundryWebService sharedInstance] request:rdata responseClass:[FEOrderDetailResponse class] response:^(NSError *error, id response) {
        FEOrderDetailResponse *rsp = response;
        if (!error && rsp.errorCode.integerValue == 0) {
            
        }
    }];
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
