package lox.jlox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jlox.TokenType.*;

class Scanner {
    private final String source; // Source code
    private final List<Token> tokens = new ArrayList<>();
    
    // Constructor
    Scanner(String source) {
        this.source = source;
    }
}
