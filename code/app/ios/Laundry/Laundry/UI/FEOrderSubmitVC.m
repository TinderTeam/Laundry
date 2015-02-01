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

#define __KEY_PAY_NAME @"name"
#define __KEY_PAY_TYPE @"type"

@interface FEOrderSubmitVC ()<UITableViewDataSource,UITableViewDelegate,FEPickerViewDelegate,UIAlertViewDelegate>{
    NSArray *_dataSource;
}
@property (strong, nonatomic) IBOutlet UILabel *totalValueLabel;
@property (strong, nonatomic) FEOrderInfo *oinfo;
@property (assign, nonatomic) float totalPrice;
@property (assign, nonatomic) NSInteger totalNumber;
@property (strong, nonatomic) NSArray *payType;
//@property (nonatomic, strong) NSDictionary *
@property (strong, nonatomic) IBOutlet UITableView *tableView;

@end

@implementation FEOrderSubmitVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = kString(@"订单");
    _oinfo = [FEOrderInfo new];
    _dataSource = @[@"取衣地址",@"送回地址",@"联系人",@"联系电话",@"付款方式",@"您的要求"];
    _payType = @[@{__KEY_PAY_NAME:@"在线付款",__KEY_NUMBER:@(1)},@{__KEY_PAY_NAME:@"取衣付款",__KEY_PAY_TYPE:@(2)}];
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
    FEInfoInputVC *mvc = segue.destinationViewController;
    mvc.orderInfo = self.oinfo;
    NSString *title = _dataSource[((NSIndexPath *)sender).row];
    mvc.typeName = title;
}

-(void)refreshUI{
//    self.payTypeLabel.text = kString(@"送衣付款");
//    self.phoneLabel.text = self.oinfo.phone;
//    self.contactLabel.text = self.oinfo.contact_name;
//    self.getAdressLabel.text = self.oinfo.take_addr;
//    self.backAddress.text = self.oinfo.delivery_addr;
    [self.tableView reloadData];
    self.totalValueLabel.text = [NSString stringWithFormat:@"总量:%ld,共计:%.2f",(long)self.totalNumber,self.totalPrice];
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
        [marray addObject:detail];
        
    }
    
    FEUser *user = [[FEUser alloc] initWithDictionary:kLoginUser];
    NSMutableDictionary *dic = [NSMutableDictionary dictionaryWithDictionary:self.oinfo.dictionary];
    [dic setObject:user.user_id forKey:@"user_id"];
    [dic setObject:@(self.totalPrice) forKey:@"total_price"];
    [dic setObject:@(self.totalNumber) forKey:@"total_count"];
    [dic setObject:@"正常下单" forKey:@"order_type"];
    
    FEOrder *order = [[FEOrder alloc] initWithDictionary:dic];
    
    [[FELaundryWebService sharedInstance] request:[[FEOrderCreateRequest alloc] initWithOrder:order orderDetails:marray] responseClass:[FECreateOrderResponse class] response:^(NSError *error, id response) {
        FECreateOrderResponse *rsp = response;
        if (!error && rsp.errorCode.integerValue == 0) {
            NSLog(@"order success!");
//
            [[FEDataCache sharedInstance] clearSelectProduct];
            if ([weakself.oinfo.payType isEqualToString:@"在线支付"]) {
                [[FEAlipay sharedInstance] payWithOrder:rsp.obj complete:^(NSDictionary *result) {
                    if ([result[@"resultStatus"] integerValue] == 9000) {
                        [weakself toOrder];
                    }else{
                        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"提示" message:@"未支付成功" delegate:weakself cancelButtonTitle:@"确定" otherButtonTitles: nil];
                        [alert show];
                    }
                }];
            }else{
                [weakself toOrder];
            }
        }
    }];
}


-(void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex{
    [self toOrder];
}


-(void)toOrder{
    UIViewController *controller = [[[self.tabBarController viewControllers] objectAtIndex:2] topViewController];
    if ([controller isKindOfClass:[FEProfileVC class]]) {
        [self.tabBarController setSelectedIndex:2];
        [self.navigationController popViewControllerAnimated:NO];
        [((FEProfileVC *)controller) automaticToOrder];
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
        cell.titleLabel.text = self.oinfo.payType;
    }else if([identifier isEqualToString:@"您的要求"]){
        cell.titleLabel.text = self.oinfo.remark;
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
        FEPickerView *pick = [[FEPickerView alloc] initFromView:[[AppDelegate sharedDelegate].window viewWithTag:0]];
        pick.delegate = self;
        [pick show];
    }else{
        [self performSegueWithIdentifier:@"inputSegue" sender:indexPath];
    }
    
}

#pragma mark - FEPickerViewDelegate
-(void)pickerDidSelected:(NSInteger)number{
//    self.fatherID = self.categoryList[number][__KEY_NUMBER];
//    [self reloadCateGory];
    self.oinfo.payType = self.payType[number][__KEY_PAY_NAME];
    [self.tableView reloadData];
}

-(NSInteger)pickerNumber:(FEPickerView *)picker{
    return self.payType.count;
}
-(NSString *)pickerStringAtIndex:(NSInteger)index{
    return self.payType[index][__KEY_PAY_NAME];
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
