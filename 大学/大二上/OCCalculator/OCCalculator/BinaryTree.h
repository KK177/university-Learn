//
//  BinaryTree.h
//  OCCalculator
//
//  Created by MacBook pro on 2020/12/18.
//

#import <Foundation/Foundation.h>
#import "TreeNode.h"

NS_ASSUME_NONNULL_BEGIN

@interface BinaryTree : NSObject

@property (nonatomic, strong) TreeNode *rootNode;

@property (nonatomic, copy)   NSString *chars;

//初始化二叉树
- (id)buildTree:(NSString *)rootData;

//插入元素到左子树
- (id)insertToLeftTree:(NSString *)leftNode : (TreeNode *)treeNode;

//插入结点到右子树
- (void)insertNodeToRightTree:(TreeNode *)treeNode;

//插入结点到左子树
- (void)insertNodeToLeftTree:(TreeNode *)treeNode;

//插入元素到右子树
- (id)insertToRightTree:(NSString *)rightNode : (TreeNode *)treeNode;

//判断左子树是否为空
- (BOOL)leftTreeIsEmpty:(TreeNode *)leftNode;

//判断右子树是否为空
- (BOOL)rightTreeIsEmpty:(TreeNode *)rightNode;

//递归遍历二叉树输出结果
- (float)inOrderTraverse:(TreeNode *)treeNode;

//销毁根结点
- (void)DestoryTree;

//更新根结点
- (void)changeRootNode:(TreeNode *)node;
@end

NS_ASSUME_NONNULL_END
