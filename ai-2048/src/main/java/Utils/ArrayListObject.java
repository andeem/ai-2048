/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author emil
 */
public class ArrayListObject<T> {
    
    private ArrayListObject<T> next;
    private T obj;

    public ArrayListObject(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }
    
    public ArrayListObject<T> getNext() {
        return next;
    }

    public void setNext(ArrayListObject<T> next) {
        this.next = next;
    }
    
    
}
