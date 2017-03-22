package sunny.haina;

import java.io.IOException;

import sunny.homework.dataStructors.tree.BinarySearchTree;
import sunny.homework.dataStructors.tree.Node;
import sunny.homework.decorator.MyTokenStream;
import sunny.homework.decorator.Token;
import sunny.homework.decorator.TokenStream;
import sunny.homework.decorator.Token.TokenType;

public class BinaryTreeExpression2 {
	public TokenStream ts;

    public static void main(String args[]) throws IOException {
    	BinaryTreeExpression2 e = new BinaryTreeExpression2();
        BinarySearchTree<Token> bst = new BinarySearchTree<Token>();
        bst.root = e.expression();
        bst.postOrder(bst.root);
    }

    public BinaryTreeExpression2() throws IOException {
        ts = new MyTokenStream(System.in);
    }

    //存在问题，无法处理1-2+3-4的情况
    public Node<Token> expression() {
        Node<Token> left = term();
        Token op = null;
        try {
        	op = ts.getToken();
        	Node<Token> root = null;
        	while(op.tokenType == TokenType.PLUS || op.tokenType == TokenType.MINUS) {
        		ts.consumeToken();
        		Node<Token> right = term();
	            if (op.tokenType == TokenType.PLUS) {
	            	root = new Node<>(new Token(Token.TokenType.PLUS, "+"));
	                root.left = left;
	                root.right = right;
	            }
	            else  {
	            	root = new Node<>(new Token(Token.TokenType.MINUS, "-"));
	                root.left = left;
	                root.right = right;
	            }
	            op = ts.getToken();
        	}
        	return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Node<Token> term() {
        Node<Token> left = factor();

        try {
            if (match(TokenType.MULT)) {
                Node<Token> root = new Node<>(new Token(Token.TokenType.MULT, "*"));
                root.left = left;
                root.right = factor();
                return root;
            }
            else if(match(TokenType.DIV)){
                Node<Token> root = new Node<>(new Token(Token.TokenType.DIV, "/"));
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
            else if (match(TokenType.LPAR)) {
                Node<Token> v = expression();
                if (!match(TokenType.RPAR))
                    assert false;
                return v;
            }
            else {
                throw new IOException("Illegal Expression!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // should not reach here
        return null;
    }

    private boolean match(Token.TokenType tt) throws IOException {
        if (ts.getToken().tokenType == tt) {
            ts.consumeToken();
            return true;
        }
        return false;
    }
}
