package com.ddm;

import org.junit.Assert;
import org.junit.Test;


import  com.ddm.Arrays.ArrayMassive;

import java.util.Iterator;

public class ArraysTest {
    @Test
    public void addTest() {
        ArrayMassive<Integer> m = new ArrayMassive<>(1, 1,1);
        Assert.assertEquals(0, m.size());
        m.add(4);
        Assert.assertEquals(1, m.size());
        Assert.assertEquals(4,m.get(0));
        m.add(4);
        Assert.assertEquals(2, m.size());
        m.add(3);
        Assert.assertEquals(4, m.get(1));
    }
    @Test
    public void addTest2(){
        ArrayMassive<Integer> m = new ArrayMassive<>(1, 1,1);
        Assert.assertEquals(0, m.size());
        m.add(4);
        Assert.assertEquals(1, m.size());
    }
    @Test(expected = ElementNotFoundException.class)
    public void removeTest1() throws ElementNotFoundException {
        ArrayMassive<Integer> m = new ArrayMassive<>(1, 1,1);
        m.remove(5);
    }
    @Test(expected = ElementNotFoundException.class)
    public void removeTest2() throws ElementNotFoundException {
        ArrayMassive<Integer> n = new ArrayMassive<>(1, 1,1);
        n.add(4);
        n.add(6);
        n.remove(6);
        Assert.assertEquals(1, n.size());
        n.remove(6);
        Assert.assertEquals(1, n.size());
    }
    @Test
    public void equalsTest() {
        ArrayMassive<Integer> m = new ArrayMassive<>(1, 1,1);
        ArrayMassive<Integer> n = new ArrayMassive<>(1, 1,1);
        m.add(4);
        n.add(4);
        Assert.assertTrue(m.equals(n));
        m.add(5);
        Assert.assertFalse(m.equals(n));
    }
    @Test
    public void hashCodeTest() {
        ArrayMassive<String> m = new ArrayMassive<>(1, 1,1);
        ArrayMassive<String> n = new ArrayMassive<>(1, 1,1);
        m.add("4");
        n.add("4");
        Assert.assertTrue(m.hashCode() == n.hashCode());
//        m.add(5);
//        n.add(5);
//        m.add(11);
//        n.add(11);
//        Assert.assertTrue(m.hashCode() == n.hashCode());
//        n.remove(4);
//        Assert.assertFalse(m.hashCode() == n.hashCode());
    }
    @Test
    public void iteratorTest1() {
        ArrayMassive<Integer> m = new ArrayMassive<>(1, 1,1);
        Iterator<Integer> i = m.iterator();
        m.add(4);
        m.add(6);
        m.add(9);
        Assert.assertTrue(i.hasNext());
        Assert.assertEquals((Integer) 4, i.next());
        Assert.assertEquals((Integer) 6, i.next());
        Assert.assertEquals((Integer) 9, i.next());
    }
    @Test
    public void iteratorTest2() {
        ArrayMassive<Integer> m = new ArrayMassive<>(2, 2,2);
        Iterator<Integer> i = m.iterator();
        m.add(4);
        Assert.assertEquals((Integer) 4, i.next());
        Assert.assertEquals(null, i.next());
        m.add(5);
        m.add(7);
        Assert.assertEquals((Integer) 7, i.next());
    }
    @Test
    public void fatherTest() {
        ArrayMassive<String> ls = new ArrayMassive<>(1,1,1);
        ls.add("1");
        ls.add("2");

        Assert.assertEquals(2, ls.size());
        Assert.assertEquals("1", ls.get(0));
        Assert.assertEquals("2", ls.get(1));

        ls.remove("1");
        Assert.assertEquals(1, ls.size());

        ls.remove("2");
        Assert.assertEquals(0, ls.size());

        try {
            ls.remove("0");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ElementNotFoundException);
        }
    }
}

