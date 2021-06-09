//
//  Stack.m
//  OCCalculator
//
//  Created by MacBook pro on 2020/12/17.
//

#import "Stack.h"
#import "TreeNode.h"

@interface Stack()


@property (nonatomic, strong) NSArray *operatorArray;

@property (nonatomic, copy)   NSString *chars;

@property (nonatomic, strong) TreeNode *treeNode;

@end


@implementation Stack

- (TreeNode *)treeNode {
    if (!_treeNode) {
        _treeNode = [[TreeNode alloc] init];
    }
    return _treeNode;
}
- (NSArray *)operatorArray {
    if (!_operatorArray) {
        _operatorArray = @[@"+",@"-",@"×",@"÷"];
    }
    return _operatorArray;
}
//栈的初始化
- (NSMutableArray *)array {
    if (!_array) {
        _array = [NSMutableArray array];
    }
    return _array;
}

- (NSMutableArray *)tempArray {
    if (!_tempArray) {
        _tempArray = [NSMutableArray array];
    }
    return _tempArray;
}

//操作符入栈
- (BOOL)PushIntoStack:(NSString *)str {
    if (self.array.count == 0 || [self.operatorArray indexOfObject:str]/2 > [self.operatorArray indexOfObject:self.array[self.array.count - 1]]/2 ) {
        //栈为空或者操作符的优先级比栈顶元素高
        [self.array addObject:str];
        return YES;
    }
    return NO;
}
//数字入栈
- (void)PushmIntoStack:(id)ids{
    [self.tempArray addObject:ids];
}
//判断栈是否为空
- (BOOL)StackIsEmpty {
    if (self.array.count == 0) {
        return YES;
    }
    return NO;
}
//出栈
- (id)PopStack {
    if (self.array.count) {
        self.chars = self.array.lastObject;
        [self.array removeLastObject];
        return self.chars;
    }
    return nil;
}
//出栈
- (id)PopTempStack {
    if (self.tempArray.count) {
        if ([self.tempArray.lastObject isKindOfClass:[NSString class]]) {
            self.chars = self.tempArray.lastObject;
            [self.tempArray removeLastObject];
            return self.chars;
        }else {
            self.treeNode = self.tempArray.lastObject;
            [self.tempArray removeLastObject];
            return self.treeNode;
        }
    }
    return nil;
}
//获取栈顶元素
- (id)getStackHead {
    if (self.tempArray.count) {
        if ([self.tempArray.lastObject isKindOfClass:[NSString class]]) {
            self.chars = self.tempArray.lastObject;
            return self.chars;
        }else {
            self.treeNode = self.tempArray.lastObject;
            return self.treeNode;
        }
    }
    return nil;
}
//判断优先级
- (BOOL)isHigh:(NSString *)chars {
    if ([self.operatorArray indexOfObject:chars]/2 > [self.operatorArray indexOfObject:self.array[self.array.count - 1]]/2) {
        return YES;
    }
    return NO;
}
//销毁栈
- (void)DestroyStack {
    [self.array removeAllObjects];
    [self.tempArray removeAllObjects];
}
@end
