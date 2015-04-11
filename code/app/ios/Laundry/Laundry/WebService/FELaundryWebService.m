//
//  FELaundryWebService.m
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//


#import "FELaundryWebService.h"
#import "FELaundryDefine.h"

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
        NSLog(@"response %@",responseObject);
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
        
//        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"错误" message:[self getErrorCode:response.errorCode.stringValue] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles: nil];
        kAlert([self getErrorCode:response.errorCode.stringValue], nil);
//        [alert show];
    }
}

-(NSString *)getErrorCode:(NSString *)code{
    static NSDictionary *errorCode = NULL;
    if (!errorCode) {
        NSString *plistPath = [[NSBundle mainBundle] pathForResource:@"ErrorCode" ofType:@"plist"];
        errorCode = [[NSMutableDictionary alloc] initWithContentsOfFile:plistPath];
    }
    return errorCode[code];
}

@end
