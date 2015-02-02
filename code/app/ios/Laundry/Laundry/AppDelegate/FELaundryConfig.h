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

#define kImageURL(_A)           [NSString stringWithFormat:@"http://%@:%@%@%@%@",kServerIP,kServerPort,kBasePath,kImagePath,_A]//[@"http://120.24.217.173:9000/Laundry/Public/Fuego/uploads/" stringByAppendingString:_A]
#define __SERVICE_BASE_URL    [NSString stringWithFormat:@"http://%@:%@%@",kServerIP,kServerPort,kBasePath]//@"http://120.24.217.173:80/Laundry"

//login user
#define kLoginUserKey           @"user"

#define kLoginUser              kUserDefaultsObjectForKey(kLoginUserKey)

#define kNotificationUserDidLogin           @"notificationUserDidLogin"

#define kCall(_PHONE)       [[UIApplication sharedApplication] openURL:[NSURL URLWithString:[@"tel://" stringByAppendingString:_PHONE]]];

#endif
