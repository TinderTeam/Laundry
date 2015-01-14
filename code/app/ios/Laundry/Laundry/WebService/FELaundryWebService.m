//
//  FELaundryWebService.m
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#define __SERVICE_BASE_URL @"http://120.24.217.173:9000/Laundry"

#import "FELaundryWebService.h"

@implementation FELaundryWebService

+(FELaundryWebService *)sharedInstance{
    static FELaundryWebService *instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [[FELaundryWebService alloc] initWithBaseURL:[NSURL URLWithString:__SERVICE_BASE_URL]];
    });
    return instance;
}

-(instancetype)initWithBaseURL:(NSURL *)url{
    self = [super initWithBaseURL:url];
    if (self) {
//        NSMutableSet *mset = [NSMutableSet setWithSet:self.responseSerializer.acceptableContentTypes];
//        [mset addObject:@"text/html"];
//        self.responseSerializer.acceptableContentTypes = mset;
        self.requestSerializer = [AFJSONRequestSerializer serializer];
    }
    return self;
}

-(AFHTTPRequestOperation *)request:(id)rdata responseClass:(Class)cl response:(void(^)(NSError *error, id response))block{
    return [self POST:[rdata method] parameters:[rdata dictionary] success:^(AFHTTPRequestOperation *operation, id responseObject) {
        id rsp = [[cl alloc] initWithResponse:responseObject];
        [self showerrorResponse:rsp];
        block(NULL,rsp);
        
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        block(error,NULL);
    }];
}

-(void)showerror:(NSError *)error{
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"EShoping" message:[NSString stringWithFormat:@"%@",error.localizedDescription] delegate:nil cancelButtonTitle:kString(@"OK") otherButtonTitles:nil];
    [alert show];
}

-(void)showerrorResponse:(FEBaseResponse *)response{
    
    if (response.errorCode.integerValue != 0) {
        
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"错误" message:response.errorCode.stringValue delegate:nil cancelButtonTitle:@"OK" otherButtonTitles: nil];
        [alert show];
    }
}

@end
