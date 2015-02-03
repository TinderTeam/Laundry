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
#import "FEModifyInfoVC.h"
#import "FEOrderInfo.h"
#import "FEUser.h"
#import "FEDataCache.h"
#import "FEAlipay.h"
#import "FEOrderInfoItemCell.h"
#import "FEInfoInputVC.h"
#import "FEPickerView.h"
#import "AppDelegate.h"
#import "FECreateOrderResponse.h"
#import "FEPopPickerView.h"
#import "FEPayOnlineVC.h"
#import "GAAlertObj.h"
#import "FEBasketVC.h"
#import "FEProfileVC.h"

#define __KEY_PAY_NAME @"name"
#define __KEY_PAY_TYPE @"type"

@interface FEOrderSubmitVC ()<UITableViewDataSource,UITableViewDelegate,UIAlertViewDelegate,FEPopPickerViewDataSource>{
    NSArray *_dataSource;
    BOOL _canSelectPayType;
}
@property (strong, nonatomic) IBOutlet UILabel *totalValueLabel;
@property (strong, nonatomic) FEOrderInfo *oinfo;
@property (assign, nonatomic) float totalPrice;
@property (assign, nonatomic) NSInteger totalNumber;
@property (strong, nonatomic) NSArray *payType;
//@property (nonatomic, strong) NSDictionary *
@property (strong, nonatomic) IBOutlet UITableView *tableView;
@property (strong, nonatomic) FEOrder *currentOrder;

@end

@implementation FEOrderSubmitVC

-(id)initWithCoder:(NSCoder *)aDecoder{
    self = [super initWithCoder:aDecoder];
    if (self) {
        _orderType = @"正常下单";
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = kString(@"提交订单");
    _oinfo = [FEOrderInfo new];
    _dataSource = @[@"取衣地址",@"送回地址",@"联系人",@"联系电话",@"付款方式",@"您的要求"];
    _payType = @[@{__KEY_PAY_NAME:@"在线支付",__KEY_NUMBER:@(1)},@{__KEY_PAY_NAME:@"送衣付款",__KEY_PAY_TYPE:@(2)}];
    FEUser *user = [[FEUser alloc] initWithDictionary:kLoginUser];
    _oinfo.phone = user.user_name;
    self.totalPrice = 0;
    self.totalNumber = 0;
     NSArray *products = [FEDataCache sharedInstance].selectProducts;
    for (FEProduct *product in products) {
        NSInteger number = [[FEDataCache sharedInstance] productNumber:product].integerValue;
        self.totalNumber += number;
        self.totalPrice += product.price.floatValue * number;
    }
    [self refreshUI];
    
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewDidAppear:animated];
    [self refreshUI];
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if ([segue.identifier isEqualToString:@"payOnlineSegue"]) {
        FEPayOnlineVC *vc = segue.destinationViewController;
        vc.order = self.currentOrder;
    }else{
        FEInfoInputVC *mvc = segue.destinationViewController;
        mvc.orderInfo = self.oinfo;
        NSString *title = _dataSource[((NSIndexPath *)sender).row];
        mvc.typeName = title;
    }
    
}

-(void)refreshUI{
    [self.tableView reloadData];
    if ([self.orderType isEqualToString:@"直接下单"]) {
        self.totalValueLabel.text = [NSString stringWithFormat:@"总量:0,共计:面议"];
        self.oinfo.pay_option = @"送衣付款";
        _canSelectPayType = NO;
        return;
    }
    NSArray *products = [FEDataCache sharedInstance].selectProducts;
    NSArray *noPrice = [products filteredArrayUsingPredicate:[NSPredicate predicateWithFormat:@"SELF.price_type == %@",@"面议"]];
    if (noPrice.count) {
        self.totalValueLabel.text = [NSString stringWithFormat:@"总量:%ld,共计:面议",(long)self.totalNumber];
        self.oinfo.pay_option = @"送衣付款";
        _canSelectPayType = NO;
    }else{
        self.totalValueLabel.text = [NSString stringWithFormat:@"总量:%ld,共计:%.2f",(long)self.totalNumber,self.totalPrice];
        self.oinfo.pay_option = @"在线支付";
        _canSelectPayType = YES;
    }
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)submitOrder:(id)sender {
    
    __weak typeof(self) weakself = self;
    [self displayHUD:@"提交中..."];
    NSArray *products = [FEDataCache sharedInstance].selectProducts;
    NSMutableArray *marray = [NSMutableArray new];
    for (FEProduct *product in products) {
        NSInteger number = [[FEDataCache sharedInstance] productNumber:product].integerValue;
        
        FEOrderDetail *detail = [[FEOrderDetail alloc] init];
        detail.quantity = @(number);
        detail.product_name = product.product_name;
        detail.product_type = product.type_id.stringValue;
        detail.current_price = product.price;
        detail.original_price = product.original_price;
        detail.product_img = product.img;
        detail.product_status = product.product_status;
        detail.product_update_time = product.update_time;
        detail.product_limit_time = product.end_time;
        detail.price_type = product.price_type;
        [marray addObject:detail];
        
    }
    BOOL isDorder = NO;
    FEUser *user = [[FEUser alloc] initWithDictionary:kLoginUser];
    
    NSMutableDictionary *dic = [NSMutableDictionary dictionaryWithDictionary:self.oinfo.dictionary];
    FEOrder *order = [[FEOrder alloc] initWithDictionary:dic];
    order.user_id = user.user_id;
    if ([self.orderType isEqualToString:@"直接下单"]) {
//        order.total_price = @(self.totalPrice);
//        order.total_count = @(self.totalNumber);
        order.price_type = @"面议";
        isDorder = YES;
    }else{
        order.total_price = @(self.totalPrice);
        order.total_count = @(self.totalNumber);
        if (_canSelectPayType) {
            order.price_type = @"固定";
        }else{
            order.price_type = @"面议";
        }
    }
    
    order.order_type = self.orderType;
    order.phone = self.oinfo.phone;
    
    
    [[FELaundryWebService sharedInstance] request:[[FEOrderCreateRequest alloc] initWithOrder:order orderDetails:isDorder?nil:marray] responseClass:[FECreateOrderResponse class] response:^(NSError *error, id response) {
        FECreateOrderResponse *rsp = response;
        if (!error && rsp.errorCode.integerValue == 0) {
            NSLog(@"order success!");
//
            if (!isDorder) {
                [[FEDataCache sharedInstance] clearSelectProduct];
            }
            weakself.currentOrder = rsp.obj;
            
            if ([weakself.oinfo.pay_option isEqualToString:@"在线支付"]) {
                [weakself performSegueWithIdentifier:@"payOnlineSegue" sender:self];
            }else{
                [weakself toOrder];
            }
        }else if (rsp.errorCode.integerValue == 10){
//            kUserDefaultsRemoveForKey(kLoginUserKey);
//            kUserDefaultsRemoveForKey(kCustomerKey);
//            kUserDefaultsSync;
//            [FEDataCache sharedInstance].user = nil;
//            [FEDataCache sharedInstance].customer = nil;
            [weakself toLogin];
//            [weakself.navigationController popToRootViewControllerAnimated:YES];
        }
        [weakself hideHUD:YES];
    }];
}

-(void)toLogin{
    UIViewController *controller = [[[self.tabBarController viewControllers] objectAtIndex:2] topViewController];
    if ([controller isKindOfClass:[FEProfileVC class]]) {
//        [self.tabBarController setSelectedIndex:2];
        FEProfileVC *vc = (FEProfileVC *)controller;
        [vc signin];
    }
}


-(void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex{
    [self toOrder];
}


-(void)toOrder{
    
    UIViewController *controller = [[[self.tabBarController viewControllers] objectAtIndex:2] topViewController];
    if ([controller isKindOfClass:[FEProfileVC class]]) {
        [self.tabBarController setSelectedIndex:2];
        [((FEProfileVC *)controller) automaticToOrder];
        [self.navigationController popToRootViewControllerAnimated:NO];
    }
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    FEOrderInfoItemCell *cell = [tableView dequeueReusableCellWithIdentifier:@"orderInfoItemCell" forIndexPath:indexPath];
    [cell.titleButton setTitle:_dataSource[indexPath.row] forState:UIControlStateNormal];
    NSString *identifier = _dataSource[indexPath.row];
    if ([identifier isEqualToString:@"取衣地址"]) {
        cell.titleLabel.text = self.oinfo.take_addr.length?self.oinfo.take_addr:@"未设置";
    }else if([identifier isEqualToString:@"送回地址"]){
        cell.titleLabel.text = self.oinfo.delivery_addr.length?self.oinfo.delivery_addr:@"未设置";
    }else if([identifier isEqualToString:@"联系人"]){
        cell.titleLabel.text = self.oinfo.contact_name.length?self.oinfo.contact_name:@"未设置";
    }else if([identifier isEqualToString:@"联系电话"]){
        cell.titleLabel.text = self.oinfo.phone.length?self.oinfo.phone:@"未设置";
    }else if([identifier isEqualToString:@"付款方式"]){
        cell.titleLabel.text = self.oinfo.pay_option;
    }else if([identifier isEqualToString:@"您的要求"]){
        cell.titleLabel.text = self.oinfo.order_note;
    }
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return 6;
}

#pragma mark - UITableViewDelegate
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
//    if (indexPath.section == 0 && indexPath.row == 0) {
//        
//    }
    NSString *title = _dataSource[indexPath.row];
    if ([title isEqualToString:@"付款方式"]) {
        if (_canSelectPayType) {
            FEPopPickerView *pick = [[FEPopPickerView alloc] initFromView:[[AppDelegate sharedDelegate].window viewWithTag:0]];
            pick.tlabel.text = @"付款方式";
            NSInteger index = 0;
            for (NSDictionary *item in self.payType) {
                if ([item[__KEY_PAY_NAME] isEqualToString:self.oinfo.pay_option]) {
                    index = [self.payType indexOfObject:item];
                    break;
                }
            }
            pick.selectIndex = index;
            pick.dataSource = self;
            [pick show];
        }else{
            GAAlertAction *action = [GAAlertAction actionWithTitle:@"确定" action:^{
                
            }];
            [GAAlertObj showAlertWithTitle:@"提示" message:@"面议不能选择付款方式" actions:@[action] inViewController:self];
        }
        
    }else{
        [self performSegueWithIdentifier:@"inputSegue" sender:indexPath];
    }
    
}

#pragma mark - FEPopPickerViewDataSource
-(NSInteger)numberInPicker:(FEPopPickerView *)picker{
    return self.payType.count;
}
-(NSString *)picker:(FEPopPickerView *)picker titleAtIndex:(NSInteger)index{
    return self.payType[index][__KEY_PAY_NAME];
}


-(void)picker:(FEPopPickerView *)picker didSelectAtIndex:(NSInteger)index{
    self.oinfo.pay_option = self.payType[index][__KEY_PAY_NAME];
    [self.tableView reloadData];
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
