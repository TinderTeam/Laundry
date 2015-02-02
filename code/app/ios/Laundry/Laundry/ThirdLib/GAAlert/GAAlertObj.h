//
//  GAAlertObj.h
//  GreenApp
//
//  Created by sven on 14/11/20.
//  Copyright (c) 2014年 allgateways. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface GAAlertAction : NSObject

@property (strong, nonatomic) NSString *title;
@property (strong, nonatomic) void (^action)();

+ (instancetype)actionWithTitle:(NSString *)title action:(void(^)())action;

@end


@interface GAAlertObj : NSObject

+ (instancetype)alertWithTitle:(NSString *)title message:(NSString *)message;

+ (instancetype)alertWithTitle:(NSString *)title message:(NSString *)message actions:(NSArray *)actions;

//+ (instancetype)showAlertWithTitle:(NSString *)title message:(NSString *)message actions:(NSArray *)actions;

+ (instancetype)showAlertWithTitle:(NSString *)title message:(NSString *)message actions:(NSArray *)actions inViewController:(UIViewController *)vc;

- (void)addAction:(GAAlertAction *)action;

//- (void)show;
- (void)showInViewController:(UIViewController *)vc;

@property (strong, nonatomic) NSString *title;
@property (strong, nonatomic) NSString *message;

@end
