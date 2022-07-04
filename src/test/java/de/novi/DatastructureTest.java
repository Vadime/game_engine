package de.novi;

import org.junit.jupiter.api.Test;

import de.novi.datastructures.LinkedList;
import de.novi.datastructures.Queue;

public class DatastructureTest {
    @Test
    public void testQueue() {
        Queue<Integer> list = new Queue<>();
        list.push(0);
        list.push(1);
        list.push(2);
        list.push(3);

        list.pop();


        System.out.println("List of Elements: " + list.toString());

        System.out.println("Front Element: " + list.getFront().getValue());
        System.out.println("Rear Element: " + list.getRear().getValue());
        System.out.println("First Element: " + list.first());
        System.out.println("List Size: " + String.valueOf(list.size()));
    }

    @Test
    public void testLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        //list.add(2);
        list.add(3);
        list.addFirst(4);


        System.out.println("List of Elements: " + list.toString());

        System.out.println("First Element: " + list.getFirst());
        System.out.println("Last Element: " + list.getLast());
        System.out.println("List Size: " + String.valueOf(list.size()));
    }
}
