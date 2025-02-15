package com.dsa.practice.stacks;

public class LinkedStackOfStrings implements StackInterface {

    private Node first;

    private class Node {
        String item;
        Node next;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public void push(String item) {
        if(isEmpty()) {
            first = new Node();
            first.item = item;
        }
        else {
            Node firstOld = first;
            first = new Node();
            first.item = item;
            first.next = firstOld;
        }
    }

    @Override
    public String pop() {
        if(isEmpty()) {
            throw new StackOverflowError("Popping empty list");
        }

        String item = first.item;
        first = first.next;

        return item;
    }

    @Override
    public void display() {
        if(isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }

        Node node = first;
        while(node.next != null) {
            System.out.print(node.item + ", ");
            node = node.next;
        }
        System.out.println(node.item);
    }
}
