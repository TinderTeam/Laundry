//
//  FERegistVC.m
//  Laundry
//
//  Created by Seven on 15-1-15.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FERegistVC.h"
#import "UIImage+LogN.h"
#import "FELaundryWebService.h"
#import "FEVerifyCodeRequest.h"
#import "FEVerifyCodeResponse.h"
#import "FERegistResquest.h"
#import "GAAlertObj.h"

@interface FERegistVC ()<UITextFieldDelegate>
@property (strong, nonatomic) IBOutlet UIButton *getCodeButton;
@property (strong, nonatomic) IBOutlet UITextField *phoneTextFeild;
@property (strong, nonatomic) IBOutlet UITextField *verifyCode;
@property (strong, nonatomic) IBOutlet UITextField *passwordTextField;
@property (strong, nonatomic) NSTimer *timer;
@property (assign, nonatomic) NSInteger totalTime;
@property (strong, nonatomic) NSString *code;
@property (assign, nonatomic) long time;

@end

@implementation FERegistVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = kString(@"注册");
    [self.getCodeButton setBackgroundImage:[UIImage imageFromColor:kColor(255, 100, 63, 1)] forState:UIControlStateNormal];
    _totalTime = 60;
}

- (IBAction)getCodeAction:(id)sender {
    if (self.phoneTextFeild.text.length) {
        if ([self.phoneTextFeild.text isPhone]) {
            _totalTime = 60;
            [self requestGetCode];
        }else{
            kAlert(@"手机格式不正确！", self);
        }
    }else{
        kAlert(@"请输入手机号码！", self);
    }
    
}
- (IBAction)registAction:(id)sender {
    if (self.passwordTextField.text.length >=6 && self.passwordTextField.text.length <= 20) {
        if (([[NSDate date] timeIntervalSince1970] - self.time) < 10 * 60 && [self.code isEqual:self.verifyCode.text]) {
            NSLog(@"resgist");
            [self regist];
        }else{
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:kString(@"提示") message:kString(@"验证码错误！") delegate:nil cancelButtonTitle:kString(@"OK") otherButtonTitles: nil];
            [alert show];
        }
    }else{
        kAlert(@"密码为6-20位！", self);
    }
}

-(void)requestGetCode{
    __weak typeof(self) weaksekf = self;
    [[FELaundryWebService sharedInstance] request:[[FEVerifyCodeRequest alloc] initWithPhoneNumber:self.phoneTextFeild.text] responseClass:[FEVerifyCodeResponse class] response:^(NSError *error, id response) {
        FEVerifyCodeResponse *rsp = response;
        if (!error && rsp.errorCode.integerValue == 0) {
            weaksekf.getCodeButton.enabled = NO;
            weaksekf.timer = [NSTimer scheduledTimerWithTimeInterval:1 target:weaksekf selector:@selector(updateTime) userInfo:nil repeats:YES];//[NSTimer timerWithTimeInterval:1 target:weaksekf selector:@selector(updateTime) userInfo:nil repeats:YES];
            weaksekf.code = rsp.obj;
            weaksekf.time = [[NSDate date] timeIntervalSince1970];
        }
    }];
}

-(void)regist{
    __weak typeof(self) weakself = self;
    [[FELaundryWebService sharedInstance] request:[[FERegistResquest alloc] initWithUserName:self.phoneTextFeild.text password:self.passwordTextField.text addr:nil] responseClass:[FEBaseResponse class] response:^(NSError *error, id response) {
        FEBaseResponse *rsp = response;
        if (!error && rsp.errorCode.integerValue == 0) {
            [weakself.navigationController popViewControllerAnimated:YES];
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
#pragma mark - UITextFieldDelegate
-(BOOL)textFieldShouldReturn:(UITextField *)textField{
    [textField resignFirstResponder];
    if (self.phoneTextFeild == textField) {
        [self.verifyCode becomeFirstResponder];
    }else if (self.verifyCode == textField){
        [self.passwordTextField becomeFirstResponder];
    }else{
        [self registAction:nil];
    }
    return YES;
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
