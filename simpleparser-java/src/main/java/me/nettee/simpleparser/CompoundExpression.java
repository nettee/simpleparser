package me.nettee.simpleparser;

import com.google.common.base.MoreObjects;

public class CompoundExpression extends Expression {

    public enum Type {
        ADD("Add", "+"),
        SUB("Sub", "-"),
        MUL("Multi", "*"),
        DIV("Divide", "/"),
        ;

        private final String name;
        private final String symbol;

        Type(String name, String symbol) {
            this.name = name;
            this.symbol = symbol;
        }

        public String getName() {
            return name;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    private final Type type;
    private final Expression left;
    private final Expression right;

    public CompoundExpression(Type type, Expression left, Expression right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toNameString() {
        return String.format("%s(%s, %s)", type.getName(), left.toNameString(), right.toNameString());
    }

    @Override
    public String toSymbolString() {
        String template = right instanceof VariableExpression ? "%s %s %s" : "%s %s (%s)";
        return String.format(template, left.toSymbolString(), type.getSymbol(), right.toSymbolString());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", type)
                .add("left", left)
                .add("right", right)
                .toString();
    }
}
