//
//  FELaundryLandingPage.m
//  Laundry
//
//  Created by Seven on 15-2-5.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FELaundryLandingPage.h"
#import "AppDelegate.h"

@interface FELaundryLandingPage (){
    NSArray *_images;
}
@property (strong, nonatomic) IBOutlet UIScrollView *scrollView;
@property (strong, nonatomic) IBOutlet UIPageControl *page;
@property (strong, nonatomic) IBOutlet UIButton *startButton;

@end

@implementation FELaundryLandingPage

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
//    self.edgesForExtendedLayout = UIRectEdgeNone;
//    self.extendedLayoutIncludesOpaqueBars = YES;
    if (self.isFromSetting) {
        self.title = @"新手指导";
        self.startButton.hidden = YES;
    }
    self.page.currentPageIndicatorTintColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"image_selected"]];
    self.page.pageIndicatorTintColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"image_unselected"]];
    
    _images = @[@"help1",@"help2",@"help3",@"help4",@"help5"];
    self.page.numberOfPages = _images.count;
    self.scrollView.contentSize = CGSizeMake(self.view.bounds.size.width * _images.count, 20);
    int i = 0;
    for (NSString *name in _images) {
        UIImageView *imageView = [[UIImageView alloc] initWithFrame:CGRectMake(i*self.view.bounds.size.width, 0, self.view.bounds.size.width, self.view.bounds.size.height)];
        
        imageView.image = [UIImage imageNamed:name];
        [self.scrollView addSubview:imageView];
        i++;
    }
}

#pragma mark - UIScrollViewDelegate
-(void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView{
    NSInteger page = scrollView.contentOffset.x / self.view.bounds.size.width;
    if (page == _images.count - 1) {
        [UIView animateWithDuration:0.2 animations:^{
            
        }];
    }else{
        [UIView animateWithDuration:0.2 animations:^{
           
        }];
    }
    
    self.page.currentPage = page;
}

- (IBAction)start:(id)sender {
    [self start];
}

-(void)start{
    if (!kUserDefaultsObjectForKey(kDidLaunchedFirstTime(kAppVersion))) {
        kUserDefaultsSetObjectForKey(@(YES), kDidLaunchedFirstTime(kAppVersion));
        kUserDefaultsSync;
    }
    [[AppDelegate sharedDelegate] loadMain];
}

-(void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate{
    if (self.isFromSetting) {
        return;
    }
    if (scrollView.contentOffset.x > self.view.bounds.size.width * (_images.count - 1) + self.view.bounds.size.width / 4.0) {
        [self start];
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
