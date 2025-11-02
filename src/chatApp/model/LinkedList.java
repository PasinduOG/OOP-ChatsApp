package chatApp.model;

import chatApp.view.ChatWindow;

public class LinkedList {

    private Node first;

    public int size() {
        int count = 0;
        Node temp = first;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public void add(ChatWindow chatWindow) {
        Node node = new Node(chatWindow);
        if (first == null) {
            first = node;
            return;
        }
        Node lastNode = first;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        lastNode.next = node;
    }

    public void add(int index, ChatWindow chatWindow) {
        if (index >= 0 && index <= size()) {
            Node node = new Node(chatWindow);
            if (index == 0) {
                node.next = first;
                first = node;
                return;
            }
            int count = 0;
            Node temp = first;
            while (count < index - 1) {
                count++;
                temp = temp.next;
            }
            node.next = temp.next;
            temp.next = node;
        }
    }

    public void addFirst(ChatWindow chatWindow) {
        add(0, chatWindow);
    }

    public void addLast(ChatWindow chatWindow) {
        add(size(), chatWindow);
    }

    public void remove(ChatWindow chatWindow) {
        if (first == null) {
            return;
        }

        if (first.chatWindow.equals(chatWindow)) {
            first = first.next;
            return;
        }

        Node prev = first;
        Node current = first.next;

        while (current != null) {
            if (current.chatWindow.equals(chatWindow)) {
                prev.next = current.next;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public ChatWindow[] toArray() {
        ChatWindow[] chatWindows = new ChatWindow[size()];
        Node temp = first;
        for (int i = 0; i < chatWindows.length; i++) {
            chatWindows[i] = temp.chatWindow;
            temp = temp.next;
        }
        return chatWindows;

    }

    class Node {

        ChatWindow chatWindow;
        Node next;

        public Node(ChatWindow chatWindow) {
            this.chatWindow = chatWindow;
        }
    }
}
