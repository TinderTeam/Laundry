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

@interface FEHomePageVC ()<UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout>
@property (strong, nonatomic) IBOutlet UIPageControl *pageIndicate;
@property (strong, nonatomic) IBOutlet UICollectionView *adImageCollectionView;
@property (strong, nonatomic) IBOutlet UICollectionView *functionCollectionView;

@property (strong, nonatomic) NSArray *adList;

@end

@implementation FEHomePageVC

-(id)initWithCoder:(NSCoder *)aDecoder{
    self = [super initWithCoder:aDecoder];
    if (self) {
        self.title = kString(@"快客洗涤");
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self request];
    [self requestAD];
    
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

-(void)initUI{
    self.pageIndicate.numberOfPages = 0;
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
        return cell;
        
    }
    return nil;
}

-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    if (collectionView == self.adImageCollectionView) {
        return self.adList.count;
    }else if(collectionView == self.functionCollectionView){
        return 8;
    }
    return 0;
}

-(CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath{
    if (collectionView == self.adImageCollectionView) {
        return CGSizeMake(collectionView.bounds.size.width, collectionView.bounds.size.height);
    }
    return CGSizeMake(70, 70);
    
}

#pragma mark - UITableViewDataSource
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"homePageCell" forIndexPath:indexPath];
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return 3;
}

#pragma mark - UIScrollViewDelegate
-(void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView{
    if (scrollView == self.adImageCollectionView) {
        NSInteger page = scrollView.contentOffset.x / scrollView.bounds.size.width;
        self.pageIndicate.currentPage = page;
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
