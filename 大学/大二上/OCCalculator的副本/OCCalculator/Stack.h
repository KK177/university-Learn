//
//  Stack.h
//  OCCalculator
//
//  Created by MacBook pro on 2020/12/17.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface Stack : NSObject

@property (nonatomic, strong) NSMutableArray *array;

@property (nonatomic, strong) NSMutableArray *tempArray;

//操作符入栈
- (BOOL)PushIntoStack:(NSString *)str;

//数字入栈
- (void)PushmIntoStack:(id)ids;

//判断栈是否为空
- (BOOL)StackIsEmpty;

//出栈
- (id)PopStack;

//出栈
- (id)PopTempStack;

//获取栈顶元素
- (id)getStackHead;

//判断优先级
- (BOOL)isHigh:(NSString *)chars;

//销毁栈
- (void)DestroyStack;

@end

NS_ASSUME_NONNULL_END
