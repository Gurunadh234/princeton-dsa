package com.dsa.practice.queues;

public interface QueueInterface {
    boolean isEmpty();
    void enqueue(String item);
    String dequeue();
    void display();
}
