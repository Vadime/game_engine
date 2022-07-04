package de.novi.datastructures;

public class Node<T> {

    private T value;
    private Node<T> next;

    public Node(T d) {
        value = d;
        next = null;
    }
    Node(Node<T> node) {
        value = node.value;
        next = node.next;
    }

    String inToString(String str, Node<T> node) {
        if(node == null) {
            return str;
        } else if(node.next == null) {
            str += String.valueOf(node.value);
        } else {
            str += String.valueOf(node.value) + ", ";
        }

        return inToString(str, node.next);
    }

    /**
     * @param next the next to set
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * @param value the value to set
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * @return the next
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * @return the value
     */
    public T getValue() {
        return value;
    }


}
