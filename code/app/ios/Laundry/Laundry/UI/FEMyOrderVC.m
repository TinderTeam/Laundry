//
//  FEMyOrderVC.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEMyOrderVC.h"
#import "FELaundryWebService.h"
#import "FEOrderRequest.h"
#import "FEOrderListResponse.h"
#import "FEUser.h"
#import "FEOrderDetail.h"
#import "FEOrderDetailVC.h"

@interface FEMyOrderVC ()
@property (nonatomic, strong) NSArray *orderList;

@end

@implementation FEMyOrderVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = kString(@"我的订单");
    [self requestOrder];
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:NO animated:YES];
}

-(void)requestOrder{
    __weak typeof(self) weakself = self;
    FEUser *user = [[FEUser alloc] initWithDictionary:kLoginUser];
    [[FELaundryWebService sharedInstance] request:[[FEOrderRequest alloc] initWithUserID:user.user_id] responseClass:[FEOrderListResponse class] response:^(NSError *error, id response) {
        FEOrderListResponse *rsp = response;
        if (!error && rsp.errorCode.integerValue == 0) {
            weakself.orderList = rsp.obj;
            [weakself.tableView reloadData];
        }
    }];
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if ([sender isKindOfClass:[UITableViewCell class]]) {
        NSIndexPath *indexPath = [self.tableView indexPathForCell:sender];
        FEOrder *order = self.orderList[indexPath.row];
        FEOrderDetailVC *vc = segue.destinationViewController;
        vc.order = order;
    }
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    FEOrder *order = self.orderList[indexPath.row];
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"orderItemCell" forIndexPath:indexPath];
    UILabel *statusLabel = (UILabel *)[cell viewWithTag:1];
    UILabel *priceLabel = (UILabel *)[cell viewWithTag:2];
    UILabel *orderIDLabel = (UILabel *)[cell viewWithTag:3];
    UILabel *orderTimeLabel = (UILabel *)[cell viewWithTag:4];
    statusLabel.text = order.order_status;
    
    priceLabel.text = [NSString stringWithFormat:@"%@ %.2f",kString(@"总价: "),order.total_price.floatValue];
    orderIDLabel.text = order.order_code;
    orderTimeLabel.text = order.create_time;
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.orderList.count;
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
