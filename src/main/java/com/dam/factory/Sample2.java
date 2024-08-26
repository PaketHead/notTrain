package com.dam.factory;

public class Sample2 {
    @WithDefault(value = "123")
    private String value1;
    @WithDefault(value = "321")
    private String value2;
    @Override
    public String toString() {
        return "Sample2{" +
                "value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                '}';
    }
}
