package org.timor.domain;

public class Variable {

    private final int identifier;
    private final int type;
    private final String value;

    public Variable(int identifier, int type, String value) {
        this.identifier = identifier;
        this.type = type;
        this.value = value;
    }

    public int getIdentifier() {
        return identifier;
    }

    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
