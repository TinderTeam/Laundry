//
//  FESettingVC.m
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FESettingVC.h"
#import "FEClientVersionResponse.h"
#import "FEUpdateRequest.h"
#import "FELaundryWebService.h"
#import "FECheckUpdate.h"
#import "FEUpdateVC.h"
#import "FEWebVC.h"
#import "FELaundryLandingPage.h"

@interface FESettingVC ()
@property (nonatomic, strong) NSString *version;
@property (nonatomic, strong) NSString *versionURL;
@end

@implementation FESettingVC

-(id)initWithCoder:(NSCoder *)aDecoder{
    self = [super initWithCoder:aDecoder];
    if (self) {
        self.title = kString(@"更多");
        UITabBarItem *tabitem = [[UITabBarItem alloc] initWithTitle:kString(@"更多") image:[UIImage imageNamed:@"tab_icon_more_normal"] selectedImage:[UIImage imageNamed:@"tab_icon_more_pressed"]];
        self.tabBarItem = tabitem;
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.row == 4) {
        NSLog(@"更新");
        __weak typeof(self) weakself = self;
        [self displayHUD:@"检查更新..."];
        
        [[FECheckUpdate sharedInstance] chechUpdate:^(NSError *error, id response) {
            NSDictionary *rsp = response;
            if (error) {
                [self hideHUD:YES];
                return ;
            }
            if (rsp && [rsp[@"resultCount"] integerValue] != 0) {
                NSArray *resultArray = [rsp objectForKey:@"results"];
                NSDictionary *resultDict = [resultArray objectAtIndex:0];
                NSString *newVersion = [resultDict objectForKey:@"version"];
                weakself.version = newVersion;
                NSString *urlstring = [[resultDict objectForKey:@"trackViewUrl"] copy];
                self.versionURL = urlstring;
            }else{
                weakself.version = @"";
            }
            [weakself performSegueWithIdentifier:@"toupdateSegue" sender:self];
//            if (rsp && [rsp[@"resultCount"] integerValue] != 0) {
//                NSArray *resultArray = [rsp objectForKey:@"results"];
//                //                DLog(@"version %@",[resultArray objectAtIndex:0]);
//                NSDictionary *resultDict = [resultArray objectAtIndex:0];
//                //                DLog(@"version is %@",[resultDict objectForKey:@"version"]);
//                NSString *newVersion = [resultDict objectForKey:@"version"];
//                
//                if ([newVersion doubleValue] > [kAppVersion doubleValue]) {
//                    NSString *msg = [NSString stringWithFormat:@"最新版本为%@,是否更新？",newVersion];
//                    
//                    NSString *urlstring = [[resultDict objectForKey:@"trackViewUrl"] copy];
//                    
//                    GAAlertAction *action = [GAAlertAction actionWithTitle:@"立即更新" action:^{
//                        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:urlstring]];
//                    }];
//                    
//                    GAAlertAction *action2 = [GAAlertAction actionWithTitle:@"取消" action:^{
//                        
//                    }];
//                    
//                    [GAAlertObj showAlertWithTitle:@"更新" message:msg actions:@[action,action2] inViewController:weakself];
//
//                }
//            }else{
//                kAlert(@"已是最新版本！", weakself);
//            }
            [self hideHUD:YES];
        }];
        
//        [[FELaundryWebService sharedInstance] request:[FEUpdateRequest new] responseClass:[FEClientVersionResponse class] response:^(NSError *error, id response) {
//            FEClientVersionResponse *rsp = response;
//            if (!error && rsp.errorCode.integerValue == 0) {
//                
//            }
//            
//        }];
    }else if (indexPath.row == 3){
        FELaundryLandingPage *page = [[UIStoryboard storyboardWithName:@"LandingPage" bundle:nil] instantiateInitialViewController];
        page.isFromSetting = YES;
        [self.navigationController pushViewController:page animated:YES];
    }
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if ([segue.identifier isEqualToString:@"toupdateSegue"]) {
        FEUpdateVC *vc = segue.destinationViewController;
        vc.versionLast = self.version;
        vc.versionUrl = self.versionURL;
    }else if ([segue.identifier isEqualToString:@"toWebViewSegue1"]){
        FEWebVC *vc = segue.destinationViewController;
        vc.title = @"快客介绍";
        vc.urlString = kIntroductURL;
    }else if ([segue.identifier isEqualToString:@"toWebViewSegue2"]){
        FEWebVC *vc = segue.destinationViewController;
        vc.title = @"加入快客";
        vc.urlString = kJoinURL;
    }else if ([segue.identifier isEqualToString:@"toWebViewSegue3"]){
        FEWebVC *vc = segue.destinationViewController;
        vc.title = @"注意事项";
        vc.urlString = kAttentionURL;
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
