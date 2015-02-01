//
//  FEBasketVC.m
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBasketVC.h"
#import "FEBasketItemCell.h"
#import "FEDataCache.h"
#import "FEPickerView.h"
#import "AppDelegate.h"
#import "FEHomePageVC.h"
#import "UIImage+LogN.h"


@interface FEBasketVC ()<UITextFieldDelegate,FEPickerViewDelegate>

@property (nonatomic, strong) NSMutableArray *productList;
@property (nonatomic, strong) UITextField *textField;
@property (strong, nonatomic) IBOutlet UIButton *toCategory;
@property (strong, nonatomic) IBOutlet UITableView *tableView;
@property (strong, nonatomic) IBOutlet UILabel *totalLabel;
@property (strong, nonatomic) IBOutlet UIButton *submitButton;
@property (strong, nonatomic) IBOutlet UIButton *addButton;

@end

@implementation FEBasketVC

-(id)initWithCoder:(NSCoder *)aDecoder{
    self = [super initWithCoder:aDecoder];
    if (self) {
        self.title = kString(@"洗衣篮");
        UITabBarItem *tabitem = [[UITabBarItem alloc] initWithTitle:kString(@"洗衣篮") image:[UIImage imageNamed:@"tab_icon_cart_normal"] selectedImage:[UIImage imageNamed:@"tab_icon_cart_pressed"]];
        self.tabBarItem = tabitem;
        _productList = [NSMutableArray new];
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self.toCategory setBackgroundImage:[UIImage imageFromColor:kColor(23, 157, 197, 1)] forState:UIControlStateNormal];
    [self refreshUI];
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    [self refreshUI];
}

-(void)shoulRefresh{
    [self.tableView reloadData];
    [self refreshUI];
}

-(void)appendProduct:(NSArray *)products{
    [_productList removeAllObjects];
    [_productList addObjectsFromArray:products];
    [self.tableView reloadData];
}

#pragma mark - UITableViewDelegate
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    FEBasketItemCell *cell = [tableView dequeueReusableCellWithIdentifier:@"basketItemCell" forIndexPath:indexPath];
    FEProduct *product = [FEDataCache sharedInstance].selectProducts[indexPath.row];
    
    [cell configWithProduct:product number:[[FEDataCache sharedInstance] productNumber:product]];
    return cell;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [FEDataCache sharedInstance].selectProducts.count;
}
- (IBAction)deleteItem:(id)sender {
    
    FEBasketItemCell *cell = (FEBasketItemCell *)[[sender superview] superview];
//    NSIndexPath *indexPath = [self.tableView indexPathForCell:cell];
    [[FEDataCache sharedInstance] removeSelectProduct:cell.product];
    [self.tableView reloadData];
    [self refreshUI];
    
}

- (IBAction)toCategoryAction:(id)sender {
    UIViewController *controller = [[[self.tabBarController viewControllers] objectAtIndex:0] topViewController];
    if ([controller isKindOfClass:[FEHomePageVC class]]) {
        
//        [((FEHomePageVC *)controller) shoulRefresh];
        [self.tabBarController setSelectedIndex:0];
        [((FEHomePageVC *)controller) toCategory];
    }
}

#pragma mark - UITextFieldDelegate
-(BOOL)textFieldShouldBeginEditing:(UITextField *)textField{
    self.textField = textField;
    FEPickerView *pick = [[FEPickerView alloc] initFromView:[[AppDelegate sharedDelegate].window viewWithTag:0]];
    pick.delegate = self;
    [pick show];
    return NO;
}

#pragma mark - FEPickerViewDelegate
-(void)pickerDidSelected:(NSInteger)number{
    
    FEBasketItemCell *cell = (FEBasketItemCell *)[[self.textField superview] superview];
    FEProduct *product = cell.product;
    [cell configWithProduct:product number:@(number + 1)];
    
    [[FEDataCache sharedInstance] setProduct:product number:@(number + 1)];
    
    [self refreshUI];
    
}

-(NSInteger)pickerNumber:(FEPickerView *)picker{
    return 20;
}
-(NSString *)pickerStringAtIndex:(NSInteger)index{
    return @(index + 1).stringValue;
}

-(void)refreshUI{
    NSArray *products = [FEDataCache sharedInstance].selectProducts;
    float totalPrice = 0;
    NSInteger totalNumber = 0;
    for (FEProduct *product in products) {
        NSInteger number = [[FEDataCache sharedInstance] productNumber:product].integerValue;
        totalNumber += number;
        totalPrice += product.price.floatValue * number;
    }
    self.totalLabel.text = [NSString stringWithFormat:@"总量:%ld,共计:%.2f",(long)totalNumber,totalPrice];
    if (products.count) {
        self.addButton.hidden = NO;
        self.submitButton.hidden = NO;
        self.totalLabel.hidden = NO;
        self.toCategory.hidden = YES;
    
    }else{
        self.toCategory.hidden = NO;
        self.addButton.hidden = YES;
        self.totalLabel.hidden = YES;
        self.submitButton.hidden = YES;
    }
    
}
- (IBAction)nextAction:(id)sender {
    if (kLoginUser) {
        if ([FEDataCache sharedInstance].selectProducts.count) {
            [self performSegueWithIdentifier:@"orderSegue" sender:nil];
        }
    }else{
        [self performSegueWithIdentifier:@"signinSegue" sender:nil];
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
