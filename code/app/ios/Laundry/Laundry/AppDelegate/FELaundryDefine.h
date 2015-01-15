//
//  FELaundryDefine.h
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#ifndef Laundry_FELaundryDefine_h
#define Laundry_FELaundryDefine_h

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


#endif
