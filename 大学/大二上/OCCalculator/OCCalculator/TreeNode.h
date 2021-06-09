//
//  TreeNode.h
//  OCCalculator
//
//  Created by MacBook pro on 2020/12/18.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TreeNode : NSObject

@property (nonatomic, copy) NSString *data;//当前结点的数值

@property (nonatomic, strong) TreeNode *leftTreeNode;//当前结点的左子树

@property (nonatomic, strong) TreeNode *rightTreeNode;//当前结点的右子树


@end

NS_ASSUME_NONNULL_END
