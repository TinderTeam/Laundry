//
//  FESelectCategoryVC.m
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FESelectCategoryVC.h"
#import "FEGetProductTypeReques.h"
#import "FEGetProductTypeResponse.h"
#import "FELaundryWebService.h"
#import "FEDataCache.h"
#import <SDWebImage/UIImageView+WebCache.h>
#import "FEBasketVC.h"
#import "FEDataCache.h"

@interface FESelectCategoryVC ()<UICollectionViewDataSource, UICollisionBehaviorDelegate, UICollectionViewDelegateFlowLayout>
@property (strong, nonatomic) IBOutlet UICollectionView *categoryCollectionView;
@property (strong, nonatomic) IBOutlet UIButton *goBasket;
@property (strong, nonatomic) NSArray *productList;
@property (strong, nonatomic) NSMutableArray *selectProduct;
@end

@implementation FESelectCategoryVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = kString(@"添加待洗物品");
    self.view.backgroundColor = kThemeColor;
    self.selectProduct = [NSMutableArray new];
    [self refreshButton];

    __weak typeof(self) weakself = self;
    [[FEDataCache sharedInstance] getProductForID:self.fatherID block:^(NSArray *list) {
        weakself.productList = list;
        NSPredicate *pre = [NSPredicate predicateWithFormat:@"SELF in %@",list];
        [weakself.selectProduct addObjectsFromArray:[[FEDataCache sharedInstance].selectProducts filteredArrayUsingPredicate:pre]];
        [weakself.categoryCollectionView reloadData];
    }];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - UICollectionDataSource
-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    
    FEProduct *product = self.productList[indexPath.row];
    UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"categoryItemCell" forIndexPath:indexPath];
    UILabel *label = (UILabel *)[cell viewWithTag:1];
    label.text = product.product_name;
    
    UIImageView *imageView = (UIImageView *)[cell viewWithTag:2];
    [imageView sd_setImageWithURL:[NSURL URLWithString:kImageURL(product.img)] placeholderImage:[UIImage imageNamed:@"loading_small_image"]];
    
    UIImageView *checkImageView = (UIImageView *)[cell viewWithTag:3];
    if ([self.selectProduct containsObject:self.productList[indexPath.row]]) {
        checkImageView.image = [UIImage imageNamed:@"checkbox_on"];
    }else{
        checkImageView.image = nil;//[UIImage imageNamed:@"checkbox_off"];
    }
    
    UILabel *priceLabel = (UILabel *)[cell viewWithTag:4];
    priceLabel.text = [NSString stringWithFormat:@"￥%@",product.price];
    
    return cell;
}

-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    return self.productList.count;
}

#pragma mark - UICollectionViewDelegate
-(void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{
    if ([self.selectProduct containsObject:self.productList[indexPath.row]]) {
        [self.selectProduct removeObject:self.productList[indexPath.row]];
        [[FEDataCache sharedInstance] removeSelectProduct:self.productList[indexPath.row]];
    }else{
        [self.selectProduct addObject:self.productList[indexPath.row]];
        [[FEDataCache sharedInstance] addSelectProduct:self.productList[indexPath.row]];
    }
    [self.categoryCollectionView reloadItemsAtIndexPaths:@[indexPath]];
    [self refreshButton];
}

-(void)refreshButton{
    
    [self.goBasket setTitle:[NSString stringWithFormat:@"%@（%ld）",kString(@"洗衣篮"),(long)[FEDataCache sharedInstance].selectProducts.count] forState:UIControlStateNormal];
}

- (IBAction)goBasket:(id)sender {

    UIViewController *controller = [[[self.tabBarController viewControllers] objectAtIndex:1] topViewController];
    if ([controller isKindOfClass:[FEBasketVC class]]) {
        
        [((FEBasketVC *)controller) shoulRefresh];
        [self.tabBarController setSelectedIndex:1];
        [self.navigationController popViewControllerAnimated:NO];
    }
}

#pragma mark - UICollectionViewDelegateFlowLayout
- (UIEdgeInsets)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout insetForSectionAtIndex:(NSInteger)section{
    CGFloat height = [UIScreen mainScreen].bounds.size.height;
    if (height == 667) {
        return UIEdgeInsetsMake(20, 20, 20, 20);
    }else if(height == 736){
        return UIEdgeInsetsMake(25, 25, 25, 25);
    }else{
        return UIEdgeInsetsMake(5, 5, 5, 5);
    }
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
