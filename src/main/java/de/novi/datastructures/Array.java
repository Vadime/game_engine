package de.novi.datastructures;

public class Array<T> extends List<T> {

    private T[] array;

    public Array(T[] array) {
        this.array = array;
    }

    public LinkedList<T> toLinkedList() {
        LinkedList<T> list = new LinkedList<>();
        for(T item : array) {
            list.add(item);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public int size() {
        return array.length;
    }
    
}
