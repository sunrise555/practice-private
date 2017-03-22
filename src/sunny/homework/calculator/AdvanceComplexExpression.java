package sunny.homework.calculator;

import java.io.BufferedInputStream;
import java.io.IOException;

import sunny.homework.decorator.MyTokenStream;
import sunny.homework.decorator.Token;
import sunny.homework.decorator.Token.TokenType;
import sunny.homework.decorator.TokenStream;


/**
 * expr := term (+|-) term (+|-) ... (+|-) term
 *term := factor (*|/) factor (* | /) ... (*|/) factor
 *factor := INT | "(" expr ")"
 *递归实现四则运算，支持负数处理
 * Created by Sunny on 2017年3月16日
 */
public class AdvanceComplexExpression {
	public TokenStream ts;

    public static void main(String args[]) throws IOException {
    	AdvanceComplexExpression e = new AdvanceComplexExpression();
        System.out.println(e.expression());
    }

    public AdvanceComplexExpression() throws IOException {
        ts = new MyTokenStream(new BufferedInputStream(System.in));
    }

    public int expression() {
        int t = term();
        Token op = null;
        try {
        	op = ts.getToken();
        	while(op.tokenType == TokenType.PLUS || op.tokenType == TokenType.MINUS) {
        		ts.consumeToken();
        		int temp = term();
	            if (op.tokenType == TokenType.PLUS) {
	                t += temp;
	            }
	            else  {
	                t -= temp;
	            }
	            op = ts.getToken();
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    private int term() {
        int t = factor();
        Token op = null;
        try {
            op = ts.getToken();

            while (op.tokenType == TokenType.MULT || op.tokenType == TokenType.DIV) {
                ts.consumeToken();
                int t2 = factor();
                if (op.tokenType == TokenType.MULT) {
                    t *= t2;
                } else {
                    t /= t2;
                }
                op = ts.getToken();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    private int factor() {
        Token t = null;
        try {
            t = ts.getToken();

            if (t.tokenType == TokenType.INT) {
                ts.consumeToken();
                return (((Integer) t.value).intValue());
            }
            else if (match(TokenType.LPAR)) {
                int v = expression();
                if (!match(TokenType.RPAR))
                    assert false;
                return v;
            }
            //处理--3负数形式
            else if (t.tokenType == TokenType.MINUS) {
                ts.consumeToken();
                return 0 - factor();
            }
            else {
                throw new IOException("Illegal Expression!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private boolean match(Token.TokenType tt) throws IOException {
        if (ts.getToken().tokenType == tt) {
            ts.consumeToken();
            return true;
        }
        return false;
    }
}
