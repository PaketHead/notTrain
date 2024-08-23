package com.ddm;

import org.junit.Assert;
import org.junit.Test;

import  com.ddm.Arrays.LinkedMassive;

import java.util.Iterator;

public class LinkedTest {
    @Test
    public void addTest() {
        LinkedMassive<Integer> l = new LinkedMassive<>();
        l.add(4);
        Assert.assertEquals(1, l.size());
        Assert.assertEquals(4, l.get(0));
    }
    @Test
    public void addTest1() {
        LinkedMassive<String> l = new LinkedMassive<>();
        l.add("4");
        l.add("5");
        l.add("SA");
        Assert.assertEquals("4", l.get(0));
        Assert.assertEquals("5", l.get(1));
        Assert.assertEquals("SA", l.get(2));

    }

    @Test
    public void removeTest1() throws ElementNotFoundException{
        LinkedMassive<Integer> l = new LinkedMassive<>();
        l.add(4);
        l.add(5);
        l.add(6);
        l.remove(5);
        l.remove(4);
        Assert.assertEquals(1, l.size());
        Assert.assertEquals(6, l.get(0));
    }
    @Test(expected = ListIsEmptyException.class)
    public void removeTest2() throws ListIsEmptyException{
        LinkedMassive<Integer> l = new LinkedMassive<>();
        l.remove(4);
    }
    @Test(expected = ElementNotFoundException.class)
    public void removeTest3() throws ElementNotFoundException{
        LinkedMassive<Integer> l = new LinkedMassive<>();
        l.add(4);
        l.add(5);
        l.remove(2);
        l.remove(4);
        l.remove(5);
    }
    @Test(expected = ElementNotFoundException.class)
    public void removeTest4() throws ElementNotFoundException{
        LinkedMassive<Integer> l = new LinkedMassive<>();
        l.add(4);
        l.add(5);
        l.remove(4);
        l.remove(2);
        l.remove(5);
    }
    @Test
    public void removeTest5(){
        LinkedMassive<Integer> l = new LinkedMassive<>();
        l.add(4);
        l.add(5);
        l.remove(4);
        //l.remove(2);
        l.remove(5);
        l.remove(5);
    }
    @Test
    public void updateTest1() {
        LinkedMassive<Integer> l = new LinkedMassive<>();
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);
        l.add(6);
        l.update(3, 10);
        Assert.assertEquals(10, l.get(3));

    }
    @Test
    public void equalsTest() {
        LinkedMassive<String> l = new LinkedMassive<>();
        LinkedMassive<String> d = new LinkedMassive<>();
        l.add("5");
        d.add("5");
        Assert.assertTrue(l.equals(d));
        l.add("7");
        d.add("3");
        Assert.assertFalse(l.equals(d));

    }
    @Test
    public void iteratorTest() {
        LinkedMassive<Integer> m = new LinkedMassive<>();
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
    public void hashCodeTest() {
        LinkedMassive<Integer> m = new LinkedMassive<>();
        LinkedMassive<Integer> n = new LinkedMassive<>();
        m.add(4);
        n.add(4);
        Assert.assertEquals(4, m.hashCode());
        m.add(5);
        n.add(5);
        m.add(11);
        n.add(11);
        Assert.assertTrue(m.hashCode() == n.hashCode());
        n.remove(4);
        Assert.assertFalse(m.hashCode() == n.hashCode());
    }
    @Test
    public void fatherTest() {
        LinkedMassive<String> ls = new LinkedMassive<>();
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
            Assert.assertTrue(e instanceof ListIsEmptyException);
        }
    }
}
