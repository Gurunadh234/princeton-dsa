package com.dsa.problems.queue_stack;

import com.dsa.practice.stacks.StackInterface;

import java.util.NoSuchElementException;

public class CustomStack implements StackInterface {
    private Node head;

    private class Node {
        private String item;
        private Node next;

        public Node(String item) {
            this.item = item;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void push(String item) {
        if(head == null) {
            head = new Node(item);
        }
        else {
            Node oldHead = head;
            head = new Node(item);
            head.next = oldHead;
        }
    }

    @Override
    public String pop() {
        if(isEmpty()) {
            throw new NoSuchElementException("stack underflow");
        }
        String item = head.item;
        head = head.next;

        return item;
    }

    public String peek() {
        return head.item;
    }

    @Override
    public void display() {
    }
}
