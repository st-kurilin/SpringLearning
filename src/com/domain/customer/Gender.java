package com.domain.customer;

/**
 * User: andrii.loboda
 */
public enum Gender {
    MALE("Male"), FEMALE("Female");     //should think: Male/female vs masculine/feminine

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}