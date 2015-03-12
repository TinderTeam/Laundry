//
//  FEHomePageVC.m
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEHomePageVC.h"
#import "FELaundryWebService.h"
#import "FEGetCompanyRequest.h"
#import "FEGetCompanyResponse.h"
#import "FEGetADRequest.h"
#import "FEGetADResponse.h"
#import <SDWebImage/UIImageView+WebCache.h>
#import "FESelectCategoryVC.h"
#import "FEDataCache.h"
#import "FECustomSegue.h"
#import "GAAlertObj.h"
#import "FEBasketVC.h"
#import "FEWebVC.h"
#import "UIImage+Resize.h"

//#define __KEY_TITLE @"title"
//#define __KEY_PNG   @"png"
//#define __KEY_NUMBER    @"number"
#define __KEY_ACTION    @"action"

@interface FEHomePageVC ()<UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout>
@property (strong, nonatomic) IBOutlet UIPageControl *pageIndicate;
@property (strong, nonatomic) IBOutlet UICollectionView *adImageCollectionView;
@property (strong, nonatomic) IBOutlet UICollectionView *functionCollectionView;

@property (strong, nonatomic) NSArray *adList;
@property (strong, nonatomic) NSArray *categoryList;
@property (strong, nonatomic) NSArray *listData;
@property (strong, nonatomic) FECompany *company;

@end

@implementation FEHomePageVC

-(id)initWithCoder:(NSCoder *)aDecoder{
    self = [super initWithCoder:aDecoder];
    if (self) {
        self.title = kString(@"快客洗涤");
        
        UIView *titleView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 200, 40)];
        UILabel *tlabel = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, titleView.bounds.size.width, 25)];
        tlabel.font = [UIFont appFontWithSize:20];
        tlabel.textAlignment = NSTextAlignmentCenter;
        tlabel.textColor = [UIColor whiteColor];
        tlabel.text = kString(@"快客洗涤");
        [titleView addSubview:tlabel];
        
        UILabel *slabel = [[UILabel alloc] initWithFrame:CGRectMake(0, tlabel.frame.origin.y + tlabel.frame.size.height, tlabel.bounds.size.width, 12)];
        slabel.font = [UIFont appFontWithSize:10];
        slabel.textAlignment = NSTextAlignmentCenter;
        slabel.textColor = [UIColor whiteColor];
        slabel.text = @"www.kkxd.com.cn";
        [titleView addSubview:slabel];
//        [[UIImage imageNamed:@"tab_icon_home_normal"] imageScaledToSize:CGSizeMake(80, 25)]
        
        self.navigationItem.titleView = titleView;
        UITabBarItem *tabitem = [[UITabBarItem alloc] initWithTitle:kString(@"首页") image:[[UIImage imageNamed:@"tab_icon_home_normal"] imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal] selectedImage:[UIImage imageNamed:@"tab_icon_home_pressed"]];
        self.tabBarItem = tabitem;
        
        self.categoryList = [FEDataCache sharedInstance].cateGoryList;

        NSInvocation *inv1 = [self invocation:@selector(goOrder:)];
        NSInvocation *inv2 = [self invocation:@selector(call:)];
        NSInvocation *inv3 = [self invocation:@selector(join:)];
        
        self.listData = @[@{__KEY_PNG:@"home_direct_order",__KEY_ACTION:inv1},
                          @{__KEY_PNG:@"home_service_phone",__KEY_ACTION:inv2},
                          @{__KEY_PNG:@"home_join_invest",__KEY_ACTION:inv3}
                          ];
        
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
    [self request];
//    [self autoScroll];
}

-(void)request{
    __weak typeof(self) weakself = self;
    dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
    dispatch_group_t group = dispatch_group_create();
    dispatch_group_async(group, queue, ^{
        dispatch_semaphore_t sem = dispatch_semaphore_create(0);
        [[FELaundryWebService sharedInstance] request:[[FEGetCompanyRequest alloc] initWithCid:@(1)] responseClass:[FEGetCompanyResponse class] response:^(NSError *error, id response) {
            FEGetCompanyResponse *rsp = response;
            if (!error && rsp.errorCode.integerValue == 0) {
                weakself.company = rsp.obj;
                [FEDataCache sharedInstance].company = rsp.obj;
            }
            dispatch_semaphore_signal(sem);
        }];
        dispatch_semaphore_wait(sem, DISPATCH_TIME_FOREVER);
    });
    dispatch_group_async(group, queue, ^{
        dispatch_semaphore_t sem = dispatch_semaphore_create(0);
        [[FELaundryWebService sharedInstance] request:[[FEGetADRequest alloc] init] responseClass:[FEGetADResponse class] response:^(NSError *error, id response) {
            FEGetADResponse *rsp = response;
            if (!error && rsp.errorCode.integerValue == 0) {
                weakself.pageIndicate.numberOfPages = rsp.obj.count;
                weakself.adList = rsp.obj;
            }
            dispatch_semaphore_signal(sem);
        }];
        dispatch_semaphore_wait(sem, DISPATCH_TIME_FOREVER);
    });
    dispatch_group_notify(group, dispatch_get_main_queue(), ^{
        weakself.pageIndicate.numberOfPages = weakself.adList.count;
        [weakself.adImageCollectionView reloadData];
        [weakself autoScroll];
    });

}


-(NSInvocation *)invocation:(SEL)selector{
    
    NSMethodSignature *sig=[self methodSignatureForSelector:selector];
    NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:sig];
    [invocation setSelector:selector];
    [invocation setTarget:self];
    return invocation;
}

-(void)initUI{
    self.view.backgroundColor = kThemeColor;
    self.pageIndicate.currentPageIndicatorTintColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"image_selected"]];
    self.pageIndicate.pageIndicatorTintColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"image_unselected"]];
    self.pageIndicate.numberOfPages = 0;
    
}

-(void)toCategory{
    
    [self performSegueWithIdentifier:@"toCategorySegue" sender:self];
}

-(void)autoScroll{
    __weak typeof(self) weakself = self;
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_LOW, 0), ^{
        NSInteger index = 0;
        while (1) {
            if (weakself.adList.count && weakself.pageIndicate.numberOfPages != 0) {
                dispatch_async(dispatch_get_main_queue(), ^{
                    [weakself.adImageCollectionView scrollToItemAtIndexPath:[NSIndexPath indexPathForRow:index inSection:0] atScrollPosition:UICollectionViewScrollPositionNone animated:YES];
                    weakself.pageIndicate.currentPage = index;
                });
                index++;
                if (index >= weakself.adList.count) {
                    index = 0;
                }
            }
            
            sleep(3);
        }
    });
}

#pragma mark - UIStoryboardSegue
-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if ([segue.identifier isEqualToString:@"toWebViewSegue"]) {
        FEWebVC *vc = segue.destinationViewController;
        vc.title = @"服务范围";
        vc.urlString = kServicePartURL;
    }else if ([sender isKindOfClass:[UICollectionViewCell class]]) {
        FESelectCategoryVC *vc = segue.destinationViewController;
        NSIndexPath *indexPath = [self.functionCollectionView indexPathForCell:sender];
        vc.fatherID = self.categoryList[indexPath.row][__KEY_NUMBER];
    }else if(sender == self){
        ((FECustomSegue *)segue).animation = NO;
        FESelectCategoryVC *vc = segue.destinationViewController;
        vc.fatherID = self.categoryList[0][__KEY_NUMBER];
    }
}

#pragma mark - UICollectionDataSource
-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    if (collectionView == self.adImageCollectionView) {
        
        FEAD *ad = self.adList.count?self.adList[indexPath.row]:nil;
        UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"adImageItemCell" forIndexPath:indexPath];
        UIImageView *imageView = (UIImageView *)[cell viewWithTag:1];
        [imageView sd_setImageWithURL:[NSURL URLWithString:kImageURL(ad.ad_img)] placeholderImage:[UIImage imageNamed:@"loading_large_image"]];
        return cell;
    }else if(collectionView == self.functionCollectionView){
        UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"functionCategoryItemCell" forIndexPath:indexPath];
        UIImageView *imageView = (UIImageView *)[cell viewWithTag:1];
        imageView.image = [UIImage imageNamed:self.categoryList[indexPath.row][__KEY_PNG]];
        
        UILabel *label = (UILabel *)[cell viewWithTag:2];
        label.text = self.categoryList[indexPath.row][__KEY_TITLE];
        return cell;
        
    }
    return nil;
}

-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    if (collectionView == self.adImageCollectionView) {
        if (self.adList.count) {
            return self.adList.count;
        }
        return 1;
    }else if(collectionView == self.functionCollectionView){
        return self.categoryList.count;
    }
    return 0;
}

//#pragma mark - UICollectionViewDelegateFlowLayout
//- (UIEdgeInsets)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout insetForSectionAtIndex:(NSInteger)section{
//    CGFloat height = [UIScreen mainScreen].bounds.size.height;
//    if (height == 667) {
//        return UIEdgeInsetsMake(20, 20, 20, 20);
//    }else if(height == 736){
//        return UIEdgeInsetsMake(25, 25, 25, 25);
//    }else{
//        return UIEdgeInsetsMake(10, 10, 10, 10);
//    }
//}

-(CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath{
    if (collectionView == self.adImageCollectionView) {
        return CGSizeMake(collectionView.bounds.size.width, collectionView.bounds.size.height);
    }
    return CGSizeMake(70, 50);
    
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"homePageCell" forIndexPath:indexPath];
    UIImageView *imageView = (UIImageView *)[cell viewWithTag:1];
    imageView.image = [UIImage imageNamed:self.listData[indexPath.row][__KEY_PNG]];
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.listData.count;
}

#pragma mark - UITabelViewDelegate
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    NSInvocation *inv = self.listData[indexPath.row][__KEY_ACTION];
    [inv invoke];
}

#pragma mark - UIScrollViewDelegate
-(void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView{
    if (scrollView == self.adImageCollectionView) {
        NSInteger page = scrollView.contentOffset.x / scrollView.bounds.size.width;
        self.pageIndicate.currentPage = page;
    }
}

-(void)goOrder:(id)sender{
    if (kLoginUser) {
//        [self.tabBarController setSelectedIndex:1];
        UIViewController *controller = [[[self.tabBarController viewControllers] objectAtIndex:1] topViewController];
        if ([controller isKindOfClass:[FEBasketVC class]]) {
            [self.tabBarController setSelectedIndex:1];
            [((FEBasketVC *)controller) toSubmitOrder];
            [self.navigationController popToRootViewControllerAnimated:NO];
        }

    }else{
//        toSigninSegue
        [self performSegueWithIdentifier:@"toSigninSegue" sender:nil];
    }
    
}

-(void)call:(id)sender{
    if (self.company) {
        __weak typeof(self) weakself = self;
        GAAlertAction *action = [GAAlertAction actionWithTitle:@"拨打" action:^{
            kCall(weakself.company.service_phone);
        }];
        
        GAAlertAction *action1 = [GAAlertAction actionWithTitle:@"取消" action:^{
            
        }];
        
        [GAAlertObj showAlertWithTitle:@"客服电话" message:weakself.company.service_phone actions:@[action,action1] inViewController:self];
        
//        kCall(self.company.service_phone);
    }
}

-(void)join:(id)sender{
    [self performSegueWithIdentifier:@"toWebViewSegue" sender:nil];
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
