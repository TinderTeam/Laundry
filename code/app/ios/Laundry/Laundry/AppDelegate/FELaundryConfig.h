//
//  FELaundryConfig.h
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#ifndef Laundry_FELaundryConfig_h
#define Laundry_FELaundryConfig_h

#define kColor(_R,_G,_B,_A)                    [UIColor colorWithRed:_R / 255.0f green:_G / 255.0f blue: _B / 255.0f alpha:_A]

#define kThemeColor             kColor(20, 56, 68, 1)
#define kThemeGrayColor             kColor(230, 230, 230, 1)
#define kButtonColor            kColor(23,157,197,1)

#define kServerIP               @"120.24.217.173"
#define kServerPort             @"80"
#define kBasePath               @"/Laundry"
#define kImagePath              @"/Public/Fuego/uploads/"
#define kAliPayPath             @"/index.php/AlipayNotify/GetNotify"
#define kJoinPath               @"/Laundry.php/ADManage/joinUS.html"
#define kIntroductPath          @"/Laundry.php/ADManage/introduct.html"
#define kAttentionPath          @"/Laundry.php/ADManage/attention.html"

#define kImageURL(_A)           [NSString stringWithFormat:@"http://%@:%@%@%@%@",kServerIP,kServerPort,kBasePath,kImagePath,_A]//[@"http://120.24.217.173:9000/Laundry/Public/Fuego/uploads/" stringByAppendingString:_A]
#define __SERVICE_BASE_URL    [NSString stringWithFormat:@"http://%@:%@%@",kServerIP,kServerPort,kBasePath]//@"http://120.24.217.173:80/Laundry"
#define kJoinURL                [NSString stringWithFormat:@"%@%@",__SERVICE_BASE_URL,kJoinPath]
#define kIntroductURL           [NSString stringWithFormat:@"%@%@",__SERVICE_BASE_URL,kIntroductPath]
#define kAttentionURL           [NSString stringWithFormat:@"%@%@",__SERVICE_BASE_URL,kAttentionPath]

#define kAliPayURL              [NSString stringWithFormat:@"%@%@",__SERVICE_BASE_URL,kAliPayPath]

//login user
#define kLoginUserKey           @"user"
#define kUserTokenKey           @"token"
#define kCustomerKey               @"customer"

#define kLoginUser              kUserDefaultsObjectForKey(kLoginUserKey)
#define kUserToken              kUserDefaultsObjectForKey(kUserTokenKey)
#define kCustomer               kUserDefaultsObjectForKey(kCustomerKey)

#define kNotificationUserDidLogin           @"notificationUserDidLogin"
#define kNotificationUserDidChange           @"notificationUserDidChange"

#define kCall(_PHONE)       [[UIApplication sharedApplication] openURL:[NSURL URLWithString:[@"tel://" stringByAppendingString:_PHONE]]];

#endif
