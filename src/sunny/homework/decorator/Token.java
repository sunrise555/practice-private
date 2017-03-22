package sunny.homework.decorator;

public class Token implements Comparable<Token>{
	public  enum TokenType {
        LPAR, RPAR,
        PLUS,
        MINUS,
        MULT,
        DIV,
        INT,
        NONE,
        SPACE
    }
    public TokenType tokenType;
    public Object value;

    public Token(TokenType tt, Object v) {
        this.tokenType = tt;
        this.value = v;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{" + tokenType + ", " + value + "}";
	}

	@Override
	public int compareTo(Token o) {
		if (this.tokenType == o.tokenType) {
			if(this.tokenType == TokenType.INT) {
				if(Integer.valueOf(this.value.toString())==Integer.valueOf(o.value.toString()))
					return 0;
				else if(Integer.valueOf(this.value.toString())>Integer.valueOf(o.value.toString()))
					return 1;
				else
					return -1;
			}else {
				if(this.value.toString().equals(o.value.toString()))
					return 0;
			}
		}else 
			throw new ClassCastException();
		return -1;
	}
}
