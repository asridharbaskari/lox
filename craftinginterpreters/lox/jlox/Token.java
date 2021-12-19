package lox.jlox;
class Token {
    final TokenType type; // Type of token
    final String lexeme; // String encapsulation of token
    final Object literal; // Value of token
    final int line; // Line number of token

    Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    // Converts token to string representation of token for utility
    public String toString() {
        return type + " " + lexeme + " " + literal; 
    }
}
