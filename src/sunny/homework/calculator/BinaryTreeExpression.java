package sunny.homework.calculator;

import java.io.BufferedInputStream;
import java.io.IOException;

import sunny.homework.dataStructors.tree.BinarySearchTree;
import sunny.homework.dataStructors.tree.Node;
import sunny.homework.decorator.MyTokenStream;
import sunny.homework.decorator.Token;
import sunny.homework.decorator.Token.TokenType;
import sunny.homework.decorator.TokenStream;

/**
 * 将四则运算表达式转换为二叉树表示
 * Created by Sunny on 2017年3月21日
 */
public class BinaryTreeExpression {
	//格式流
	private TokenStream ts;
	
	//测试使用
	public static void main(String[] args) throws IOException {
		BinaryTreeExpression bte = new BinaryTreeExpression();
		BinarySearchTree<Token> bst = new BinarySearchTree<Token>();
		bst.root = bte.expression();
		bst.postOrder(bst.root);
	}
	
	public BinaryTreeExpression() throws IOException {
		ts = new MyTokenStream(new BufferedInputStream(System.in));
	}

	/**
	 * 构造表达式的二叉树，并返回二叉树的根节点
	 * @return 根节点
	 */
	//存在问题：无法处理1-2+3-4的情况
	public Node<Token> expression() {
		Node<Token> left = term();
		
		try {
			if(match(TokenType.PLUS)) {
				Node<Token> root = new Node<Token>(new Token(TokenType.PLUS, "+"));
				root.left = left;
				root.right = term();
				return root;
			}else if(match(TokenType.MINUS)) {
				Node<Token> root = new Node<Token>(new Token(TokenType.MINUS, "-"));
				root.left = left;
				root.right = term();
				return root;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return left;
	}
	
	private Node<Token> term() {
		Node<Token> left = factor();
		
		try {
			if(match(TokenType.MULT)) {
				Node<Token> root = new Node<Token>(new Token(TokenType.MULT, "*"));
				root.left = left;
				root.right = factor();
				return root;
			}
			else if(match(TokenType.DIV)) {
				Node<Token> root = new Node<Token>(new Token(TokenType.DIV, "/"));
				root.left = left;
				root.right = factor();
				return root;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return left;
	}
	
	private Node<Token> factor() {
		Token t = null;
		//Node<Token> left;
		try {
			t = ts.getToken();
			
			if (t.tokenType == TokenType.INT) {
				ts.consumeToken();
				return new Node<Token>(t);
			}
			else if(match(TokenType.LPAR)) {
				//如果当前是左括号，则进入表达式
				Node<Token> left = expression();
				//判断表达式是否闭合
				if (!match(TokenType.RPAR))
					assert false;
				return left;
			}
			else {
				throw new IOException("Illegal Expression!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//判断token类型是否匹配
	private boolean match(TokenType tt) throws IOException {
		if (ts.getToken().tokenType == tt) {
			ts.consumeToken();
			return true;
		}
		return false;
	}
}
