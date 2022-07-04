package de.novi.datastructures;

public class LinkedList<T> extends List<T> {
    private Node<T> head;

    public void add(T value) {
        if(isEmpty()) {
            length++;
            head = new Node<>(value);
            return;
        }
        inAdd(head, value);
    }

    public void addFirst(T value) {
        Node<T> prevHead = new Node<>(head);
        head = new Node<>(value);
        length++;
        head.setNext(prevHead);
    }

    public void inAdd(Node<T> node, T value) {
        if(node.getNext() == null) {
            length++;
            node.setNext(new Node<>(value));
            return;
        }
        inAdd(node.getNext(), value);
    }

    public T getFirst() {
        if(head == null) return null;
        return head.getValue();
    }

    public T getLast() {
        if(isEmpty()){
            System.err.println("Empty List");
            return null;
        } 
        return getLast(head).getValue();
    }

    private Node<T> getLast(Node<T> node) {
        if(node.getNext() == null) {
            return node;
        }
        return getLast(node.getNext());
    }

    @Override
    public String toString() {
        if(isEmpty()) return "[]";
        return "[ " + head.inToString("", head) + " ]";
    }


}
