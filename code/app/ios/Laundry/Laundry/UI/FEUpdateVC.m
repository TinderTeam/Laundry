//
//  FEUpdateVC.m
//  Laundry
//
//  Created by Seven on 15-2-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEUpdateVC.h"

@interface FEUpdateVC (){
    BOOL _canUpdate;
}
@property (strong, nonatomic) IBOutlet UILabel *cversion;
@property (strong, nonatomic) IBOutlet UILabel *lversion;
@property (strong, nonatomic) IBOutlet FEButton *updateButton;

@end

@implementation FEUpdateVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"版本更新";
    [self initUI];
}

-(void)initUI{
    self.cversion.text = [NSString stringWithFormat:@"当前版本:%@",kAppBuildVersion];
    self.lversion.text = [NSString stringWithFormat:@"最新版本:%@",self.versionLast];
    if ([self.versionLast floatValue] > [kAppBuildVersion floatValue]) {
        [self.updateButton setTitle:@"更新" forState:UIControlStateNormal];
        _canUpdate = YES;
    }else{
        [self.updateButton setTitle:@"当前是最新版本" forState:UIControlStateNormal];
    }
}

- (IBAction)update:(id)sender {
    if (_canUpdate) {
        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:self.versionUrl]];
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
