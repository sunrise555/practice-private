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
 * ������������ʽת��Ϊ��������ʾ
 * Created by Sunny on 2017��3��21��
 */
public class BinaryTreeExpression {
	//��ʽ��
	private TokenStream ts;
	
	//����ʹ��
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
	 * ������ʽ�Ķ������������ض������ĸ��ڵ�
	 * @return ���ڵ�
	 */
	//�������⣺�޷�����1-2+3-4�����
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
				//�����ǰ�������ţ��������ʽ
				Node<Token> left = expression();
				//�жϱ��ʽ�Ƿ�պ�
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
	
	//�ж�token�����Ƿ�ƥ��
	private boolean match(TokenType tt) throws IOException {
		if (ts.getToken().tokenType == tt) {
			ts.consumeToken();
			return true;
		}
		return false;
	}
}
