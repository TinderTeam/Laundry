//
//  FEGetCompanyResponse.h
//  Laundry
//
//  Created by Seven on 15-1-14.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEBaseResponse.h"
#import "FECompany.h"

@interface FEGetCompanyResponse : FEBaseResponse
@property (nonatomic, strong, readonly) FECompany *obj;

@end
