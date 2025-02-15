package com.dsa.problems.queue;

import com.dsa.practice.queues.QueueInterface;

import java.util.NoSuchElementException;

public class DualStackQueue implements QueueInterface {

    private CustomStack enQueueStack;
    private CustomStack deQueueStack;

    public DualStackQueue() {
        this.enQueueStack = new CustomStack();
        this.deQueueStack = new CustomStack();
    }

    @Override
    public boolean isEmpty() {
        return enQueueStack.isEmpty() && deQueueStack.isEmpty();
    }

    @Override
    public void enqueue(String item) {
        enQueueStack.push(item);
    }

    @Override
    public String dequeue() {
        if(isEmpty()) throw new NoSuchElementException("Queue underflow");

        if(!deQueueStack.isEmpty()) {
            while(!enQueueStack.isEmpty()) {
                deQueueStack.push(enQueueStack.pop());
            }
        }

        return deQueueStack.pop();
    }

    @Override
    public void display() {

    }
}
