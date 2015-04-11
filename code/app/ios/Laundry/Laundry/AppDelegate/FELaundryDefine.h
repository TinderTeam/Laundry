//
//  FELaundryDefine.h
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#ifndef Laundry_FELaundryDefine_h
#define Laundry_FELaundryDefine_h
#import "THud.h"

#define kString(_S)                            NSLocalizedString(_S, @"")

// App infomation
#define kDidLaunchedFirstTime(_V)              [NSString stringWithFormat:@"DidLaunchedFirstTime V-%@",_V]
#define	kAppVersion                            [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleVersion"]
#define	kAppIdentifier                         [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleIdentifier"]
#define kAppBuildVersion                       [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleShortVersionString"]
#define kRateTimes                            [[[NSBundle mainBundle] objectForInfoDictionaryKey:@"RateTimes"] integerValue]
#define kAppItunesID                           [[NSBundle mainBundle] objectForInfoDictionaryKey:@"AppItunesIdentifier"]

//NSUserDefaults

#define kUserDefaults                          [NSUserDefaults standardUserDefaults]
#define kUserDefaultsObjectForKey(_KEY)        [kUserDefaults objectForKey:_KEY]
#define kUserDefaultsSetObjectForKey(_O,_KEY)  [kUserDefaults setObject:_O forKey:_KEY]
#define kUserDefaultsRemoveForKey(_KEY)        [kUserDefaults removeObjectForKey:_KEY]
#define kUserDefaultsSync                      [kUserDefaults synchronize]


#define SYSTEM_VERSION_LESS_THAN(v)                 ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] == NSOrderedAscending)
#define SYSTEM_VERSION_LESS_THAN_OR_EQUAL_TO(v)     ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] != NSOrderedDescending)
//[[THud sharedInstance] disPlayMessage:_M]
#define kAlert(_M,_C) [[THud sharedInstance] disPlayMessage:_M];
//{GAAlertAction *action = [GAAlertAction actionWithTitle:@"确定" action:^{ \
//\
//}];\
//[GAAlertObj showAlertWithTitle:@"提示" message:_M actions:@[action] inViewController:_C];}

#endif
