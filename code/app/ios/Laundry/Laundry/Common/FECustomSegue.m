//
//  FECustomSegue.m
//  Laundry
//
//  Created by Seven on 15-1-16.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECustomSegue.h"

@implementation FECustomSegue

-(id)init{
    self = [super init];
    if (self) {
        _animation = YES;
    }
    return self;
}

-(id)initWithIdentifier:(NSString *)identifier source:(UIViewController *)source destination:(UIViewController *)destination{
    self = [super initWithIdentifier:identifier source:source destination:destination];
    if (self) {
        _animation = YES;
    }
    return self;
}

-(void)perform{
    [((UIViewController *)self.sourceViewController).navigationController pushViewController:self.destinationViewController animated:self.animation];
}

@end
