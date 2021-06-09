//
//  ViewController.m
//  OCCalculator
//
//  Created by MacBook pro on 2020/11/22.
//

//设备的宽高
#define SCREENWIDTH [UIScreen mainScreen].bounds.size.width
#define SCREENHEIGHT [UIScreen mainScreen].bounds.size.height

#import "ViewController.h"
#import <SDAutoLayout/SDAutoLayout.h>
#import "CollectionViewCell.h"
#import "Stack.h"
#import "TreeNode.h"
#import "BinaryTree.h"

@interface ViewController ()<UICollectionViewDataSource,UICollectionViewDelegate,UITextViewDelegate>
@property (strong, nonatomic) UITextView *textView;
@property (strong, nonatomic) UICollectionView *collectionView;
@property (copy,   nonatomic) NSArray *array;
@property (strong, nonatomic) UILabel *outPutLabel;
@property (strong, nonatomic) NSArray *SignsArray;
@property (strong, nonatomic) Stack *stack;
@property (strong, nonatomic) TreeNode *treeNode;
@property (strong, nonatomic) BinaryTree *binaryTree;
@property (strong, nonatomic) Stack *tempStack;
@property (strong, nonatomic) TreeNode *tempNode;

@end

@implementation ViewController

- (TreeNode *)tempNode {
    if (!_tempNode) {
        _tempNode = [[TreeNode alloc] init];
    }
    return _tempNode;
}
- (Stack *)tempStack {
    if (!_tempStack) {
        _tempStack = [[Stack alloc] init];
    }
    return _tempStack;
}
- (BinaryTree *)binaryTree {
    if (!_binaryTree) {
        _binaryTree = [[BinaryTree alloc] init];
    }
    return _binaryTree;
}
- (TreeNode *)treeNode {
    if (!_treeNode) {
        _treeNode = [[TreeNode alloc] init];
    }
    return _treeNode;
}
- (Stack *)stack {
    if (!_stack) {
        _stack = [[Stack alloc] init];
    }
    return _stack;
}
- (NSArray *)SignsArray {
    
    if (_SignsArray == NULL) {
        NSArray *SignsArray = @[@"+",@"-",@"×",@"÷",@"√",@"ln"];
        _SignsArray = SignsArray;
    }
    return _SignsArray;
}
- (UITextView *)textView {
    if (!_textView) {
        _textView = [[UITextView alloc] init];
    }
    return _textView;
}
- (NSArray *)array {
    if (!_array) {
        _array = [NSArray array];
        _array = @[@"C",@"(",@")",@"÷",@"7",@"8",@"9",@"×",@"4",@"5",@"6",@"-",@"1",@"2",@"3",@"+",@"0",@".",@"="];
        
    }
    return _array;
}
- (UILabel *)outPutLabel {
    if (!_outPutLabel) {
        _outPutLabel = [[UILabel alloc] init];
    }
    return _outPutLabel;
}
- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self setUpUI];
    
}
- (void)setUpUI {
    //背景view的颜色
    self.view.backgroundColor = [UIColor blackColor];
    
    //输入框
    self.textView.backgroundColor = [UIColor blackColor];
    [self.view addSubview:self.textView];
    self.textView.editable = NO;
    self.textView.textColor = [UIColor whiteColor];
    [self.textView setFont:[UIFont fontWithName:@"Helvetica-Bold" size:80]];
    // 添加监视
    [self.textView addObserver:self forKeyPath:@"text" options:NSKeyValueObservingOptionNew context:nil];
    self.textView.sd_layout.topSpaceToView(self.navigationController.navigationBar, 80).leftSpaceToView(self.view, 10).rightSpaceToView(self.view, 10).heightIs(SCREENHEIGHT/3-50);
    
    //结果输出框
    self.outPutLabel.backgroundColor = [UIColor blackColor];
    self.outPutLabel.textColor = [UIColor whiteColor];
    [self.outPutLabel setFont:[UIFont fontWithName:@"Helvetica-Bold" size:32]];
    self.outPutLabel.textAlignment = NSTextAlignmentRight;
    [self.view addSubview:self.outPutLabel];
    self.outPutLabel.sd_layout.leftEqualToView(self.textView).rightEqualToView(self.textView)
    .topSpaceToView(self.textView, 5).heightIs(50);
    
    //底部的按钮
    UICollectionViewFlowLayout *layout = [[UICollectionViewFlowLayout alloc] init];
    //设置内边距
    layout.sectionInset = UIEdgeInsetsMake(12, 3, 12, 3);
    //设置item大小
    layout.itemSize = CGSizeMake(SCREENWIDTH/5, SCREENWIDTH/5);
    self.collectionView = [[UICollectionView alloc] initWithFrame:CGRectZero collectionViewLayout:layout];
    self.collectionView.backgroundColor = [UIColor blackColor];
    self.collectionView.dataSource = self;
    self.collectionView.delegate = self;
    [self.collectionView registerClass:[CollectionViewCell class]
     forCellWithReuseIdentifier:@"cell"];
    [self.view addSubview:self.collectionView];
    self.collectionView.sd_layout.topSpaceToView(self.outPutLabel, 0).leftEqualToView(self.textView).rightEqualToView(self.textView).bottomEqualToView(self.view);
}
#pragma mark - uicollectionViewDelegate & DataSource
- (NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView{
    return 1;
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section {
    return 19;
}
- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath {
    CollectionViewCell *cell = [_collectionView dequeueReusableCellWithReuseIdentifier:@"cell" forIndexPath:indexPath];
    if (indexPath.row==0||indexPath.row==1||indexPath.row==2) {
        cell.backgroundColor = [UIColor colorWithRed:163/255.0 green:164/255.0 blue:165/255.0 alpha:1];
    }else if (indexPath.row==3||indexPath.row==7||indexPath.row==11||indexPath.row==15||indexPath.row==18){
        cell.backgroundColor = [UIColor colorWithRed:244/255.0 green:159/255.0 blue:63/255.0 alpha:1];
    }else {
        cell.backgroundColor = [UIColor colorWithRed:51/255.0 green:52/255.0 blue:53/255.0 alpha:1];
    }
    if (indexPath.row==16) {
        cell.layer.cornerRadius = SCREENWIDTH/10;
    }else {
        cell.layer.cornerRadius = SCREENWIDTH/10;
    }
    cell.titleLabel.text = self.array[indexPath.row];
    return cell;
    
}

-(CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath {
    if (indexPath.row == 16) {
        return CGSizeMake(SCREENWIDTH/2-10, SCREENWIDTH/5);
    }
    return CGSizeMake(SCREENWIDTH/5, SCREENWIDTH/5);
}

- (void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath {
    NSString *text = self.textView.text;
    if (!text.length) {
        //一开始不能输入操作符
        NSString *Chars = self.array[indexPath.row];
        BOOL flag = ([Chars isEqual:@"÷"] || [Chars isEqual:@"×"] ||[Chars isEqual:@"+"] || [Chars isEqual:@"-"]||[Chars isEqual:@"="] || [Chars isEqual:@"."] || [Chars isEqual:@"～"] || [Chars isEqual:@"C"] || [Chars isEqual:@"←"] || [Chars isEqual:@")"]);
        if (flag) {
            //第一个是操作符不做任何处理
        }else {
            //第一个不是操作符
            text = [text stringByAppendingString:self.array[indexPath.row]];
            self.textView.text = text;
        }
    }else {
        if ([self.array[indexPath.row] isEqual:@"～"] || [[self.textView.text substringFromIndex:self.textView.text.length - 1] isEqual:@"="]) {
            //输入的是～不做任何处理
            if ([self.array[indexPath.row]  isEqual: @"C"]) {
                self.textView.text = @"";
                [self.textView setFont:[UIFont fontWithName:@"Helvetica-Bold" size:80]];
                self.outPutLabel.text = @"";
            }else if ([self.array[indexPath.row]  isEqual: @"←"]){
                if (self.textView.hasText) {
                    NSString *text = self.textView.text;
                    text = [text substringToIndex:text.length-1];
                    self.textView.text = text;
                }
            }
        }else {
            //判断操作符是否重叠
            NSString *lastStr = [text substringFromIndex:text.length-1];
            if (([lastStr isEqual:@"÷"] || [lastStr isEqual:@"×"] ||[lastStr isEqual:@"+"] || [lastStr isEqual:@"-"] || [lastStr isEqual:@"."] || [lastStr isEqual:@"="]) && ([self.array[indexPath.row] isEqual:@"÷"] || [self.array[indexPath.row] isEqual:@"×"] ||[self.array[indexPath.row] isEqual:@"+"] || [self.array[indexPath.row] isEqual:@"-"] || [self.array[indexPath.row] isEqual:@"."] || [self.array[indexPath.row] isEqual:@"="])) {
                //重叠
            }else {
                text = [text stringByAppendingString:self.array[indexPath.row]];
                if ([self.array[indexPath.row]  isEqual: @"C"]) {
                    self.textView.text = @"";
                    [self.textView setFont:[UIFont fontWithName:@"Helvetica-Bold" size:80]];
                    self.outPutLabel.text = @"";
                }else if ([self.array[indexPath.row]  isEqual: @"←"]){
                    if (self.textView.hasText) {
                        NSString *text = self.textView.text;
                        text = [text substringToIndex:text.length-1];
                        self.textView.text = text;
                    }
                }else {
                    self.textView.text = text;
                }
                if ([self.array[indexPath.row] isEqual:@"="]) {
                    if (self.textView.hasText) {
                        //判断是否包含不合法字符串
                        if ([self.textView.text rangeOfString:@"÷0"].location != NSNotFound) {
                            self.outPutLabel.text = @"错误";
                        }else {
                            [self Calculate:self.textView.text];
                        }
                    }
                }
            }
        }
    }
    //textView自动跑到最后一行
    [_textView scrollRectToVisible:CGRectMake(0, _textView.contentSize.height-15, _textView.contentSize.width, 10) animated:YES];
}
// 接收textView输入框值的变化
- (void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary *)change context:(void *)context {
    NSString *text = [object text];
    NSUInteger count = text.length;
    if (count<=9) {
        switch (count) {
            case 9:{
                [self.textView setFont:[UIFont fontWithName:@"Helvetica-Bold" size:40]];
                break;
            }
            case 8:{
                [self.textView setFont:[UIFont fontWithName:@"Helvetica-Bold" size:50]];
                break;
            }
            case 7:{
                [self.textView setFont:[UIFont fontWithName:@"Helvetica-Bold" size:60]];
                break;
            }
            case 6:{
                [self.textView setFont:[UIFont fontWithName:@"Helvetica-Bold" size:70]];
                break;
            }
            default:
                break;
        }
    }else {
        [self.textView setFont:[UIFont fontWithName:@"Helvetica-Bold" size:38]];
    }
}

- (void)Calculate :(NSString *)text {
    [self dealWithText:text];
}

//处理字符串
- (void) dealWithText:(NSString *)Equation {
    Equation = [Equation stringByReplacingOccurrencesOfString:@"+" withString:@"|+|"];
    Equation = [Equation stringByReplacingOccurrencesOfString:@"-" withString:@"|-|"];
    Equation = [Equation stringByReplacingOccurrencesOfString:@"×" withString:@"|*|"];
    Equation = [Equation stringByReplacingOccurrencesOfString:@"÷" withString:@"|/|"];
    Equation = [Equation stringByReplacingOccurrencesOfString:@"(" withString:@"|(|"];
    Equation = [Equation stringByReplacingOccurrencesOfString:@")" withString:@"|)|"];
    Equation = [Equation stringByReplacingOccurrencesOfString:@"=" withString:@""];
    NSMutableArray *array = [Equation componentsSeparatedByString:@"|"].mutableCopy;
    [array removeObject:@""];
    if ([array[0] isEqualToString:@"-"]||[array[0] isEqualToString:@"+"]) {
        [array insertObject:@"0" atIndex:0];
    }
    //判断是否只有一个数字的运算
    if (array.count == 1) {
        self.outPutLabel.text = array[0];
    }else {
        //将中缀表达式转换为后缀表达式
        [self TranslateToInversePolandForClculate:array];
    }
}
- (void) TranslateToInversePolandForClculate:(NSMutableArray *)array {
    //NSMutableArray *Stack = [NSMutableArray array];
    NSMutableArray *outPutArray = [NSMutableArray array];
    for (int i=0; i<array.count; i++) {
        NSString *Chars = array[i];
        //操作符号判断
        BOOL flag = ([Chars isEqual:@"/"] || [Chars isEqual:@"*"] ||[Chars isEqual:@"+"] || [Chars isEqual:@"-"] || [Chars isEqual:@"("] || [Chars isEqual:@")"] );
        //如果只是数字那就压入栈
        if (!flag) {
            [outPutArray addObject:Chars];
        }
        //如果是操作符
        if (flag) {
            while ([Chars isEqual:@"/"] || [Chars isEqual:@"*"] ||[Chars isEqual:@"+"] || [Chars isEqual:@"-"] || [Chars isEqual:@"("] || [Chars isEqual:@")"]) {
                //先判断是否入栈成功
                if ([self.stack PushIntoStack:Chars]) {
                    //入栈成功
                    break;
                }else {
                    //入栈失败
                    //出栈
                    while ([self.stack StackIsEmpty] == NO) {
                        if ([self.stack isHigh:Chars] == NO || [Chars isEqual:@")"]) {
                            if ([Chars isEqual:@")"]) {
                                NSString *chars = [self.stack PopStack];
                                while (![chars isEqual:@"("] && chars.length) {
                                    [outPutArray addObject:chars];
                                    chars = [self.stack PopStack];
                                }
                            }else {
                                if ([self.stack.array.lastObject isEqual:@"("]) {
                                    
                                }else {
                                    [outPutArray addObject:[self.stack PopStack]];
                                }
                            }
                        }else {
                            break;
                        }
                    }
                    
                    //入栈
                    //如果操作符是右括号），则不需要入栈
                    if ([Chars isEqual:@")"]) {
                        
                    }else {
                        [self.stack PushIntoStack:Chars];
                    }
                    break;
                }
            }
        }
    }
    //栈中还残留其他操作符
    while ([self.stack StackIsEmpty] == NO) {
        [outPutArray addObject:[self.stack PopStack]];
    }
    //销毁栈
    [self.stack DestroyStack];
    [self BuildTree:outPutArray];
    
}
- (void)BuildTree:(NSMutableArray *)array {
    [self.stack DestroyStack];
    [self.tempStack DestroyStack];
    NSString *Chars,*chars1,*chars2;
    for (int i=0; i<array.count; i++) {
        Chars = array[i];
        //操作符号判断
        BOOL flag = ([Chars isEqual:@"/"] || [Chars isEqual:@"*"] ||[Chars isEqual:@"+"] || [Chars isEqual:@"-"]);
        //如果只是数字那就压入栈
        if (!flag) {
            //[outPutArray addObject:Chars];
            [self.tempStack PushmIntoStack:Chars];
        }else {
            //操作符
            //重新构建二叉树的根结点
            //先判断是否是第一次构建根结点
            if (!self.binaryTree.rootNode.data.length) {
                //第一次构建根结点
                self.treeNode = [self.binaryTree buildTree:Chars];
                //栈元素出栈
                chars2 = [self.tempStack PopTempStack];
                chars1 = [self.tempStack PopTempStack];
                //插入右结点
                [self.binaryTree insertToRightTree:chars2 :self.treeNode];
                //插入左结点
                [self.binaryTree insertToLeftTree:chars1 :self.treeNode];
                //操作入栈
                [self.tempStack PushmIntoStack:self.binaryTree.rootNode];
            }else {
                TreeNode *treeNode = [[TreeNode alloc] init];
                treeNode.data = Chars;
                //栈顶元素出栈
                //右子树
                if ([[self.tempStack getStackHead] isKindOfClass:[NSString class]]) {
                    chars1 = [self.tempStack PopTempStack];
                    //更新右结点
                    //插入右结点
                    [self.binaryTree insertToRightTree:chars1 :treeNode];
                }else {
                    TreeNode *tempNode = [[TreeNode alloc] init];
                    tempNode = [self.tempStack PopTempStack];
                    //更新右结点
                    treeNode.rightTreeNode = tempNode;
                }
                //栈顶元素再次出栈
                if ([[self.tempStack getStackHead] isKindOfClass:[NSString class]]) {
                    chars1 = [self.tempStack PopTempStack];
                    //更新左结点
                    [self.binaryTree insertToLeftTree:chars1 :treeNode];
                }else {
                    TreeNode *tempNode = [[TreeNode alloc] init];
                    tempNode = [self.tempStack PopTempStack];
                    treeNode.leftTreeNode = tempNode;
                }
                //判断是否是最后一个操作符
                if (i == array.count - 1) {
                    //更新根结点
                    [self.binaryTree changeRootNode:treeNode];
                }else {
                    //将操作添加到栈中
                    [self.tempStack PushmIntoStack:treeNode];
                }
                
            }
            
        }
    }
    float value = [self.binaryTree inOrderTraverse:self.binaryTree.rootNode];
    NSString *outPut = [NSString stringWithFormat:@"%f",value];
    NSDecimalNumber *nsd = [NSDecimalNumber decimalNumberWithString:outPut];
    self.outPutLabel.text = [nsd stringValue];
    //销毁树
    [self.binaryTree DestoryTree];
}
@end
