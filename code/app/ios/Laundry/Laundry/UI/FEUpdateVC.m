//
//  FEUpdateVC.m
//  Laundry
//
//  Created by Seven on 15-2-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEUpdateVC.h"
#import "FECheckUpdate.h"

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
    self.title = @"版本信息";
    [self.updateButton setTitle:@"更新" forState:UIControlStateNormal];
    [self.updateButton setTitle:@"当前是最新版本" forState:UIControlStateDisabled];
    [self initUI];
}

-(void)initUI{
    
//    self.lversion.text = [NSString stringWithFormat:@"最新版本:%@",self.versionLast];
    NSString *lastVersion = [FECheckUpdate sharedInstance].lastVersion;
    if ([self isNewForLastVersion:lastVersion]) {
        self.cversion.text = [NSString stringWithFormat:@"当前版本:%@",kAppBuildVersion];
        _canUpdate = YES;
    }else{
        self.cversion.text = [NSString stringWithFormat:@"当前版本:%@",kAppBuildVersion];
        self.updateButton.enabled = NO;
        
    }
}

-(BOOL)isNewForLastVersion:(NSString *)lastVersion{
    if (![FECheckUpdate sharedInstance].lastVersion) {
        return NO;
    }else{
        NSString *lastVersion = [FECheckUpdate sharedInstance].lastVersion;
//        NSString *lastBVersion = [FECheckUpdate sharedInstance].buildVersion;
        NSString *cVersion = kAppBuildVersion;
//        NSString *bVersion = kAppVersion;
//        if (bVersion) {
//            if (![bVersion isEqualToString:lastBVersion]) {
//                return YES;
//            }
//        }else{
//            return NO;
//        }
        
        
        NSArray *lvA = [lastVersion componentsSeparatedByString:@"."];
        NSArray *cvA = [cVersion componentsSeparatedByString:@"."];
        if (lvA.count == 3 && cvA.count == 3) {
                if ([lvA[0] integerValue] > [cvA[0] integerValue]) {
                    return YES;
                }else if ([lvA[0] integerValue] == [cvA[0] integerValue]){
                    if ([lvA[1] integerValue] > [cvA[1] integerValue]) {
                        return YES;
                    }else if([lvA[1] integerValue] == [cvA[1] integerValue]){
                        if ([lvA[2] integerValue] > [cvA[2] integerValue]) {
                            return YES;
                        }else{
                            return NO;
                        }
                    }else{
                        return NO;
                    }
                }else{
                    return NO;
                }
        }else{
            return NO;
        }
        
    }
}

- (IBAction)update:(id)sender {
    if (_canUpdate) {
        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:[FECheckUpdate sharedInstance].versionURLString]];
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
