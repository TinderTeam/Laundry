//
//  FEForgotPasswordVC.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEForgotPasswordVC.h"
#import "UIImage+LogN.h"
#import "FELaundryWebService.h"
#import "FEVerifyCodeRequest.h"
#import "FEVerifyCodeResponse.h"
#import "FEResetPasswordRequest.h"

@interface FEForgotPasswordVC ()<UITextFieldDelegate>
@property (strong, nonatomic) IBOutlet UIButton *getCodeButton;
@property (strong, nonatomic) IBOutlet UITextField *phoneTextField;
@property (strong, nonatomic) IBOutlet UITextField *codeTextFeild;
@property (strong, nonatomic) IBOutlet UITextField *passwordTextFeild;
@property (strong, nonatomic) NSTimer *timer;
@property (assign, nonatomic) NSInteger totalTime;
@property (nonatomic, strong) NSString *code;
@property (nonatomic, assign) long time;

@end

@implementation FEForgotPasswordVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = kString(@"找回密码");
    [self.getCodeButton setBackgroundImage:[UIImage imageFromColor:kColor(255, 100, 63, 1)] forState:UIControlStateNormal];
    _totalTime = 60;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)getCode:(id)sender {
    if ([self.phoneTextField.text isPhone]) {
        [self requestGetCode];
    }else{
        GAAlertAction *action = [GAAlertAction actionWithTitle:@"确定" action:^{
            
        }];
        [GAAlertObj showAlertWithTitle:@"提示" message:@"请输入正确的手机号码" actions:@[action] inViewController:self];
    }
}

-(void)requestGetCode{
    __weak typeof(self) weaksekf = self;
    [[FELaundryWebService sharedInstance] request:[[FEVerifyCodeRequest alloc] initWithPhoneNumber:self.phoneTextField.text] responseClass:[FEVerifyCodeResponse class] response:^(NSError *error, id response) {
        FEVerifyCodeResponse *rsp = response;
        if (!error && rsp.errorCode.integerValue == 0) {
            weaksekf.getCodeButton.enabled = NO;
            weaksekf.timer = [NSTimer scheduledTimerWithTimeInterval:1 target:weaksekf selector:@selector(updateTime) userInfo:nil repeats:YES];
            weaksekf.code = rsp.obj;
            weaksekf.time = [[NSDate date] timeIntervalSince1970];
        }
    }];
}

-(void)updateTime{
    
    [self.getCodeButton setTitle:[NSString stringWithFormat:@"%ld's",(long)_totalTime] forState:UIControlStateDisabled];
    _totalTime--;
    if (_totalTime <= 0) {
        self.getCodeButton.enabled = YES;
        [self.timer invalidate];
        self.timer = nil;
        _totalTime = 60;
        [self.getCodeButton setTitle:@"60's" forState:UIControlStateDisabled];
    }
}

- (IBAction)resetPassword:(id)sender {
    if ([[NSDate date] timeIntervalSince1970] - self.time < 10 * 60 && [self.codeTextFeild.text isEqualToString:self.code]) {
        
        if (self.passwordTextFeild.text.length >= 6) {
            __weak typeof(self) weakself = self;
            [[FELaundryWebService sharedInstance] request:[[FEResetPasswordRequest alloc] initWithUserName:self.phoneTextField.text password:self.passwordTextFeild.text] responseClass:[FEBaseResponse class] response:^(NSError *error, id response) {
                FEBaseResponse *rsp = response;
                if (!error && !rsp.errorCode.boolValue) {
                    NSLog(@"success");
                    [weakself.navigationController popViewControllerAnimated:YES];
                }
            }];
        }
    }
    
}

-(BOOL)textFieldShouldReturn:(UITextField *)textField{
    [textField resignFirstResponder];
    if (self.phoneTextField == textField) {
        [self.codeTextFeild becomeFirstResponder];
    }else if (self.codeTextFeild == textField){
        [self.passwordTextFeild becomeFirstResponder];
    }else{
        [self resetPassword:nil];
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
