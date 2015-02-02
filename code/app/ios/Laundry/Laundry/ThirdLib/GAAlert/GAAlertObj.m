//
//  GAAlertObj.m
//  GreenApp
//
//  Created by sven on 14/11/20.
//  Copyright (c) 2014年 allgateways. All rights reserved.
//

#import "GAAlertObj.h"
#import <UIAlertView-Blocks/UIAlertView+Blocks.h>

#define IOSVersionUp8   (((float)[[[UIDevice currentDevice] systemVersion] floatValue]) >= 8.0)

@implementation GAAlertAction

+ (instancetype)actionWithTitle:(NSString *)title action:(void (^)())action {
    GAAlertAction *act = [GAAlertAction new];
    act.title = title;
    act.action = action;
    return act;
}

@end

@interface GAAlertObj ()

//use in ios8 up
@property (strong, nonatomic) UIAlertController *alertController;
//use in ios8 down
@property (strong, nonatomic) UIAlertView *alertView;

@end

@implementation GAAlertObj

+ (instancetype)alertWithTitle:(NSString *)title message:(NSString *)message {
    GAAlertObj *obj = [GAAlertObj new];
    obj.title = title;
    obj.message = message;
    if (IOSVersionUp8) {
        obj.alertController = [UIAlertController alertControllerWithTitle:title message:message preferredStyle:UIAlertControllerStyleAlert];
    }else {
        obj.alertView = [[UIAlertView alloc] initWithTitle:title message:message cancelButtonItem:nil otherButtonItems:nil, nil];
    }
    
    return obj;
}

+ (instancetype)alertWithTitle:(NSString *)title message:(NSString *)message actions:(NSArray *)actions {
    GAAlertObj *obj = [self alertWithTitle:title message:message];
    for (GAAlertAction *action in actions) {
        [obj addAction:action];
    }
    return obj;
}

+ (instancetype)showAlertWithTitle:(NSString *)title message:(NSString *)message actions:(NSArray *)actions inViewController:(UIViewController *)vc{
    GAAlertObj *obj = [self alertWithTitle:title message:message];
    for (GAAlertAction *action in actions) {
        [obj addAction:action];
    }
    [obj showInViewController:vc];
    return obj;
}

- (void)addAction:(GAAlertAction *)action {
    if (![action isKindOfClass:[GAAlertAction class]]) {
        return;
    }
    if (IOSVersionUp8) {
        UIAlertAction *act = [UIAlertAction actionWithTitle:action.title style:UIAlertActionStyleDefault handler:action.action];
        [self.alertController addAction:act];
    }else {
        RIButtonItem *item = [RIButtonItem itemWithLabel:action.title];
        item.action = action.action;
        [self.alertView addButtonItem:item];
    }
}

- (void)showInViewController:(UIViewController *)vc{
    if (IOSVersionUp8) {
        if (!self.alertController.actions.count) {
            UIAlertAction *act = [UIAlertAction actionWithTitle:NSLocalizedString(@"OK", nil) style:UIAlertActionStyleDefault handler:^(UIAlertAction *action) {
                
            }];
            [self.alertController addAction:act];
        }
//        if ([self isKindOfClass:[UIViewController class]]) {
//            [(UIViewController *)self presentViewController:self.alertController animated:YES completion:^{
//                
//            }];
//        }else {
            [vc presentViewController:self.alertController animated:YES completion:^{
                
            }];
//        }
    }else {
        if (!self.alertView.numberOfButtons) {
            RIButtonItem *item = [RIButtonItem itemWithLabel:NSLocalizedString(@"OK", nil)];
            [self.alertView addButtonItem:item];
        }
        [self.alertView show];
    }
}

@end
