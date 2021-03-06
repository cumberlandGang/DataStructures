package edu.cccnj;

import java.util.ArrayList;

public class Main {

    public static void testLinkedList() {
        /*
        * Let's test out basic LinkedList class!
         *
        * To make creation of it easier, we have a method called generate which we can give any number of arguments
        * to to make the LinkedList for us.
        */

        LinkedList<Integer> linkedList1 = LinkedList.generate(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        // What's the length of the list?
        System.out.println("Length: " + linkedList1.length());

        // What's the fifth element of the list?
        System.out.println("Fifth element: " + linkedList1.get(5));

        // Let's grow the list and print out the new length
        linkedList1.append(10);
        System.out.println("New length: " + linkedList1.length());

        // Let's test out our toString method
        System.out.println("\nToString test: ");
        System.out.println(linkedList1.toString());

        // Time to test mapping!
        LinkedList<Integer> linkedList2 = linkedList1.map(x -> x + 1);

        // What is the last element of the mapped list?
        System.out.println("\nThe new list, after the mapped function: ");
        System.out.println(linkedList2);

        // Let's cascade some higher-order functions! Let's get the sum of all even numbers from 1 - 10 after 3 is added to them
        int sum  = linkedList2
                .filter(x -> x % 2 == 0)
                .map(x -> x + 3)
                .reduce((x, y) -> x + y, 0);

        System.out.println("Sum of even numbers from 1-10 after 3 is added on: " + sum);
    }

    public static void testBuffers() {
        System.out.println();

        Buffers.Queue testQueue = (Buffers.Queue)Buffers.make(
                Buffers.Types.Queue,
                1, 2, 3, 4, 5
        );

        System.out.println("Getting an item from the queue, which should be 1, is: " + testQueue.dequeue());

        testQueue.enqueue(3);

        System.out.println("After adding an item, it should give 2: " + testQueue.dequeue());

        Buffers.Stack testStack = (Buffers.Stack)Buffers.make(
                Buffers.Types.Stack,
                1, 2, 3, 4, 5
        );

        System.out.println("Getting an item from the stack, which should be 5: " + testStack.pop());

        testStack.push(6);

        System.out.println("After adding an item, it should be 6: " + testStack.pop());

        System.out.println("See the design notes in Buffers to see a better way to make this class");
    }

    public static void main(String[] args) {
	// This code will test the data structures in lieu of JUnit, for the time being.

        testLinkedList();

        /*Now, it's time to test our Buffers types*/
        testBuffers();
    }
}
