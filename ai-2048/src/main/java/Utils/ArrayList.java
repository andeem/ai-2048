/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Iterator;

/**
 *
 * @author emil
 */
public class ArrayList<T> implements Iterable<T> {

    private ArrayListObject<T> root;
    int size;

    public ArrayList() {
    }

    public boolean isEmpty() {
        return root == null;
    }

    public T remove(T obj) {
        if (root == null) {
            throw new AbstractMethodError();
        }

        if (root.getNext() == null && root.getObj().equals(obj)) {
            T toReturn = root.getObj();
            root = null;
            return toReturn;

        }

        ArrayListObject<T> current = root;
        ArrayListObject<T> prev = root;
        while (!current.getObj().equals(obj)) {
            prev = current;
            current = current.getNext();

            if (current == null) {
                throw new AbstractMethodError();
            }
        }
        prev.setNext(current.getNext());

        return current.getObj();
    }

    public T remove(int i) {
        if (root == null) {
            throw new AbstractMethodError();
        }
        ArrayListObject<T> prev = root;

        if (root.getNext() == null) {
            root = null;
            return prev.getObj();
        }

        ArrayListObject<T> current = root;

        int k = 0;
        while (k < i) {
            prev = current;
            current = current.getNext();
            if (current == null) {
                throw new AbstractMethodError();
            }
            k++;
        }
        prev.setNext(current.getNext());

        return current.getObj();
    }

    public T get(int i) {
        if (root == null) {
            throw new AbstractMethodError();
        }

        ArrayListObject<T> current = root;
        ArrayListObject<T> prev = root;

        int k = 0;
        while (k < i) {
            prev = current;
            current = current.getNext();
            if (current == null) {
                throw new AbstractMethodError();
            }
            k++;
        }

        return current.getObj();
    }

    public void add(T obj) {
        ArrayListObject<T> toAdd = new ArrayListObject<>(obj);
        if (root == null) {
            root = toAdd;
            return;
        }

        ArrayListObject<T> current = root;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(toAdd);
    }

    public void clear() {
        this.root = null;
    }

    public int size() {
        if (this.root == null) {
            return 0;
        }
        int i = 1;
        ArrayListObject<T> current = root;
        while (current.getNext() != null) {
            current = current.getNext();
            i++;
        }
        return i;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator(root);
    }

    class ListIterator<T> implements Iterator<T> {

        private ArrayListObject<T> current;

        public ListIterator(ArrayListObject<T> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            if (current == null) {
                return false;
            }
            return current.getObj() != null;
        }

        @Override
        public T next() {
            T toreturn = current.getObj();
            current = current.getNext();
            return toreturn;
        }
    }

}
