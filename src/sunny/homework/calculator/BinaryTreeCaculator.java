package sunny.homework.calculator;

import sunny.homework.dataStructors.tree.BinarySearchTree;
import sunny.homework.decorator.Token;
/**
 * ���ڶ�����������������
 * @Created by Sunny on 2017��4��7��
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
