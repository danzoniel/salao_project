package com.mycompany.salaoproject.models;

public class ItemModel {
    private String value;

    public ItemModel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
