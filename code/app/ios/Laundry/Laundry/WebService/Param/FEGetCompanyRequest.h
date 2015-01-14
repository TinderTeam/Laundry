//
//  FEGetCompanyRequest.h
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseRequest.h"

@interface FEGetCompanyRequest : FEBaseRequest
@property (nonatomic, strong, readonly) NSNumber *obj;//公司ID；

-(id)initWithCid:(NSNumber *)cid;
@end
