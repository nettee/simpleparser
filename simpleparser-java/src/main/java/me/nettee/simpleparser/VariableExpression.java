package me.nettee.simpleparser;

import com.google.common.base.MoreObjects;

public class VariableExpression extends Expression {

    private final String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toNameString() {
        return name;
    }

    @Override
    public String toSymbolString() {
        return name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .toString();
    }
}
