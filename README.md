# practice-private

非项目，日常练手，主要包括Java的基础学习。<br>
## sunny/homework/caculator`四则运算器`
* 表达式计算
四则运算实现方式：
  * 非递归，判断操作符入栈的情况(不处理负数)
  * 递归，将表达式抽象为<br>`expression : term +/- expression`<br>`term : factor * term`<br>`facter：Integer || expression`<br>
  * 二叉树表示法
将表达式用二叉树表示，使用二叉查找树的后序遍历对表达式求值
  * 后缀表达式
## sunny/homework/dataStructure`数据结构`
* 链表<br>双向链表的插入删除操作
* 栈<br>进栈、出栈操作
* 树
   * 二叉查找树
     1. 插入删除
     2. 迭代器实现
     3. 遍历：递归、非递归；层次遍历
   * 平衡二叉树
     1. 插入删除
     2. 旋转平衡
   * 红黑树
     1. 插入删除
     2. 旋转、颜色变换<br>
红黑树删除的具体过程描述见<http://www.jianshu.com/p/29c68fd8bf8a>
## sunny/homework/decorator`适配器模式`
* 适配器的实现<br>
TokenStream可见表达式`(1+2)-3`处理为`{LPAR, (} {INT, 1} {PLUS, +} {INT, 2} {RPAR, )} {MINUS, -} {INT, 3} `这种形式
## sunny/homework/sort`排序算法`
* 冒泡、选择、插入、堆、归并、快速排序
* 实际应用：求中位数、求最值条件、立方根等，在`sunny.homework.sort.SorterExercise`中
## sunny/homework/proxy`代理`
* 动态代理InvocationHandler.invoke()方法的理解
## sunny/homework/rpc`远程调用`
* 待续
## sunny/homework/keyWords`常见关键字`
* transient

以上学习资料主要来源于<https://zhuanlan.zhihu.com/hinus>，表示感谢！<br>
学习进度更新中...<br>
2017/04/07
