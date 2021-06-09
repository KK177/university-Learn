//
//  BinaryTree.m
//  OCCalculator
//
//  Created by MacBook pro on 2020/12/18.
//

#import "BinaryTree.h"

@implementation BinaryTree

//初始化二叉树
- (id)buildTree:(NSString *)rootData {
    self.rootNode = [[TreeNode alloc] init];
    self.rootNode.data = rootData;
    return self.rootNode;
}

//插入元素到左子树
- (id)insertToLeftTree:(NSString *)leftNode : (TreeNode *)treeNode {
    treeNode.leftTreeNode = [[TreeNode alloc] init];
    treeNode.leftTreeNode.data = leftNode;
    return treeNode.leftTreeNode;
}
//插入结点到右子树
- (void)insertNodeToRightTree:(TreeNode *)treeNode {
    treeNode.rightTreeNode = self.rootNode;
    self.rootNode = treeNode;
}
//插入结点到左子树
- (void)insertNodeToLeftTree:(TreeNode *)treeNode {
    treeNode.leftTreeNode = self.rootNode;
    self.rootNode = treeNode;
}
//插入元素到右子树
- (id)insertToRightTree:(NSString *)rightNode : (TreeNode *)treeNode {
    treeNode.rightTreeNode = [[TreeNode alloc] init];
    treeNode.rightTreeNode.data = rightNode;
    return treeNode.rightTreeNode;
}

//判断左子树是否为空
- (BOOL)leftTreeIsEmpty:(TreeNode *)leftNode {
    if (!leftNode.leftTreeNode.data.length) {
        return YES;
    }
    return NO;
}
//判断右子树是否为空
- (BOOL)rightTreeIsEmpty:(TreeNode *)rightNode {
    if (!rightNode.rightTreeNode.data.length) {
        return YES;
    }
    return NO;
}
//递归遍历二叉树
- (float)inOrderTraverse:(TreeNode *)treeNode {
    if (!treeNode.data.length) {
        return 0;
    }else {
        self.chars = treeNode.data;
        if ([self.chars isEqual:@"+"]) {
            //return [self inOrderTraverse:treeNode.leftTreeNode] + [self inOrderTraverse:treeNode.rightTreeNode];
            NSDecimalNumber *num1 = [NSDecimalNumber decimalNumberWithString:[NSString stringWithFormat:@"%f",[self inOrderTraverse:treeNode.leftTreeNode]]];
            NSDecimalNumber *num2 = [NSDecimalNumber decimalNumberWithString:[NSString stringWithFormat:@"%f",[self inOrderTraverse:treeNode.rightTreeNode]]];
            NSDecimalNumber *num = [num1 decimalNumberByAdding:num2];
            return num.floatValue;

        }
        if ([self.chars isEqual:@"-"]) {
            //return [self inOrderTraverse:treeNode.leftTreeNode] - [self inOrderTraverse:treeNode.rightTreeNode];
            NSDecimalNumber *num1 = [NSDecimalNumber decimalNumberWithString:[NSString stringWithFormat:@"%f",[self inOrderTraverse:treeNode.leftTreeNode]]];
            NSDecimalNumber *num2 = [NSDecimalNumber decimalNumberWithString:[NSString stringWithFormat:@"%f",[self inOrderTraverse:treeNode.rightTreeNode]]];
            NSDecimalNumber *num = [num1 decimalNumberBySubtracting:num2];
            return num.floatValue;
        }
        if ([self.chars isEqual:@"*"]) {
            //return [self inOrderTraverse:treeNode.leftTreeNode] * [self inOrderTraverse:treeNode.rightTreeNode];
            NSDecimalNumber *num1 = [NSDecimalNumber decimalNumberWithString:[NSString stringWithFormat:@"%f",[self inOrderTraverse:treeNode.leftTreeNode]]];
            NSDecimalNumber *num2 = [NSDecimalNumber decimalNumberWithString:[NSString stringWithFormat:@"%f",[self inOrderTraverse:treeNode.rightTreeNode]]];
            NSDecimalNumber *num = [num1 decimalNumberByMultiplyingBy:num2];
            return num.floatValue;
        }
        if ([self.chars isEqual:@"/"]) {
            //return [self inOrderTraverse:treeNode.leftTreeNode] / [self inOrderTraverse:treeNode.rightTreeNode];
            NSDecimalNumber *num1 = [NSDecimalNumber decimalNumberWithString:[NSString stringWithFormat:@"%f",[self inOrderTraverse:treeNode.leftTreeNode]]];
            NSDecimalNumber *num2 = [NSDecimalNumber decimalNumberWithString:[NSString stringWithFormat:@"%f",[self inOrderTraverse:treeNode.rightTreeNode]]];
            NSDecimalNumber *num = [num1 decimalNumberByDividingBy:num2];
            return num.floatValue;
        }else {
            //return treeNode.data.floatValue;
            NSDecimalNumber *num1 = [NSDecimalNumber decimalNumberWithString:[NSString stringWithFormat:@"%f",treeNode.data.floatValue]];
            return num1.floatValue;
        }
    }
}

//销毁根结点
- (void)DestoryTree {
    self.rootNode = [[TreeNode alloc] init];
}

//更新根结点
- (void)changeRootNode:(TreeNode *)node {
    self.rootNode = node;
}
@end

