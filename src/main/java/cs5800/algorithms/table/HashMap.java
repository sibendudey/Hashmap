package cs5800.algorithms.table;

import cs5800.algorithms.hashing.HashingFunction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HashMap<U, V> implements Map<U, V> {
    private int size = 100;
    LinkedListNodeImpl<U, V> nodes[];
    // Default constructor
    public HashMap() {
        nodes = new LinkedListNodeImpl[size];
    }

    public HashMap(int size) {
        this.size = size;
        nodes = new LinkedListNodeImpl[this.size];
    }

    public void insert(U u, V v) throws IOException {
        int hashIndex = HashingFunction.hashing(u, size);

        if (nodes[hashIndex] == null) {
            nodes[hashIndex] = new LinkedListNodeImpl<U, V>(u, v);
        } else {
            System.out.println("Collision Found");
            LinkedListNodeImpl node;
            if ((node = findNode(u)) == null) {
                LinkedListNodeImpl newHead = new LinkedListNodeImpl<U, V>(u, v);
                LinkedListNodeImpl head = nodes[((int) hashIndex)];
                newHead.setNext(head);
                nodes[((int) hashIndex)] = newHead;
            } else node.setValue(v);
        }
    }

    public void delete(U u) throws IOException {
        int hashIndex = HashingFunction.hashing(u, size);
        LinkedListNodeImpl linkedListNode = nodes[hashIndex];
        if (linkedListNode == null) return;
        else nodes[hashIndex] = deleteNode(linkedListNode, u);
    }

    private LinkedListNodeImpl<U, V> deleteNode(final LinkedListNodeImpl<U,V> linkedListNode, final U u) {
        if (linkedListNode.getKey().equals(u))
            return linkedListNode.getNext();
        else {
            LinkedListNodeImpl temp = linkedListNode;
            while (temp.getNext() != null) {
                if (temp.getNext().getKey().equals(u)) {
                    temp.setNext(temp.getNext().getNext());
                    return linkedListNode;
                } else temp = temp.getNext();
            }
        }

        return linkedListNode;
    }

    // Must be called when V supports increment operations.
    // May be V should be of Type something that can be incremented??
    public V increase(U u) throws IOException {
        LinkedListNodeImpl<U, V> node = findNode(u);
        node.setValue(incrementer(node.getValue()));
        return node.getValue();
    }

    private V incrementer(V value) {
        if (value instanceof Integer) {
            Integer intValue = (Integer) value;
            Integer newValue = intValue + 1;
            return (V) newValue;
        }
        // V doesn't support increment operation
        throw new UnsupportedOperationException();
    }

    public V find(U u) throws IOException {
        LinkedListNodeImpl<U, V> node;
        return ((node = findNode(u)) != null) ? node.getValue() : null;
    }

    private LinkedListNodeImpl<U, V> findNode(U u) throws IOException {
        int hashIndex = (int) HashingFunction.hashing(u, size);
        LinkedListNodeImpl linkedListNode = nodes[hashIndex];
        while (linkedListNode != null) {
            if (linkedListNode.getKey().equals(u))
                return linkedListNode;

            linkedListNode = linkedListNode.getNext();
        }
        return null;
    }

    public List<U> listAllKeys() {
        return Arrays.stream(nodes)
                .collect(() -> new ArrayList<U>(),
                        (al, node) -> {
                            while (node != null) {
                                al.add(node.getKey());
                                node = node.getNext();
                            }
                        },
                        (al1, al2) -> al1.addAll(al2));
    }

    private class LinkedListNodeImpl<U, V> implements Node<U, V> {
        U key;
        V value;

        LinkedListNodeImpl next;

        public LinkedListNodeImpl(U key, V value) {
            this.key = key;
            this.value = value;
        }

        public U getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V v) {
            this.value = v;
        }

        public void setNext(LinkedListNodeImpl next) {
            this.next = next;
        }

        public LinkedListNodeImpl getNext() {
            return next;
        }
    }

    interface Node<U, V> {
        U getKey();

        V getValue();

        void setValue(V v);
    }


}
