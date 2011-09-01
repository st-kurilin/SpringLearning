package com.domain.customer;

/**
 * Created by IntelliJ IDEA.
 * User: andrii.loboda
 * Date: 01.09.11
 * Time: 18:19
 * To change this template use File | Settings | File Templates.
 */
public enum Sex{
    MALE("Male"),FEMALE("Female");

    private final String value;

    Sex(String value){
        this.value=value;
    }
    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
