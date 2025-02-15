package com.dsa.practice.queues;

import com.dsa.practice.stacks.LinkedStackOfStrings;

public class LinkedQueueOfStrings implements QueueInterface {

    private Node head;
    private Node tail;

    private class Node {
        String item;
        Node next;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void enqueue(String item) {
        if(isEmpty()) {
            head = tail = new Node();
            head.item = item;
        }
        else {
            Node node = new Node();
            node.item = item;
            tail.next = node;
            tail = node;
        }
    }

    @Override
    public String dequeue() {
        String item = head.item;
        head = head.next;

        return item;
    }

    @Override
    public void display() {
        Node node = head;
        while(node.next != null) {
            System.out.print(node.item + ", ");
            node = node.next;
        }
        System.out.println(node.item);
    }
}
