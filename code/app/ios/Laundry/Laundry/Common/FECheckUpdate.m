//
//  FECheckUpdate.m
//  Laundry
//
//  Created by Seven on 15-2-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//



#define kAPPID @"963489193"
#define kItunesURL [NSString stringWithFormat:@"http://itunes.apple.com/lookup?id=%@",kAPPID]

#import "FECheckUpdate.h"
#import <AFNetworking/AFNetworking.h>

@implementation FECheckUpdate
+(FECheckUpdate *)sharedInstance{
    static FECheckUpdate *instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [[FECheckUpdate alloc] init];
    });
    return instance;
}

-(void)chechUpdate:(void (^)(NSError *error,id response))complete{
    NSURL *url = [NSURL URLWithString:kItunesURL];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    
    AFHTTPRequestOperation *operation = [[AFHTTPRequestOperation alloc] initWithRequest:request];
    [operation setCompletionBlockWithSuccess:^(AFHTTPRequestOperation *operation, id responseObject) {
        NSDictionary *dict = [NSJSONSerialization JSONObjectWithData:responseObject options:NSJSONReadingMutableContainers error:nil];
        complete(nil,dict);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"");
        complete(error,nil);
    }];
    [operation start];
    
}
@end
