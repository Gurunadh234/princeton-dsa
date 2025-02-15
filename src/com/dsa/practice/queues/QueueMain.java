package com.dsa.practice.queues;

public class QueueMain {

    public static void main(String[] args) {
        arrayQueue();
    }

    public static void arrayQueue() {
        ArrayQueueOfStrings queue = new ArrayQueueOfStrings(5);
        System.out.println(queue.isEmpty());
        queue.enqueue("hello");
        queue.enqueue("world");
        queue.enqueue("!");

        queue.display();
        System.out.println(queue.isEmpty());

        System.out.println(queue.dequeue());
        queue.display();
    }

    public static void linkedQueue() {
        LinkedQueueOfStrings queue = new LinkedQueueOfStrings();
        System.out.println(queue.isEmpty());
        queue.enqueue("hello");
        queue.enqueue("world");
        queue.enqueue("!");

        queue.display();
        System.out.println(queue.isEmpty());

        System.out.println(queue.dequeue());
        queue.display();
    }
}
