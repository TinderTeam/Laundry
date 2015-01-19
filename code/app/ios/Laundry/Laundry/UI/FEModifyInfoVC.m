//
//  FEModifyInfoVC.m
//  Laundry
//
//  Created by Seven on 15-1-19.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEModifyInfoVC.h"
#import "FEOrderInfo.h"
#import "FEGetCurrentCity.h"

@interface FEModifyInfoVC ()
@property (strong, nonatomic) IBOutlet UITextField *takeAddress;
@property (strong, nonatomic) IBOutlet UITextField *backAddress;
@property (strong, nonatomic) IBOutlet UITextField *contact;
@property (strong, nonatomic) IBOutlet UITextField *contactPhone;
@property (strong, nonatomic) FEGetCurrentCity *currentCity;
@end

@implementation FEModifyInfoVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.takeAddress.text = self.orderInfo.takeAddress;
    self.backAddress.text = self.orderInfo.backAddress;
    self.contact.text = self.orderInfo.contact;
    self.contactPhone.text = self.orderInfo.contactPhone;
}
- (IBAction)defaultTakeAddress:(id)sender {
    
}
- (IBAction)takeCurrentAddress:(id)sender {
    [self.currentCity cacel];
    self.currentCity = [[FEGetCurrentCity alloc] init];
    __weak typeof(self) weakself = self;
    [self.currentCity getCity:^(NSError *error, NSString *city) {
        if (!error) {
            weakself.takeAddress.text = city;
        }
    }];
}
- (IBAction)defaultBackAddress:(id)sender {
    
}
- (IBAction)backCurrentAddress:(id)sender {
    [self.currentCity cacel];
    self.currentCity = [[FEGetCurrentCity alloc] init];
    __weak typeof(self) weakself = self;
    [self.currentCity getCity:^(NSError *error, NSString *city) {
        if (!error) {
            weakself.backAddress.text = city;
        }
    }];
}
- (IBAction)saveInfo:(id)sender {
    self.orderInfo.takeAddress = self.takeAddress.text;
    self.orderInfo.backAddress = self.backAddress.text;
    self.orderInfo.contact = self.contact.text;
    self.orderInfo.contactPhone = self.contactPhone.text;
    [self.navigationController popViewControllerAnimated:YES];
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
