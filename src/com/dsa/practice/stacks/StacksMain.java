package com.dsa.practice.stacks;

public class StacksMain {

    public static void main(String[] args) {
        arrayStack();
    }

    public static void arrayStack() {
        ArrayStackOfStrings stack = new ArrayStackOfStrings(5);
        stack.push("hello");
        stack.push("world");
        stack.push("!");

        stack.display();

        System.out.println(stack.pop());

        stack.display();
    }

    public static void linkedListStack() {
        LinkedStackOfStrings stack = new LinkedStackOfStrings();

        stack.push("hello");
        stack.push("world");
        stack.push("!");

        stack.display();

        System.out.println(stack.pop());
        stack.display();
    }
}
