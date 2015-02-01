//
//  FEAlipay.m
//  Laundry
//
//  Created by Seven on 15-1-28.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEAlipay.h"
#import <AlipaySDK/AlipaySDK.h>
#import "Order.h"
#import "FEDataCache.h"
#import "DataSigner.h"
#import "FEOrder.h"

@implementation FEAlipay

+(FEAlipay *)sharedInstance{
    static FEAlipay *instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [[FEAlipay alloc] init];
    });
    return instance;
}


//#pragma mark -
//#pragma mark   ==============产生随机订单号=============
//
//- (NSString *)generateTradeNO
//{
//    static int kNumber = 15;
//    
//    NSString *sourceStr = @"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//    NSMutableString *resultStr = [[NSMutableString alloc] init];
//    srand(time(0));
//    for (int i = 0; i < kNumber; i++)
//    {
//        unsigned index = rand() % [sourceStr length];
//        NSString *oneStr = [sourceStr substringWithRange:NSMakeRange(index, 1)];
//        [resultStr appendString:oneStr];
//    }
//    return resultStr;
//}

-(void)payWithOrder:(FEOrder *)forder complete:(void (^)(NSDictionary *result))complete{
    
    //将商品信息赋予AlixPayOrder的成员变量
    Order *order = [[Order alloc] init];
    order.partner = [FEDataCache sharedInstance].company.alipay_partner;
    order.seller = [FEDataCache sharedInstance].company.alipay_seller;
    order.tradeNO = forder.order_code; //订单ID（由商家自行制定）
    order.productName = forder.order_name; //商品标题
    order.productDescription = @"测试body"; //商品描述
    order.amount = [NSString stringWithFormat:@"%.2f",forder.total_price.floatValue]; //商品价格
    order.notifyURL =  @"http://www.xxx.com"; //回调URL
    
    order.service = @"mobile.securitypay.pay";
    order.paymentType = @"1";
    order.inputCharset = @"utf-8";
    order.itBPay = @"30m";
    order.showUrl = @"m.alipay.com";
    
    //应用注册scheme,在AlixPayDemo-Info.plist定义URL types
    NSString *appScheme = @"LaundryAlipay";
    
    //将商品信息拼接成字符串
    NSString *orderSpec = [order description];
    NSLog(@"orderSpec = %@",orderSpec);
    
    id<DataSigner> signer = CreateRSADataSigner([FEDataCache sharedInstance].company.alipay_private_key);
    NSString *signedString = [signer signString:orderSpec];
    
    //将签名成功字符串格式化为订单字符串,请严格按照该格式
    NSString *orderString = nil;
    if (signedString != nil) {
        orderString = [NSString stringWithFormat:@"%@&sign=\"%@\"&sign_type=\"%@\"",
                       orderSpec, signedString, @"RSA"];
        
        [[AlipaySDK defaultService] payOrder:orderString fromScheme:appScheme callback:^(NSDictionary *resultDic) {
            NSLog(@"reslut = %@",resultDic);
            if (complete) {
                complete(resultDic);
            }
        }];
        
    }
    
}

@end
