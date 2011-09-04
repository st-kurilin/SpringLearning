package com.domain.customer;

/**
 * User: andrii.loboda
 */
//TODO: [stas] Let's neutralize it. Use gender instead.
public enum Sex {
    MALE("Male"), FEMALE("Female");     //should think: Male/female vs masculine/feminine
    //TODO: I don't believe we need it
    private final String value;

    Sex(String value) {
        this.value = value;
    }

    @Override
    //TODO: [stas] I believe overriding to string isn't worth thing. Please consult doc.
    public String toString() {
        return value;
    }

    //TODO: I don't believe we need it
    public String getValue() {
        return value;
    }
}
