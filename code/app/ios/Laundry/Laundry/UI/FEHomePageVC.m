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

#define __KEY_TITLE @"title"
#define __KEY_PNG   @"png"
#define __KEY_NUMBER    @"number"
#define __KEY_ACTION    @"action"

@interface FEHomePageVC ()<UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout>
@property (strong, nonatomic) IBOutlet UIPageControl *pageIndicate;
@property (strong, nonatomic) IBOutlet UICollectionView *adImageCollectionView;
@property (strong, nonatomic) IBOutlet UICollectionView *functionCollectionView;

@property (strong, nonatomic) NSArray *adList;
@property (strong, nonatomic) NSArray *categoryList;
@property (strong, nonatomic) NSArray *listData;

@end

@implementation FEHomePageVC

//typeList.add(getType(0,1,"上装类"));
//typeList.add(getType(0,2,"下装类"));
//typeList.add(getType(0,3,"毛皮服饰类"));
//typeList.add(getType(0,4,"箱包鞋类"));
//typeList.add(getType(0,5,"奢侈品牌类"));
//typeList.add(getType(0,6,"居家类"));
//typeList.add(getType(0,7,"汽车配饰类"));
//typeList.add(getType(0,8,"染色/改色/救治"));

-(id)initWithCoder:(NSCoder *)aDecoder{
    self = [super initWithCoder:aDecoder];
    if (self) {
        self.title = kString(@"快客洗涤");
        UITabBarItem *tabitem = [[UITabBarItem alloc] initWithTitle:kString(@"快客洗涤") image:[UIImage imageNamed:@"tab_icon_home_normal"] selectedImage:[UIImage imageNamed:@"tab_icon_home_pressed"]];
        self.tabBarItem = tabitem;
        
        self.categoryList = @[@{__KEY_TITLE:kString(@"上装类"),__KEY_PNG:@"product_type_1",__KEY_NUMBER:@(1)},
                              @{__KEY_TITLE:kString(@"下装类"),__KEY_PNG:@"product_type_2",__KEY_NUMBER:@(2)},
                              @{__KEY_TITLE:kString(@"毛皮服饰类"),__KEY_PNG:@"product_type_3",__KEY_NUMBER:@(3)},
                              @{__KEY_TITLE:kString(@"箱包鞋类"),__KEY_PNG:@"product_type_4",__KEY_NUMBER:@(4)},
                              @{__KEY_TITLE:kString(@"奢侈品牌类"),__KEY_PNG:@"product_type_5",__KEY_NUMBER:@(5)},
                              @{__KEY_TITLE:kString(@"居家类"),__KEY_PNG:@"product_type_6",__KEY_NUMBER:@(6)},
                              @{__KEY_TITLE:kString(@"汽车配饰类"),__KEY_PNG:@"product_type_7",__KEY_NUMBER:@(7)},
                              @{__KEY_TITLE:kString(@"染色/改色/救治"),__KEY_PNG:@"product_type_8",__KEY_NUMBER:@(8)}];
        
        NSInvocation *inv1 = [self invocation:@selector(goOrder:)];
        NSInvocation *inv2 = [self invocation:@selector(call:)];
        NSInvocation *inv3 = [self invocation:@selector(join:)];
        
        self.listData = @[@{__KEY_PNG:@"home_direct_order",__KEY_ACTION:inv1},
                          @{__KEY_PNG:@"home_service_phone",__KEY_ACTION:inv2},
                          @{__KEY_PNG:@"home_join_invest",__KEY_ACTION:inv3}];
        
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.view.backgroundColor = kThemeColor;
    [self request];
    [self requestAD];
    self.pageIndicate.currentPageIndicatorTintColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"image_selected"]];
    self.pageIndicate.pageIndicatorTintColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"image_unselected"]];
}

-(void)request{
    [[FELaundryWebService sharedInstance] request:[[FEGetCompanyRequest alloc] initWithCid:@(1)] responseClass:[FEGetCompanyResponse class] response:^(NSError *error, id response) {
        
    }];
}

-(void)requestAD{
    __weak typeof(self) weakself = self;
    [[FELaundryWebService sharedInstance] request:[[FEGetADRequest alloc] init] responseClass:[FEGetADResponse class] response:^(NSError *error, id response) {
        FEGetADResponse *rsp = response;
        if (!error && rsp.errorCode.integerValue == 0) {
            weakself.pageIndicate.numberOfPages = rsp.obj.count;
            weakself.adList = rsp.obj;
            [weakself.adImageCollectionView reloadData];
        
        }
    }];
}

-(NSInvocation *)invocation:(SEL)selector{
    
    NSMethodSignature *sig=[self methodSignatureForSelector:selector];
    NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:sig];
    [invocation setSelector:selector];
    [invocation setTarget:self];
    return invocation;
}

-(void)initUI{
    self.pageIndicate.numberOfPages = 0;
}

#pragma mark - UIStoryboardSegue
-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if ([sender isKindOfClass:[UICollectionViewCell class]]) {
        FESelectCategoryVC *vc = segue.destinationViewController;
        NSIndexPath *indexPath = [self.functionCollectionView indexPathForCell:sender];
        vc.fatherID = self.categoryList[indexPath.row][__KEY_NUMBER];
    }
}

#pragma mark - UICollectionDataSource
-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    if (collectionView == self.adImageCollectionView) {
        FEAD *ad = self.adList[indexPath.row];
        UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"adImageItemCell" forIndexPath:indexPath];
        UIImageView *imageView = (UIImageView *)[cell viewWithTag:1];
        [imageView sd_setImageWithURL:[NSURL URLWithString:kImageURL(ad.ad_img)]];
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
        return self.adList.count;
    }else if(collectionView == self.functionCollectionView){
        return self.categoryList.count;
    }
    return 0;
}

-(CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath{
    if (collectionView == self.adImageCollectionView) {
        return CGSizeMake(collectionView.bounds.size.width, collectionView.bounds.size.height);
    }
    return CGSizeMake(70, 60);
    
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
    
}

-(void)call:(id)sender{
    
}

-(void)join:(id)sender{
    
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
