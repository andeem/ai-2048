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
public class StackObject<T> {
    
    private StackObject<T> prev;
    private T obj;

    public StackObject(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }
    
    public StackObject<T> getPrev() {
        return prev;
    }

    public void setPrev(StackObject<T> prev) {
        this.prev = prev;
    }
    
    
}
