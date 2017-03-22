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
		//输入1-2+3-4
		BinaryTreeExpression bte = new BinaryTreeExpression();
		BinarySearchTree<Token> bst = new BinarySearchTree<Token>();
		bst.root = bte.expression();
		System.out.println("后序遍历结果：");
		bst.postOrder(bst.root);//{INT, 1} {INT, 2} {MINUS, -} {INT, 3} {PLUS, +} {INT, 4} {MINUS, -} 
		System.out.println();
		System.out.println("中序遍历结果：");
		bst.midOrder(bst.root);//{INT, 1} {MINUS, -} {INT, 2} {PLUS, +} {INT, 3} {MINUS, -} {INT, 4} 
		System.out.println();
		System.out.println("后序遍历并求值的结果：");
		System.out.println(bst.postOrderAndCalculate(bst.root));
	}
	
	public BinaryTreeExpression() throws IOException {
		ts = new MyTokenStream(new BufferedInputStream(System.in));
	}

	/**
	 * 构造表达式的二叉树，并返回二叉树的根节点
	 * @return 根节点
	 */
	public Node<Token> expression() {
        Node<Token> left = term();
        Token op = null;
        try {
        	op = ts.getToken();
        	Node<Token> root = null;
        	while(op.tokenType == TokenType.PLUS || op.tokenType == TokenType.MINUS) {
        		ts.consumeToken();
        		//Node<Token> right = term();
	            if (op.tokenType == TokenType.PLUS) {
	            	root = new Node<>(new Token(Token.TokenType.PLUS, "+"));
	                root.left = left;
	                root.right = term();
	            }
	            else  {
	            	root = new Node<>(new Token(Token.TokenType.MINUS, "-"));
	                root.left = left;
	                root.right = term();
	            }
	            left = root;
	            op = ts.getToken();
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
        return left;
    }
	
	private Node<Token> term() {
		Node<Token> left = factor();
		Token op = null;
        try {
        	op = ts.getToken();
        	Node<Token> root = null;
        	while(op.tokenType == TokenType.MULT || op.tokenType == TokenType.DIV) {
        		ts.consumeToken();
        		//Node<Token> right = term();
	            if (op.tokenType == TokenType.MULT) {
	            	root = new Node<>(new Token(Token.TokenType.MULT, "*"));
	                root.left = left;
	                root.right = term();
	            }
	            else  {
	            	root = new Node<>(new Token(Token.TokenType.DIV, "/"));
	                root.left = left;
	                root.right = term();
	            }
	            left = root;
	            op = ts.getToken();
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
			//对负数的处理，将负号与数字作为整体存储在token.value中
			else if(match(TokenType.MINUS)){
				Node<Token> temp = factor();
				temp.data.value = 0 - (int)temp.data.value;
				return temp;
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
