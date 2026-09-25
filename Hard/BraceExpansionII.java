import java.util.*;

// 1096. Brace Expansion II

public class BraceExpansionII {
    private int ptr;

    public List<String> braceExpansionII(String expression) {
        this.ptr = 0;
        char[] expr = expression.toCharArray();
        Set<String> resultSet = parseExpression(expr);

        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }

    // Grammar Parsing: Expression -> Term (',' Term)*
    private Set<String> parseExpression(char[] expr) {
        Set<String> currentUnion = new HashSet<>();
        Set<String> currentTerm = parseTerm(expr);

        while (ptr < expr.length && expr[ptr] == ',') {
            ptr++; // consume ','
            currentUnion.addAll(currentTerm);
            currentTerm = parseTerm(expr);
        }
        currentUnion.addAll(currentTerm);
        return currentUnion;
    }

    // Grammar Parsing: Term -> Factor (Factor)* [Implicit Concatenation]
    private Set<String> parseTerm(char[] expr) {
        Set<String> result = new HashSet<>();
        result.add(""); // Identity for string concatenation

        while (ptr < expr.length && expr[ptr] != ',' && expr[ptr] != '}') {
            Set<String> nextFactor = parseFactor(expr);
            result = concatenate(result, nextFactor);
        }
        return result;
    }

    // Grammar Parsing: Factor -> single letter OR '{' Expression '}'
    private Set<String> parseFactor(char[] expr) {
        Set<String> factor = new HashSet<>();

        if (expr[ptr] == '{') {
            ptr++; // consume '{'
            factor = parseExpression(expr);
            ptr++; // consume '}'
        } else {
            // Primitive single character atom
            factor.add(String.valueOf(expr[ptr]));
            ptr++;
        }
        return factor;
    }

    // Fast Cross-Product Concatenation
    private Set<String> concatenate(Set<String> setA, Set<String> setB) {
        Set<String> product = new HashSet<>();
        for (String a : setA) {
            for (String b : setB) {
                product.add(a + b);
            }
        }
        return product;
    }
}
