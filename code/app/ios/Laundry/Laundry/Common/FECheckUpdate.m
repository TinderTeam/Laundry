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

+(void)load{
    [[FECheckUpdate sharedInstance] chechUpdate:nil];
}

+(FECheckUpdate *)sharedInstance{
    static FECheckUpdate *instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [[FECheckUpdate alloc] init];
    });
    return instance;
}

-(void)chechUpdate:(void (^)(NSError *error,NSString *version,NSString *versionURL))complete{
    
    if (self.lastVersion) {
        if (complete) {
            complete(nil,self.lastVersion,self.versionURLString);
        }
    }
    
    NSURL *url = [NSURL URLWithString:kItunesURL];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    
    AFHTTPRequestOperation *operation = [[AFHTTPRequestOperation alloc] initWithRequest:request];
    [operation setCompletionBlockWithSuccess:^(AFHTTPRequestOperation *operation, id responseObject) {
        NSDictionary *dict = [NSJSONSerialization JSONObjectWithData:responseObject options:NSJSONReadingMutableContainers error:nil];
        
        if (dict && [dict[@"resultCount"] integerValue] != 0) {
            NSArray *resultArray = [dict objectForKey:@"results"];
            NSDictionary *resultDict = [resultArray objectAtIndex:0];
            NSString *newVersion = [resultDict objectForKey:@"version"];
            self.lastVersion = newVersion;
            NSString *urlstring = [[resultDict objectForKey:@"trackViewUrl"] copy];
            self.versionURLString = urlstring;
        }
        if (complete) {
            complete(nil,self.lastVersion,self.versionURLString);
        }
        
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"");
        if (complete) {
            complete(error,self.lastVersion,self.versionURLString);
        }
    }];
    [operation start];
    
}
@end
