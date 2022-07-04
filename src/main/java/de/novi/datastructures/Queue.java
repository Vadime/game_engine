package de.novi.datastructures;

public class Queue<T> extends List<T> {

    private Node<T> front, rear;

    public Queue() {
        length = 0;
        front = rear = null;
    }

    public void push(T value) {
        Node<T> node = new Node<>(value);
        if(isEmpty()) {
            front = node;
        }else {
            rear.setNext(node);
        }
        rear = node;
        length++;
    }

    public void pop() {
        if(isEmpty()) {
            System.err.println("Empty Queue");
            return;
        }
        front = front.getNext();
        length--;
        if(isEmpty()) {
            rear = null;
        }
    }

    public T first() {
        if(isEmpty()) {
            System.err.println("Empty Queue");
            return null;
        }
        return front.getValue();
    }

    /**
     * @return the front
     */
    public Node<T> getFront() {
        return front;
    }

    /**
     * @return the rear
     */
    public Node<T> getRear() {
        return rear;
    }


    @Override
    public String toString() {
        if(isEmpty()) return "[]";
        return "[ " + front.inToString("", front) + " ]";
    }




}
