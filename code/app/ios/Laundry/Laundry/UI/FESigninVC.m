//
//  FESigninVC.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FESigninVC.h"
#import "FELaundryWebService.h"
#import "FESigninRequest.h"
#import "FESigninResponse.h"
#import "FEGetCustomerRequest.h"
#import "FEGetCustomerResponse.h"
#import "FEDataCache.h"

@interface FESigninVC ()<UITextFieldDelegate>
@property (strong, nonatomic) IBOutlet UITextField *phoneTextField;
@property (strong, nonatomic) IBOutlet UITextField *passwordTextfield;

@end

@implementation FESigninVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = kString(@"用户登录");
    [self loadBackItem];
}

-(void)backPress:(id)sender{
    [self dismissViewControllerAnimated:YES completion:nil];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


//- (IBAction)backPress:(id)sender {
//    [self dismissViewControllerAnimated:YES completion:nil];
//}


- (IBAction)signinAction:(id)sender {
    if (self.phoneTextField.text.length && self.passwordTextfield.text.length) {
        FEUser *user = [[FEUser alloc] init];
        user.user_name = self.phoneTextField.text;
        user.password = self.passwordTextfield.text;
        __weak typeof(self) weakself = self;
        [[FELaundryWebService sharedInstance] request:[[FESigninRequest alloc] initWithUser:user] responseClass:[FESigninResponse class] response:^(NSError *error, id response) {
            FESigninResponse *rsp = response;
            if (!error && rsp.errorCode.integerValue == 0) {
                [FEDataCache sharedInstance].user = rsp.user;
                kUserDefaultsSetObjectForKey(rsp.user.dictionary, kLoginUserKey);
                kUserDefaultsSetObjectForKey(rsp.token, kUserTokenKey);
                kUserDefaultsSync;
                [[NSNotificationCenter defaultCenter] postNotificationName:kNotificationUserDidLogin object:rsp.user];
                [weakself dismissViewControllerAnimated:YES completion:nil];
                FEGetCustomerRequest *customer = [[FEGetCustomerRequest alloc] initWithCid:rsp.user.user_id];
                [[FELaundryWebService sharedInstance] request:customer responseClass:[FEGetCustomerResponse class] response:^(NSError *error, id response) {
                    FEGetCustomerResponse *rsp = response;
                    if (!error && rsp.errorCode.integerValue == 0) {
                        [FEDataCache sharedInstance].customer = rsp.obj;
                        kUserDefaultsSetObjectForKey(rsp.obj.dictionary, kCustomerKey);
                        kUserDefaultsSync;
                    }
                }];
            }
        }];
    }
}

#pragma mark - UITextFieldDelegate
-(BOOL)textFieldShouldReturn:(UITextField *)textField{
    [textField resignFirstResponder];
    if (self.phoneTextField == textField) {
        [self.passwordTextfield becomeFirstResponder];
    }else{
        [self signinAction:nil];
    }
    return YES;
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
