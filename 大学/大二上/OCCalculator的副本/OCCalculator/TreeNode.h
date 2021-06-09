//
//  TreeNode.h
//  OCCalculator
//
//  Created by MacBook pro on 2020/12/18.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TreeNode : NSObject

@property (nonatomic, copy) NSString *data;

@property (nonatomic, strong) TreeNode *leftTreeNode;

@property (nonatomic, strong) TreeNode *rightTreeNode;


@end

NS_ASSUME_NONNULL_END
