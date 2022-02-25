package lruCache;

import java.util.LinkedList;

public class ProxyLruCache<K, V> implements ILruCache<K, V> {

    private final ILruCache<K, V> proxy;
    private final LinkedList<K> history;
    private final int MAX_SIZE = 10;

    public ProxyLruCache(ILruCache<K, V> proxy) {
        this.proxy = proxy;
        history = new LinkedList<>();
    }

    @Override
    public V get(K key) {
        long startTime = System.nanoTime();
        long endTime;
        printHistory();
        V el = proxy.get(key);
        if (history.size() == MAX_SIZE)
            history.removeLast();
        history.addFirst(key);
        endTime = System.nanoTime();
        System.out.println("Время выполнения в мс: " + (endTime - startTime));
        return el;
    }

    @Override
    public void set(K key, V value) {
        V lastEl;
        long startTime = System.nanoTime();
        long endTime;

        if (proxy.getLimit() > proxy.getSize()) {
            proxy.set(key, value);
        } else{
            lastEl = proxy.getLastElement();
            proxy.set(key, value);
            System.out.println("Удаленный элемент: " + lastEl);
        }

        endTime = System.nanoTime();
        System.out.println("Время выполнения в мс: " + (endTime - startTime));
    }

    @Override
    public int getSize() {
        return proxy.getSize();
    }

    @Override
    public int getLimit() {
        return proxy.getLimit();
    }

    @Override
    public String toStringValue() {
        return proxy.toStringValue();
    }

    @Override
    public V getLastElement() {
        return proxy.getLastElement();
    }

    private void printHistory() {
        System.out.println("История обращений к ключам:");
        if (history.isEmpty())
            System.out.println("пусто");
        else {
            history.forEach((K val) -> System.out.println("\t* " + val));
        }
    }
}
