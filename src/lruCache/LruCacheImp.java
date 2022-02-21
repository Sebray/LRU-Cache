package lruCache;

import java.util.HashMap;
import java.util.StringJoiner;

public class LruCacheImp<K, V> implements LruCache<K, V> {
    public static class Node<K, V> {
        private static class CacheElement<K, V> {
            K key;
            V value;

            public CacheElement(K key, V value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public String toString() {
                return key + ":" + value;
            }
        }

        private CacheElement<K, V> element;
        private Node<K, V> left;
        private Node<K, V> right;

        public Node(CacheElement<K, V> el, Node<K, V> left, Node<K, V> right) {
            element = el;
            this.left = left;
            this.right = right;
        }
    }

    private static final int MAX_SIZE = 3;
    private final HashMap<K, Node<K, V>> hashMap;
    private Node<K, V> head;
    private Node<K, V> tail;
    private int size;

    public LruCacheImp() {
        size = 0;
        hashMap = new HashMap<>(MAX_SIZE);
        head = null;
        tail = null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = hashMap.get(key);
        if (node == null)
            return null;

        if (node == head)
            return node.element.value;

        moveToFirst(node);
        return node.element.value;
    }

    @Override
    public void set(K key, V value) {
        Node.CacheElement<K, V> cacheElement = new Node.CacheElement<>(key, value);
        if (hashMap.containsKey(key)) {
            changeValueOfKey(key, cacheElement);
            return;
        }

        if (size == MAX_SIZE) {
            deleteLastElement();
            addElement(key, cacheElement, false);
            return;
        }

        addElement(key, cacheElement, size == 0);
        size++;
    }

    private void changeValueOfKey(K key, Node.CacheElement<K, V> cacheElement) {
        Node<K, V> node = hashMap.get(key);
        hashMap.get(key).element = cacheElement;
        moveToFirst(node);
    }

    private void moveToFirst(Node<K, V> node) {
        if (node == head)
            return;
        if (node == tail && size > 1)
            tail = node.left;
        if (node.right != null)
            node.right.left = node.left;
        if (node.left != null)
            node.left.right = node.right;
        node.right = head;
        node.left = null;
        head.left = node;
        head = node;
    }

    private void deleteLastElement() {
        Node<K, V> node = tail;

        if (tail.left != null) {
            tail = tail.left;
            tail.right = null;
        }
        hashMap.remove(node.element.key);
    }

    private void addElement(K key, Node.CacheElement<K, V> cacheElement, boolean isEmpty) {
        Node<K, V> node;
        if (isEmpty) {
            node = new Node<>(cacheElement, null, null);
            tail = node;
        } else {
            node = new Node<>(cacheElement, null, head);
            head.left = node;
        }
        head = node;
        hashMap.put(key, node);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getLimit() {
        return MAX_SIZE;
    }

    @Override
    public String toStringValue() {
        StringJoiner stringJoiner = new StringJoiner("; ", "", "");
        Node<K, V> node = head;
        if (node == null)
            return "Пустой";
        do {
            stringJoiner.add(node.element.toString());
            node = node.right;
        } while (node != null);
        return stringJoiner.toString();
    }

    public V getLastElement() {
        return tail.element.value;
    }
}
