package me.nettee.simpleparser;

import java.util.Scanner;
import java.util.function.Predicate;

import static com.google.common.base.Preconditions.checkState;

public class SimpleParser {

    private char[] input;
    private int pos;

    public SimpleParser(String source) {
        this.input = source.toCharArray();
        this.pos = 0;
    }

    public Expression parse() {
        return parseExpression();
    }

    private Expression parseExpression() {
        for (CompoundExpression.Type type : CompoundExpression.Type.values()) {
            if (startsWith(type.getName())) {
                return parseCompoundExpression(type);
            }
        }
        return parseVariableExpression();
    }

    private CompoundExpression parseCompoundExpression(CompoundExpression.Type type) {
        String name = type.getName();
        for (char c : name.toCharArray()) {
            checkState(c == consumeChar());
        }
        checkState('(' == consumeChar());
        Expression left = parseExpression();
        checkState(',' == consumeChar());
        consumeWhitespace();
        Expression right = parseExpression();
        checkState(')' == consumeChar());
        return new CompoundExpression(type, left, right);
    }

    private VariableExpression parseVariableExpression() {
        String name = parseName();
        return new VariableExpression(name);
    }

    private String parseName() {
        return consumeWhile(Character::isAlphabetic);
    }

    private void consumeWhitespace() {
        consumeWhile(Character::isWhitespace);
    }

    private String consumeWhile(Predicate<Character> test) {
        StringBuilder sb = new StringBuilder();
        while (!eof() && test.test(nextChar())) {
            sb.append(consumeChar());
        }
        return sb.toString();
    }

    private char consumeChar() {
        return input[pos++];
    }

    private boolean startsWith(String s) {
        return new String(input, pos, input.length - pos).startsWith(s);
    }

    private char nextChar() {
        return input[pos];
    }

    private boolean eof() {
        return pos >= input.length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String expr = scanner.nextLine();
            SimpleParser simpleParser = new SimpleParser(expr);
            Expression expression = simpleParser.parse();
            System.out.println(expression.toSymbolString());
        }
    }
}


