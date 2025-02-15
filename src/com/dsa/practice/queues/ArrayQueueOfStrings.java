package com.dsa.practice.queues;

public class ArrayQueueOfStrings implements QueueInterface {

    private String[] queue;
    private int head;
    private int tail;

    public ArrayQueueOfStrings(int capacity) {
        queue = new String[capacity];
    }

    @Override
    public boolean isEmpty() {
        return queue[head] == null;
    }

    private void resize(int capacity) {
        String[] newQueue = new String[capacity];
        System.arraycopy(queue, 0, newQueue, 0, queue.length);
        queue = newQueue;
    }

    @Override
    public void enqueue(String item) {
        if(tail == queue.length - 1) {
            resize(2 * queue.length);
        }
        queue[tail++] = item;
    }

    @Override
    public String dequeue() {
        String item = queue[head];

        for(int i=0; i<tail; i++) {
            queue[i] = queue[i+1];
        }
        tail--;

        if( tail > 0 && tail == queue.length / 4) resize(queue.length / 2);

        return item;
    }

    @Override
    public void display() {
        if(isEmpty()) {
            System.out.println("Empty queue");
            return;
        }

        for(int i=0; i<tail-1; i++) {
            System.out.print(queue[i] + ", ");
        }
        System.out.println(queue[tail - 1]);
    }
}
