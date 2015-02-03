//
//  FEMyInfoTableVC.m
//  Laundry
//
//  Created by Seven on 15-2-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEMyInfoTableVC.h"
#import "FEUser.h"
#import "FEGetCustomerRequest.h"
#import "FELaundryWebService.h"
#import "FEGetCustomerResponse.h"
#import "FEModifyProfileVC.h"

@interface FEMyInfoTableVC ()
@property (strong, nonatomic) IBOutlet UILabel *ulabel;
@property (strong, nonatomic) IBOutlet UILabel *vipCardLabel;
@property (strong, nonatomic) IBOutlet UILabel *scoreLabel;
@property (strong, nonatomic) IBOutlet UILabel *nickNameLabel;
@property (strong, nonatomic) IBOutlet UILabel *realNameLabel;
@property (strong, nonatomic) IBOutlet UILabel *sexLabel;
@property (strong, nonatomic) IBOutlet UILabel *birthLabel;
@property (strong, nonatomic) IBOutlet UILabel *phoneLabel;
@property (strong, nonatomic) IBOutlet UILabel *emailLabel;
@property (strong, nonatomic) IBOutlet UILabel *atcivityAdress;
@property (strong, nonatomic) FECustomer *customer;

@end

@implementation FEMyInfoTableVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"我的资料";
    [self requestCustomer];
    [self refreshUI];
    __weak typeof(self) weakself = self;
    [[NSNotificationCenter defaultCenter] addObserverForName:kNotificationUserDidChange object:nil queue:[NSOperationQueue mainQueue] usingBlock:^(NSNotification *note) {
        weakself.customer = note.object;
        [weakself refreshUI];
    }];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)refreshUI{
    FEUser *user = [[FEUser alloc] initWithDictionary:kLoginUser];
    self.ulabel.text = user.user_name;
    self.phoneLabel.text = user.user_name;
    
    self.nickNameLabel.text = self.customer.nickname;
    self.realNameLabel.text = self.customer.customer_name;
    self.sexLabel.text = self.customer.customer_sex;
    self.birthLabel.text = self.customer.birthday;
    self.phoneLabel.text = self.customer.phone;
    self.emailLabel.text = self.customer.customer_email;
    self.atcivityAdress.text = self.customer.addr;
    self.ulabel.text = self.customer.user_name;
    self.scoreLabel.text = self.customer.score.stringValue;
    self.vipCardLabel.text = self.customer.card_number;
}

-(void)requestCustomer{
    __weak typeof(self) weakself = self;
    FEUser *user = [[FEUser alloc] initWithDictionary:kLoginUser];
    FEGetCustomerRequest *rdata = [[FEGetCustomerRequest alloc] initWithCid:user.user_id];
    [[FELaundryWebService sharedInstance] request:rdata responseClass:[FEGetCustomerResponse class] response:^(NSError *error, id response) {
        FEGetCustomerResponse *rsp = response;
        if (!error && rsp.errorCode.integerValue == 0) {
            weakself.customer = rsp.obj;
            [weakself refreshUI];
        }
    }];
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if ([segue.identifier isEqualToString:@"modifyProfileSegue"]) {
        FEModifyProfileVC *pvc = segue.destinationViewController;
        pvc.customer = self.customer;
    }
}

-(void)dealloc{
    
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
