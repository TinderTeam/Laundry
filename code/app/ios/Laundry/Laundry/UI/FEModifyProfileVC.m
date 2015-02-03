//
//  FEModifyProfileVC.m
//  Laundry
//
//  Created by Seven on 15-2-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEModifyProfileVC.h"
#import <libPhoneNumber-iOS/NBPhoneNumberUtil.h>
#import "NSString+Verify.h"
#import "NSDate+Formatter.h"
#import "FEPopPickerView.h"
#import "AppDelegate.h"
#import "FEButton.h"
#import "FEModifyProfileRequest.h"
#import "FELaundryWebService.h"
#import "GAAlertObj.h"

@interface FEModifyProfileVC ()<UITextFieldDelegate,FEPopPickerViewDataSource>
@property (strong, nonatomic) IBOutlet UITextField *realName;
@property (strong, nonatomic) IBOutlet UITextField *sex;
@property (strong, nonatomic) IBOutlet UITextField *birthday;
@property (strong, nonatomic) IBOutlet UITextField *phone;
@property (strong, nonatomic) IBOutlet UITextField *email;
@property (strong, nonatomic) IBOutlet UITextField *activityRegion;
@property (nonatomic, strong) UITextField *currentTextField;
@property (nonatomic, strong) UIDatePicker *datePicker;
@property (nonatomic, strong) UIView *contentView;
@property (nonatomic, strong) UIControl *maskview;

@property (nonatomic, strong) NSArray *sexString;

@end

@implementation FEModifyProfileVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = kString(@"用户信息修改");
    _sexString = @[@"男",@"女"];
    [self refreshUI];
}

-(void)refreshUI{
    self.realName.text = self.customer.customer_name;
    self.sex.text = self.customer.customer_sex;
    self.birthday.text = self.customer.birthday;
    self.phone.text = self.customer.phone;
    self.email.text = self.customer.customer_email;
    self.activityRegion.text = self.customer.addr;
}

#pragma mark - UITextFieldDelegate
-(BOOL)textFieldShouldBeginEditing:(UITextField *)textField{
    [self.currentTextField resignFirstResponder];
    self.currentTextField = textField;
    if (self.birthday == textField || self.sex == textField) {
        if (self.sex == textField) {
            FEPopPickerView *pick = [[FEPopPickerView alloc] initFromView:[[AppDelegate sharedDelegate].window viewWithTag:0]];
            NSInteger index = [self.sexString indexOfObject:self.sex.text];
            NSInteger indexs = -1;
            if (index != NSNotFound) {
                indexs = index;
            }
//            NSInteger index = 0;
            pick.selectIndex = indexs;
            pick.dataSource = self;
            [pick show];
        }else{
            
            CGRect rect = [UIScreen mainScreen].bounds;
            self.maskview = [[UIControl alloc] initWithFrame:rect];
            [self.maskview addTarget:self action:@selector(dismiss:) forControlEvents:UIControlEventTouchUpInside];
            self.maskview.backgroundColor = [UIColor blackColor];
            self.maskview.alpha = .5;
            self.datePicker = [[UIDatePicker alloc] init];
            self.datePicker.datePickerMode = UIDatePickerModeDate;
            self.datePicker.backgroundColor = [UIColor whiteColor];
//            self.datePicker.center = CGPointMake(rect.size.width / 2.0, rect.size.height / 2.0);
            
            UIView *content = [[UIView alloc] initWithFrame:CGRectMake(0, 0, self.datePicker.bounds.size.width, self.datePicker.bounds.size.height + 40)];
            content.backgroundColor = [UIColor whiteColor];
            content.center = CGPointMake(rect.size.width / 2.0, rect.size.height / 2.0);
            [content addSubview:self.datePicker];
            self.contentView = content;
            
            FEButton *sure = [FEButton buttonWithType:UIButtonTypeCustom];
            [sure addTarget:self action:@selector(selectdate:) forControlEvents:UIControlEventTouchUpInside];
            [sure setTitle:@"确定" forState:UIControlStateNormal];
            sure.frame = CGRectMake(0, 0, 180, 35);
            sure.center = CGPointMake(self.contentView.bounds.size.width / 2.0, self.contentView.bounds.size.height - 35 / 2.0 - 3);
            [self.contentView addSubview:sure];
            
            [[[AppDelegate sharedDelegate].window viewWithTag:0] addSubview:self.maskview];
            [[[AppDelegate sharedDelegate].window viewWithTag:0] addSubview:self.contentView];
            
        }
        return NO;
    }
//    [textField becomeFirstResponder];
    return YES;
}

-(void)dismiss:(id)sender{
    [self.contentView removeFromSuperview];
    [self.maskview removeFromSuperview];
}
-(void)selectdate:(id)sender{
    self.currentTextField.text = [self.datePicker.date stringForDateWithFormatterString:@"YYYY-MM-dd"];
    [self dismiss:nil];
}

- (IBAction)save:(id)sender {
    BOOL isPass = NO;
    if (self.realName.text.length) {
        if ([self.phone.text isPhone]) {
            if (self.email.text.length) {
                if ([self.email.text isEmailType]) {
                    NSLog(@"email");
                    isPass = YES;
                }else{
                    NSLog(@"email invalue");
                    GAAlertAction *action = [GAAlertAction actionWithTitle:@"确定" action:^{
                        
                    }];
                    [GAAlertObj showAlertWithTitle:@"提示" message:@"邮箱格式不正确" actions:@[action] inViewController:self];
                }
            }else{
                isPass = YES;
            }
        }else{
            GAAlertAction *action = [GAAlertAction actionWithTitle:@"确定" action:^{
                
            }];
            [GAAlertObj showAlertWithTitle:@"提示" message:@"手机格式不正确" actions:@[action] inViewController:self];
            NSLog(@"phone invalue");
        }
    }else{
        GAAlertAction *action = [GAAlertAction actionWithTitle:@"确定" action:^{
            
        }];
        [GAAlertObj showAlertWithTitle:@"提示" message:@"真实姓名不能为空！" actions:@[action] inViewController:self];
        NSLog(@"名不能为空");
    }
    if (isPass) {
        FECustomer *customer = [[FECustomer alloc] initWithDictionary:self.customer.dictionary];
        customer.customer_name = self.realName.text;
        customer.customer_sex = self.sex.text;
        customer.birthday = self.birthday.text;
        customer.phone = self.phone.text;
        customer.customer_email = self.email.text;
        customer.addr = self.activityRegion.text;
        FEModifyProfileRequest *rdata = [[FEModifyProfileRequest alloc] initWithCustomer:customer];
        __weak typeof(self) weakself = self;
        [self displayHUD:@"提交中..."];
        [[FELaundryWebService sharedInstance] request:rdata responseClass:[FEBaseResponse class] response:^(NSError *error, id response) {
            FEBaseResponse *rsp = response;
            if (!error && rsp.errorCode.integerValue == 0) {
                [[NSNotificationCenter defaultCenter] postNotificationName:kNotificationUserDidChange object:customer];
                [weakself.navigationController popViewControllerAnimated:YES];
            }
            [weakself hideHUD:YES];
        }];
    }
}

#pragma mark - FEPopPickerViewDataSource
-(NSInteger)numberInPicker:(FEPopPickerView *)picker{
    return self.sexString.count;
}
-(NSString *)picker:(FEPopPickerView *)picker titleAtIndex:(NSInteger)index{
    return self.sexString[index];
}


-(void)picker:(FEPopPickerView *)picker didSelectAtIndex:(NSInteger)index{
//    self.oinfo.payType = self.payType[index][__KEY_PAY_NAME];
    self.sex.text = self.sexString[index];
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
