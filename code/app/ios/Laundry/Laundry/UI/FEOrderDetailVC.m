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
#import "FEOrderCancelRequest.h"
#import "FEOrderOperationResponse.h"
#import "FEPayOnlineVC.h"
#import "FEOrder.h"
#import "FEOrderDeleteRequest.h"
#import <SDWebImage/UIImageView+WebCache.h>

@interface FEOrderDetailVC (){
    NSDictionary *_pngDic;
}

@property (nonatomic, strong) NSMutableArray *orderDetail;
@property (strong, nonatomic) IBOutlet UIBarButtonItem *operationBarItem;


@end

@implementation FEOrderDetailVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    if ([self.order.order_status isEqualToString:@"已下单"]) {
        self.operationBarItem.title = @"取消";
    }else if ([self.order.order_status isEqualToString:@"待付款"]){
        self.operationBarItem.title = @"付款";
    }else if([self.order.order_status isEqualToString:@"已取消"]){
        self.operationBarItem.title = @"删除";
    }
    
    self.title = kString(@"我的订单");
    
    NSArray *orderInfo = @[@{@"订单号":self.order.order_code},@{@"订单状态":self.order.order_status},@{@"订单时间":self.order.create_time}];
    
    NSArray *orderArray = @[@{@"取衣地址":self.order.take_addr?self.order.take_addr:@""},@{@"送回地址":self.order.delivery_addr?self.order.delivery_addr:@""},@{@"联系人":self.order.contact_name?self.order.contact_name:@""},@{@"联系电话":self.order.phone?self.order.phone:@""}];
    _pngDic = @{@"取衣地址":@"icon_addr_home",@"送回地址":@"icon_addr_out",@"联系人":@"icon_contact_name",@"联系电话":@"icon_contact_phone",@"订单号":@"icon_order_num",@"订单状态":@"icon_order_status",@"订单时间":@"icon_order_time",@"总价":@"icon_order_sum",@"付款方式":@"icon_pay_way",@"备注":@"icon_customer_note"};
    
    NSArray *orderPrice = @[@{@"总价":[NSString stringWithFormat:@"%.2f",self.order.total_price.floatValue]},@{@"付款方式":self.order.pay_option?self.order.pay_option:@""},@{@"备注":@""}];
    
    _orderDetail = [[NSMutableArray alloc] init];
    [_orderDetail addObject:orderInfo];
    [_orderDetail addObject:orderArray];
    [_orderDetail addObject:orderPrice];
    [self requestOrderDetail];
    
}

-(void)requestOrderDetail{
    FEOrderDetailRequest *rdata = [[FEOrderDetailRequest alloc] initWithOrderID:self.order.order_id];
    __weak typeof(self) weakself = self;
    [[FELaundryWebService sharedInstance] request:rdata responseClass:[FEOrderDetailResponse class] response:^(NSError *error, id response) {
        FEOrderDetailResponse *rsp = response;
        if (!error && rsp.errorCode.integerValue == 0) {
            if (rsp.obj) {
                [weakself.orderDetail addObject:rsp.obj];
                [weakself.tableView reloadData];
            }
        }
    }];
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"orderInfoCell" forIndexPath:indexPath];
    id item = self.orderDetail[indexPath.section][indexPath.row];
    UILabel *title = (UILabel *)[cell viewWithTag:1];
    UILabel *detail = (UILabel *)[cell viewWithTag:2];
    UIImageView *imageV = (UIImageView *)[cell viewWithTag:3];
    if ([item isKindOfClass:[NSDictionary class]]) {
        NSDictionary *detailItem = item;
        NSString *key = [detailItem.allKeys firstObject];
        title.text = key;
        detail.text = detailItem[key];
//        cell.imageView.image = [UIImage imageNamed:_pngDic[key]];
        imageV.image = [UIImage imageNamed:_pngDic[key]];
    }else if ([item isKindOfClass:[FEOrderDetail class]]){
        FEOrderDetail *detailItem = item;
        title.text = detailItem.product_name;
        detail.text = detailItem.current_price;
        [imageV sd_setImageWithURL:[NSURL URLWithString:kImageURL(detailItem.product_img)]];
    }
    
    return cell;
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return self.orderDetail.count;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [self.orderDetail[section] count];
}

- (IBAction)operation:(id)sender {
    __weak typeof(self) weakself = self;
    
    if ([self.order.order_status isEqualToString:@"已下单"]) {
        [self displayHUD:@"取消中..."];
        FEOrderCancelRequest *rdata = [[FEOrderCancelRequest alloc] initWithOrderID:self.order.order_id];
        [[FELaundryWebService sharedInstance] request:rdata responseClass:[FEOrderOperationResponse class] response:^(NSError *error, id response) {
            FEOrderOperationResponse *rsp = response;
            if (!error && rsp.errorCode.integerValue == 0) {
                weakself.order.order_status = @"已取消";
                weakself.operationBarItem.title = @"删除";
                if ([weakself.delegate respondsToSelector:@selector(orderShouldRefresh:)]) {
                    [weakself.delegate orderShouldRefresh:self.order];
                }
            }
            [weakself hideHUD:YES];
        }];
    }else if ([self.order.order_status isEqualToString:@"待付款"]){
        [self performSegueWithIdentifier:@"payOnlineSegue" sender:self.order];
    }else if([self.order.order_status isEqualToString:@"已取消"]){
        [self displayHUD:@"删除中..."];
        FEOrderDeleteRequest *rdata = [[FEOrderDeleteRequest alloc] initWithOrderID:self.order.order_id];
        [[FELaundryWebService sharedInstance] request:rdata responseClass:[FEOrderOperationResponse class] response:^(NSError *error, id response) {
            FEOrderOperationResponse *rsp = response;
            if (!error && rsp.errorCode.integerValue == 0) {
                
                if ([weakself.delegate respondsToSelector:@selector(orderDidDelete:)]) {
                    [weakself.delegate orderDidDelete:self.order];
                }
                
                [weakself.navigationController popViewControllerAnimated:YES];
            }
            [weakself hideHUD:YES];
        }];
    }
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if ([sender isKindOfClass:[FEOrder class]]) {
        FEPayOnlineVC *vc = segue.destinationViewController;
        vc.order = sender;
        vc.isFromOrder = YES;
    }
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
