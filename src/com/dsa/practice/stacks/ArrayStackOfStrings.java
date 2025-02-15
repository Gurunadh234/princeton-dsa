package com.dsa.practice.stacks;

public class ArrayStackOfStrings implements StackInterface {

    private String[] stack;
    private int N;

    public ArrayStackOfStrings(int capacity) {
        stack = new String[capacity];
        N = 0;
    }

    private void resize(int capacity) {
        String[] newStack = new String[capacity];

        if (N >= 0) System.arraycopy(stack, 0, newStack, 0, N);
        stack = newStack;
    }

    @Override
    public boolean isEmpty() {
        return stack[0] == null;
    }

    @Override
    public void push(String item) {
        if(N == stack.length) resize(2 * stack.length);
        stack[N++] = item;
    }

    @Override
    public String pop() {
        if(N == 0) throw new StackOverflowError("Empty stack");

        String item = stack[--N];
        stack[N] = null;
        if(N > 0 && N == stack.length / 4) resize(stack.length / 2);
        return item;
    }

    @Override
    public void display() {
        if(isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }

        for(int i=N-1; i > 0; i--) {
            System.out.print(stack[i] + ", ");
        }
        System.out.println(stack[0]);
    }
}
