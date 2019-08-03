package me.nettee.simpleparser;

public abstract class Expression {

    public static CompoundExpression compound(CompoundExpression.Type type, Expression left, Expression right) {
        return new CompoundExpression(type, left, right);
    }

    public static VariableExpression variable(String name) {
        return new VariableExpression(name);
    }

    public abstract String toSymbolString();
    public abstract String toNameString();
}
