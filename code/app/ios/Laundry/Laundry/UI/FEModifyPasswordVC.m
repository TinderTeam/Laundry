//
//  FEModifyPasswordVC.m
//  Laundry
//
//  Created by Seven on 15-2-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEModifyPasswordVC.h"
#import "FEUser.h"
#import "GAAlertObj.h"
#import "FEModifyPswRequest.h"
#import "FELaundryWebService.h"

@interface FEModifyPasswordVC ()
@property (strong, nonatomic) IBOutlet UITextField *oldPassword;
@property (strong, nonatomic) IBOutlet UITextField *newpassword;
@property (strong, nonatomic) IBOutlet UITextField *confirmPassword;

@end

@implementation FEModifyPasswordVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = kString(@"修改密码");
}

- (IBAction)save:(id)sender {
    FEUser *user = [[FEUser alloc] initWithDictionary:kLoginUser];
    if ([self.oldPassword.text isEqualToString:user.password]) {
        if (self.newpassword.text.length >= 6){
            if ([self.newpassword.text isEqualToString:self.confirmPassword.text]) {
                __weak typeof(self) weakself = self;
                [self displayHUD:@"连接中..."];
                FEModifyPswRequest *rdata = [[FEModifyPswRequest alloc] initWithUname:user.user_name oldPsw:self.oldPassword.text newPsw:self.newpassword.text];
                [[FELaundryWebService sharedInstance] request:rdata responseClass:[FEBaseResponse class] response:^(NSError *error, id response) {
                    FEBaseResponse *rsp = response;
                    if (!error && rsp.errorCode.integerValue == 0) {
                        kUserDefaultsRemoveForKey(kLoginUserKey);
                        [weakself.navigationController popToRootViewControllerAnimated:YES];
                    }
                    [weakself hideHUD:YES];
                }];
            }else{
                GAAlertAction *action = [GAAlertAction actionWithTitle:@"确定" action:^{
                    
                }];
                [GAAlertObj showAlertWithTitle:@"提示" message:@"新密码不一致" actions:@[action] inViewController:self];
            }
        }else{
            GAAlertAction *action = [GAAlertAction actionWithTitle:@"确定" action:^{
                
            }];
            [GAAlertObj showAlertWithTitle:@"提示" message:@"新密码小于6位" actions:@[action] inViewController:self];
        }
        
    }else{
        GAAlertAction *action = [GAAlertAction actionWithTitle:@"确定" action:^{
            
        }];
        [GAAlertObj showAlertWithTitle:@"提示" message:@"输入的原始密码不正确" actions:@[action] inViewController:self];
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
