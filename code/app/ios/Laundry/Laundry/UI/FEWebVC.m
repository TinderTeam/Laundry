//
//  FEWebVC.m
//  Laundry
//
//  Created by Seven on 15-2-5.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEWebVC.h"

@interface FEWebVC ()
@property (strong, nonatomic) IBOutlet UIWebView *webView;

@end

@implementation FEWebVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self.webView loadRequest:[NSURLRequest requestWithURL:[NSURL URLWithString:self.urlString]]];
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
