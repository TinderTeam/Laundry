//
//  FELaundryWebService.h
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "AFHTTPRequestOperationManager.h"
#import "FEBaseRequest.h"
#import "FEBaseResponse.h"

@interface FELaundryWebService : AFHTTPRequestOperationManager
+(FELaundryWebService *)sharedInstance;

-(AFHTTPRequestOperation *)request:(FEBaseRequest *)rdata responseClass:(Class)cl response:(void(^)(NSError *error, id response))block;

@end
