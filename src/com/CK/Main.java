package com.CK;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(5);
        stack.push(1);
        stack.push(5);
        System.out.println(stack.top());
        System.out.println(stack.popMax());
        System.out.println(stack.top());
        System.out.println(stack.peekMax());
        System.out.println(stack.pop());
        System.out.println(stack.top());
    }
}

class MaxStack {
    Stack<Integer> stack;
    int max;

    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<>();
        max = Integer.MIN_VALUE;
    }

    public void push(int x) {
        if (x >= max){
            stack.push(max);
            stack.push(x);
            max = x;
        }
    }

    public int pop() {
        int res = stack.pop();
        if (res == max)
            max = stack.pop();
        return res;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return max;
    }

    public int popMax() {
        Stack<Integer> temp = new Stack<>();

        while (stack.peek() != max){
            temp.push(stack.pop());
        }

        int res = stack.pop();
        max = stack.pop();
        while(!temp.isEmpty()){
            push(temp.pop());
        }
        return res;
    }
}

//Double Linked List + TreeMap
class MaxStack2 {
    TreeMap<Integer, List<Node>> map;
    DoubleLinkedList dll;

    public MaxStack2() {
        map = new TreeMap();
        dll = new DoubleLinkedList();
    }

    public void push(int x) {
        Node node = dll.add(x);
        if(!map.containsKey(x))
            map.put(x, new ArrayList<Node>());
        map.get(x).add(node);
    }

    public int pop() {
        int val = dll.pop();
        List<Node> L = map.get(val);
        L.remove(L.size() - 1);
        if (L.isEmpty()) map.remove(val);
        return val;
    }

    public int top() {
        return dll.peek();
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = peekMax();
        List<Node> L = map.get(max);
        Node node = L.remove(L.size() - 1);
        dll.unlink(node);
        if (L.isEmpty()) map.remove(max);
        return max;
    }
}

class DoubleLinkedList {
    Node head, tail;

    public DoubleLinkedList() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public Node add(int val) {
        Node x = new Node(val);
        x.next = tail;
        x.prev = tail.prev;
        tail.prev = tail.prev.next = x;
        return x;
    }

    public int pop() {
        return unlink(tail.prev).val;
    }

    public int peek() {
        return tail.prev.val;
    }

    public Node unlink(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }
}

class Node {
    int val;
    Node prev, next;
    public Node(int v) {val = v;}
}


