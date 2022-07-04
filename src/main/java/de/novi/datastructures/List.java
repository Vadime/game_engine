package de.novi.datastructures;

public abstract class List<T> {
    int length = 0;

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }

}
