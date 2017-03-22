package sunny.haina;

import java.io.BufferedInputStream;
import java.io.IOException;

import sunny.homework.decorator.MyTokenStream;
import sunny.homework.decorator.Token;
import sunny.homework.decorator.Token.TokenType;
import sunny.homework.decorator.TokenStream;



/**
 * 四则运算计算器
 * Created by Haina
 */
public class ComplexExpression {
	public TokenStream ts;

    public static void main(String args[]) {
    	ComplexExpression e = new ComplexExpression();
        System.out.println(e.evalue());
    }

    public ComplexExpression() {
        try {
			ts = new MyTokenStream(new BufferedInputStream(System.in));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public int evalue() {
        int t = term();
        Token op = null;

        try {
            op = ts.getToken();
            while (op.tokenType == TokenType.PLUS || op.tokenType == TokenType.MINUS) {
                ts.consumeToken();
                int t2 = term();
                if (op.tokenType == TokenType.PLUS) {
                    t += t2;
                } else {
                    t -= t2;
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
            } else if (t.tokenType == TokenType.LPAR) {
                ts.consumeToken();
                int v = evalue();
                match(ts.getToken(), TokenType.RPAR);
                return v;
            }
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
        // should not reach here
        return 0;
    }

    private void match(Token t, Token.TokenType tt) {
        assert t.tokenType == tt;
        ts.consumeToken();
    }
}