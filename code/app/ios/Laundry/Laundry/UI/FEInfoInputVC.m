//
//  FEInfoInputVC.m
//  Laundry
//
//  Created by Seven on 15-2-1.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEInfoInputVC.h"
#import "FEGetCurrentCity.h"
#import "FEButton.h"
#import "GAAlertObj.h"
#import <libPhoneNumber-iOS/NBPhoneNumberUtil.h>

@interface FEInfoInputVC ()<UITextViewDelegate>{
    NSInteger _inputLenth;
}
@property (strong, nonatomic) IBOutlet UITextView *inputTextView;
@property (strong, nonatomic) FEGetCurrentCity *currentCity;
@property (strong, nonatomic) IBOutlet FEButton *gpsLocationButton;
@property (strong, nonatomic) IBOutlet FEButton *defaultLocationButton;
@property (strong, nonatomic) IBOutlet UILabel *limitLabel;


@end

@implementation FEInfoInputVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.edgesForExtendedLayout = UIRectEdgeNone;
    self.inputTextView.layer.borderWidth = 1;
    self.inputTextView.layer.borderColor = [UIColor lightGrayColor].CGColor;
    self.inputTextView.layer.masksToBounds = YES;
    self.inputTextView.layer.cornerRadius = 3;
    self.gpsLocationButton.hidden = YES;
    self.defaultLocationButton.hidden = YES;
    self.inputTextView.delegate = self;
    [self configUI];
    
}

-(void)viewDidAppear:(BOOL)animated{
    [super viewDidAppear:animated];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(textDidChange:) name:UITextViewTextDidChangeNotification object:nil];
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UITextViewTextDidChangeNotification object:nil];
}

-(void)textDidChange:(NSNotification *)note{
    self.limitLabel.text = [NSString stringWithFormat:@"%ld/%ld",(long)
                            self.inputTextView.text.length,(long)_inputLenth];
}


- (IBAction)currentLocation:(id)sender {
    [self.currentCity cacel];
    self.currentCity = [[FEGetCurrentCity alloc] init];
    __weak typeof(self) weakself = self;
    [self.currentCity getCity:^(NSError *error, NSString *city) {
        if (!error) {
            weakself.inputTextView.text = city;
        }
    }];
}

- (IBAction)defaultLocation:(id)sender {
    
}

-(void)configUI{
    if ([self.typeName isEqualToString:@"取衣地址"]) {
        _inputLenth = 50;
        self.gpsLocationButton.hidden = NO;
        self.defaultLocationButton.hidden = NO;
        self.inputTextView.text = self.orderInfo.take_addr;
        self.title = @"取衣地址";
    }else if([self.typeName isEqualToString:@"送回地址"]){
        _inputLenth = 50;
        self.gpsLocationButton.hidden = NO;
        self.defaultLocationButton.hidden = NO;
        self.inputTextView.text = self.orderInfo.delivery_addr;
        self.title = @"送回地址";
    }else if([self.typeName isEqualToString:@"联系人"]){
        _inputLenth = 10;
        self.inputTextView.text = self.orderInfo.contact_name;
        self.title = @"联系人";
    }else if([self.typeName isEqualToString:@"联系电话"]){
        self.inputTextView.text = self.orderInfo.phone;
        self.title = @"联系电话";
    }else if([self.typeName isEqualToString:@"您的要求"]){
        self.title = @"您的要求";
        self.inputTextView.text = self.orderInfo.remark;
    }
}
- (IBAction)saveInfo:(id)sender {
    
    if ([self.typeName isEqualToString:@"取衣地址"]) {
        self.orderInfo.take_addr = self.inputTextView.text;
    }else if([self.typeName isEqualToString:@"送回地址"]){
        self.orderInfo.delivery_addr = self.inputTextView.text;
    }else if([self.typeName isEqualToString:@"联系人"]){
        self.orderInfo.contact_name = self.inputTextView.text;
    }else if([self.typeName isEqualToString:@"联系电话"]){
        if ([self.inputTextView.text isPhone]) {
            self.orderInfo.phone = self.inputTextView.text;
        }else{
            GAAlertAction *action = [GAAlertAction actionWithTitle:@"确定" action:^{
                
            }];
            [GAAlertObj showAlertWithTitle:@"提示" message:@"电话格式有误！" actions:@[action] inViewController:self];
            return;
        }
        
    }else if([self.typeName isEqualToString:@"您的要求"]){
        self.orderInfo.remark = self.inputTextView.text;
    }
    [self.navigationController popViewControllerAnimated:YES];
}

-(BOOL)textView:(UITextView *)textView shouldChangeTextInRange:(NSRange)range replacementText:(NSString *)text{
    if ([self.typeName isEqualToString:@"取衣地址"] || [self.typeName isEqualToString:@"送回地址"] || [self.typeName isEqualToString:@"联系人"] || [self.typeName isEqualToString:@"您的要求"]) {
        if ([textView.text stringByReplacingCharactersInRange:range withString:text].length > _inputLenth) {
            return NO;
        }
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
