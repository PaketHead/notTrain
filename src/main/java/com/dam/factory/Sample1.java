package com.dam.factory;

public class Sample1 {
    @WithDefault(value = "12345")
    private String value;
    @Override
    public String toString() {
        return "Sample1{" +
                "value='" + value + '\'' +
                '}';
    }
}
