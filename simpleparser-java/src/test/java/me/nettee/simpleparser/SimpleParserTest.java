package me.nettee.simpleparser;

import org.junit.Test;

public class SimpleParserTest {

    private String[] exprs = new String[] {
            "Add(a, Multi(b, c))",
            "Multi(a, Sub(b, c))",
            "Divide(a, Sub(b, c))",
    };

    @Test
    public void testSimpleParser() {
        for (String expr : exprs) {
            SimpleParser simpleParser = new SimpleParser(expr);
            Expression expression = simpleParser.parse();
            System.out.print(expr);
            System.out.print("  -->  ");
            System.out.println(expression.toSymbolString());
        }
    }

}
