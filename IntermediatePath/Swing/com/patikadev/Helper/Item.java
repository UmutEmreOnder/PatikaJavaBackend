package com.patikadev.Helper;

import java.util.ArrayList;

public class Item {
    private int key;
    private String value;

    public Item(int key, String value) {
        this.key = key;
        this.value = value.trim();
    }

    @Override
    public String toString() {
        return this.value;
    }
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
