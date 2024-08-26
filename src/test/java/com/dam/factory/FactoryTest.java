package com.dam.factory;

import org.junit.Test;

public class FactoryTest {

    @Test
    public void simpleTest1() {
        Sample1 sample1 = SimpleFactory.newInstance(Sample1.class);
        System.out.println(sample1);
        Sample2 sample2 = SimpleFactory.newInstance(Sample2.class);
        System.out.println(sample2);
    }

    @Test
    public void simpleTest2() {
        Sample1 sample1 = new Sample1();
        System.out.println(sample1);
        Sample2 sample2 = new Sample2();
        System.out.println(sample2);
    }
}
