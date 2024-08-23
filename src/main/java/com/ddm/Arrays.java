package com.ddm;

import java.util.Iterator;
import java.util.Objects;

public class Arrays{
    interface DynamicMassive<T> {
        void add(T obj);
        void remove(T obj) throws ElementNotFoundException, ListIsEmptyException;
        int size();
        void update(int id, T obj);
        T get(int index);
        @Override
        boolean equals(Object obj);
        @Override
        int hashCode();
        default int pow(int value, int powValue) {
            int result = 1;
            for (int i = 1; i <= powValue; i++) {
                result = result * value;
            }
            return result;
        }
    }

    public static class ArrayMassive<T> implements DynamicMassive, Iterable<T> {
        @Override
        public Iterator<T> iterator() {
            return new MyIterator();
        }
        private class MyIterator implements Iterator<T> {
            private int pos;
            @Override
            public boolean hasNext() {
                return pos < size;
            }

            @Override
            public T next() {
                T result = DynMassive[pos];
                pos++;
                return result;
            }
        }
        ArrayMassive(int startSize, int incSize, int decSize) {
            this.start = startSize;
            this.inc = incSize;
            this.dec = decSize;
        }
        final private int start, inc, dec;
        private int size = 0;
        private T[] DynMassive;
        int position = 0;

        @Override
        public void add(Object obj) {
            if (size == 0) {
                size = start;
                DynMassive = (T[]) new Object[size];
            }
            if (position > size() - 1) {
                grow();
            }
            DynMassive[position] = (T) obj;
            position++;
        }

        public void remove(Object obj) throws ElementNotFoundException {
            int num = getNum(DynMassive, obj);
            if (num != 0) {
                for (int i = 0; i < num; i++) {
                    int id = getIndex(DynMassive, obj);
                    check(id);
                }
            } else {
                throw new ElementNotFoundException("Element is not present");
            }
        }

        private void check(int id) {
            if (id >= 0) {
                position--;
                DynMassive[id] = null;
                for (int j = id; j < position; j++) {
                    T temp = DynMassive[j];
                    DynMassive[j] = DynMassive[j + 1];
                    DynMassive[j + 1] = temp;
                }
                if (position <= size() - dec) {
                    shrink();
                }
            }
        }

        public int size() {
            return size;
        }

        public void update(int id, Object obj) {
            if (id > size() || id < 0) {
                System.out.println("Invalid index");
            } else {
                DynMassive[id] = (T) obj;
            }
        }

        public void grow() {
            T[] NewArray = (T[]) new Object[size() + inc];
            for (int i = 0; i < size(); i++) {
                NewArray[i] = DynMassive[i];
            }
            this.DynMassive = NewArray;
            size += inc;
        }

        public void shrink() {
            T[] NewArray = (T[]) new Object[size() - dec];
            int j = 0;
            int search = size() - dec;
            for (int i = 0; i < search; i++) {
                NewArray[i] = DynMassive[i];
            }
            this.DynMassive = NewArray;
            size -= dec;
        }

        public int getIndex(Object[] obj, Object target) {
            for (int i = size() - 1; i >= 0; i--) {
                if (obj[i] == target) {
                    return i;
                }
            }
            return -1;
        }

        public int getNum(Object[] obj, Object target) {
            int counter = 0;
            for (int i = 0; i < size(); i++) {
                if (obj[i] == target) {
                    counter++;
                }
            }
            return counter;
        }

        public Object get(int index) {
            return DynMassive[index];
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            ArrayMassive that = (ArrayMassive) obj;
            if(this.size() == that.size()) {
                for(int i = 0; i < that.size(); i++) {
                    if(that.DynMassive[i] != this.DynMassive[i]) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return java.util.Arrays.toString(DynMassive);
        }
        @Override
        public int hashCode() {
            int result = 0;
            for(int i = 0; i < size; i++) {
                result += DynMassive[i].hashCode()*pow(31, i);
            }
            return result;
        }
    }
    public static class LinkedMassive<T> implements DynamicMassive, Iterable<T> {
        @Override
        public Iterator<T> iterator() {
            return new MyIterator();
        }
        private class MyIterator implements Iterator<T> {
            private int pos;
            @Override
            public boolean hasNext() {
                return pos < size;
            }

            @Override
            public T next() {
                T result = (T) get(pos);
                pos++;
                return result;
            }
        }
        private static class Entry<T> {
            Entry next;
            T data;
        }

        private Entry<T> head, tail;

        int size;

        @Override
        public void add(Object obj) {
            Entry<T> newEntry = new Entry<>();
            newEntry.data = (T) obj;
            if (tail == null) {
                head = newEntry;
                tail = newEntry;
            } else {
                tail.next = newEntry;
                tail = newEntry;
            }
            size++;
        }

        public void remove(Object target) throws ElementNotFoundException, ListIsEmptyException {
            if(head == null) {
                throw new ListIsEmptyException("List is empty, u can't remove non-existent element");
            }
            if(head == tail && head.data == target) {
                head = null;
                tail = null;
                size--;
                return;
            }
            if(head.data == target) {
                head = head.next;
                size--;
                return;
            }
            else {
                Entry<T> search = head;
                while (search.next != null) {
                    if(search.next.data == target) {
                        if(tail == search.next) {
                            tail = search;
                        }
                        search.next = search.next.next;
                        size--;
                        return;
                    }
                    search = search.next;
                }
                throw new ElementNotFoundException("Element is not present in this list");
            }
        }

        public void update(int index, Object obj) {
            Entry<T> search = head;
            Entry<T> pointy = head;
            for(int i = 0; i< index; i++){
                search =  pointy.next;
                pointy = pointy.next;
            }
            search.data = (T) obj;
        }
        public int size() {
            return size;
        }

        public Object get(int index) {
            T giveUp = null;
            Entry<T> search = head;
            for (int i = 0; i <= index; i++) {
                giveUp = search.data;
                search = search.next;
            }
            return giveUp;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            LinkedMassive<?> that = (LinkedMassive<?>) obj;
            Entry curr = head;
            Entry thatCurr = that.head;
            if(size == that.size) {
                for(int i = 0; i < size; i++) {
                    if(curr.data != thatCurr.data) {
                        return false;
                    }
                    curr = curr.next;
                    thatCurr = thatCurr.next;
                }
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            Entry<T> hashCalculator = head;
            int result = 0;
            for(int i = 0; i < size; i++) {
                result = (int)hashCalculator.data*pow(31, i);
                hashCalculator = hashCalculator.next;
            }
            return result;
        }


    }

    public static void main(String[] args) {}
}


class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String message) {
        super(message);
    }
}
class ListIsEmptyException extends RuntimeException {
    public ListIsEmptyException(String message) {
        super(message);
    }
}