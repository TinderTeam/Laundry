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

@interface FEOrderSubmitVC ()
@property (strong, nonatomic) IBOutlet UILabel *attachLabel;
@property (strong, nonatomic) IBOutlet UILabel *payTypeLabel;
@property (strong, nonatomic) IBOutlet UILabel *totalValueLabel;
@property (strong, nonatomic) IBOutlet UILabel *phoneLabel;
@property (strong, nonatomic) IBOutlet UILabel *contactLabel;
@property (strong, nonatomic) IBOutlet UILabel *backAddress;
@property (strong, nonatomic) IBOutlet UILabel *getAdressLabel;
@property (strong, nonatomic) FEOrderInfo *oinfo;
@property (assign, nonatomic) float totalPrice;
@property (assign, nonatomic) NSInteger totalNumber;

@end

@implementation FEOrderSubmitVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = kString(@"订单");
    _oinfo = [FEOrderInfo new];
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
    FEModifyInfoVC *mvc = segue.destinationViewController;
    mvc.orderInfo = self.oinfo;
    
}

-(void)refreshUI{
    self.payTypeLabel.text = kString(@"送衣付款");
    self.phoneLabel.text = self.oinfo.phone;
    self.contactLabel.text = self.oinfo.contact_name;
    self.getAdressLabel.text = self.oinfo.take_addr;
    self.backAddress.text = self.oinfo.delivery_addr;
    self.totalValueLabel.text = [NSString stringWithFormat:@"%.2f",self.totalPrice];
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
        NSInteger number = [[FEDataCache sharedInstance] productNumber:product].integerValue;
        for (int i= 0; i < number; i++) {
            FEOrderDetail *detail = [[FEOrderDetail alloc] initWithDictionary:@{@"product_id":product.product_id}];
            [marray addObject:detail];
        }
    }
    
    FEUser *user = [[FEUser alloc] initWithDictionary:kLoginUser];
    NSMutableDictionary *dic = [NSMutableDictionary dictionaryWithDictionary:self.oinfo.dictionary];
    [dic setObject:user.user_id forKey:@"user_id"];
    [dic setObject:@(self.totalPrice) forKey:@"total_price"];
    [dic setObject:@(self.totalNumber) forKey:@"total_count"];
    
    FEOrder *order = [[FEOrder alloc] initWithDictionary:dic];
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

#pragma mark - UITableViewDelegate
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
//    if (indexPath.section == 0 && indexPath.row == 0) {
//        
//    }
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
