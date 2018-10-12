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
public class Stack<T>{
    
    private StackObject<T> head;

    public Stack() {
        this.head = null;
    }
    
    public void push(T obj) {
        StackObject<T> temp = head;
        head = new StackObject<>(obj);
        head.setPrev(temp);
    }
    
    public T pop() {
        if (head == null) {
            throw new NullPointerException();
        }
        T obj = head.getObj();
        head = head.getPrev();
        return obj;
    }
    
    public boolean empty() {
        return head == null;
    }
}
