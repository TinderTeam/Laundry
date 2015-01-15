//
//  FEButton.m
//  SmartHome
//
//  Created by Seven on 14-11-2.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEButton.h"
#import "UIImage+LogN.h"

@implementation FEButton

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/
-(id)initWithCoder:(NSCoder *)aDecoder{
    self = [super initWithCoder:aDecoder];
    if (self) {
        self.layer.cornerRadius = 5;
        self.layer.masksToBounds = YES;
        [self setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        [self setBackgroundImage:[UIImage imageFromColor:kThemeColor] forState:UIControlStateNormal];
    }
    return self;
}

+(id)buttonWithType:(UIButtonType)buttonType{
    UIButton *btn = [UIButton buttonWithType:buttonType];
    btn.layer.cornerRadius = 5;
    btn.layer.masksToBounds = YES;
    [btn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [btn setBackgroundImage:[UIImage imageFromColor:kThemeColor] forState:UIControlStateNormal];
    return btn;
}

//-(id)init{
//    self = [super init];
//    if (self) {
//        self.layer.cornerRadius = 5;
//        self.layer.masksToBounds = YES;
//        [self setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
//        [self setBackgroundImage:[UIImage imageFromColor:FEButtonColor] forState:UIControlStateNormal];
//    }
//    return self;
//}

@end
