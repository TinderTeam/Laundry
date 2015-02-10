//
//  FEProfileVC.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEProfileVC.h"
#import "FELaundryWebService.h"
#import "FESignoutRequest.h"
#import "FECustomSegue.h"
#import "FEUser.h"
#import "FEDataCache.h"

@interface FEProfileVC ()
@property (strong, nonatomic) IBOutlet UIView *headerView;
@property (strong, nonatomic) IBOutlet UIView *shouldSigninView;
@property (strong, nonatomic) UIView *header;
@property (strong, nonatomic) UIView *footer;
@property (strong, nonatomic) IBOutlet UILabel *nickNameLabel;
@property (strong, nonatomic) IBOutlet UILabel *sexLabel;
@property (strong, nonatomic) IBOutlet UILabel *phoneLabel;

@end

@implementation FEProfileVC

-(id)initWithCoder:(NSCoder *)aDecoder{
    self = [super initWithCoder:aDecoder];
    if (self) {
        self.title = kString(@"个人中心");
        UITabBarItem *tabitem = [[UITabBarItem alloc] initWithTitle:kString(@"个人中心") image:[[UIImage imageNamed:@"tab_icon_user_normal"] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal] selectedImage:[UIImage imageNamed:@"tab_icon_user_pressed"]];
        self.tabBarItem = tabitem;
        self.header = self.headerView;
        self.footer = self.shouldSigninView;
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self refreshUI];
    __weak typeof(self) weakself = self;
    [[NSNotificationCenter defaultCenter] addObserverForName:kNotificationUserDidLogin object:nil queue:[NSOperationQueue mainQueue] usingBlock:^(NSNotification *note) {
        [weakself refreshUI];
    }];
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    [self refreshUI];
    if (kLoginUser) {
//        [self.navigationController setNavigationBarHidden:YES animated:YES];
    }
}

-(void)automaticToOrder{
    [self performSegueWithIdentifier:@"orderListSegue" sender:self];
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if ([segue.identifier isEqualToString:@"orderListSegue"] && sender == self) {
//        segue.
        ((FECustomSegue *)segue).animation = NO;
    }
}

-(void)refreshUI{
    if (kLoginUser) {
        FECustomer *customer = [FEDataCache sharedInstance].customer;
        self.sexLabel.text = [NSString stringWithFormat:@"性别：%@",customer.customer_sex?customer.customer_sex:@""];
        self.nickNameLabel.text = [NSString stringWithFormat:@"姓名：%@",customer.customer_name?customer.customer_name:@""];
        self.tableView.scrollEnabled = YES;
        self.tableView.tableFooterView = [UIView new];
        self.tableView.tableHeaderView = self.headerView;
//        [self.navigationController setNavigationBarHidden:YES];
        FEUser *user = [[FEUser alloc] initWithDictionary:kLoginUser];
        self.phoneLabel.text = [NSString stringWithFormat:@"电话:%@",user.user_name];
        [self.tableView reloadData];
    }else{
        self.tableView.tableHeaderView = nil;
        self.tableView.tableFooterView = self.shouldSigninView;
        self.tableView.scrollEnabled = NO;
        self.phoneLabel.text = @"";
//        [self.navigationController setNavigationBarHidden:NO];
        [self.tableView reloadData];
    }
}

-(void)signin{
//    signinSegue
    [self performSegueWithIdentifier:@"signinSegue" sender:nil];
}

#pragma mark - UITableViewDataSource
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    if (kLoginUser) {
        return 3;
    }
    return 0;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.row == 2) {
        [self logout];
    }
}

-(void)logout{
    kUserDefaultsRemoveForKey(kLoginUserKey);
    kUserDefaultsRemoveForKey(kCustomerKey);
    [FEDataCache sharedInstance].user = nil;
    [FEDataCache sharedInstance].customer = nil;
    [self refreshUI];
//    __weak typeof(self) weakself = self;
    [[FELaundryWebService sharedInstance] request:[[FESignoutRequest alloc] initWithUser:[[FEUser alloc] initWithDictionary:kLoginUser]] responseClass:[FEBaseResponse class] response:^(NSError *error, id response) {
        FEBaseResponse *rsp = response;
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
