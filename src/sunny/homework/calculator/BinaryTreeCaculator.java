package sunny.homework.calculator;

import sunny.homework.dataStructors.tree.BinarySearchTree;
import sunny.homework.decorator.Token;
/**
 * 基于二叉树的四则运算器
 * @Created by Sunny on 2017年4月7日
 */
public class BinaryTreeCaculator implements Calculator {

	@Override
	public String calculate(String expression) {
		BinaryTreeExpression bte = new BinaryTreeExpression(expression);
		BinarySearchTree<Token> bst = new BinarySearchTree<Token>();
		bst.root = bte.expression();
		String result =Integer.toString(bst.postOrderAndCalculate(bst.root));
		return result;
	}

}
