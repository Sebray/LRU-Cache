import java.util.LinkedList;

public class ProxyLruCache<K, V> implements LruCache<K, V>{

    private final LruCacheImp<K, V> lruCacheImp;
    private final LinkedList<K> history;
    private final int MAX_SIZE = 10;

    public ProxyLruCache(LruCacheImp<K, V> lruCacheImp) {
        this.lruCacheImp = lruCacheImp;
        history = new LinkedList<>();
    }

    @Override
    public V get(K key) {
        long startTime = System.nanoTime();
        printHistory();
        V el = lruCacheImp.get(key);
        if (history.size() == MAX_SIZE)
            history.removeLast();
        history.addFirst(key);
        long endTime = System.nanoTime();
        System.out.println("Время выполнения: " + (endTime - startTime));
        return el;
    }

    @Override
    public void set(K key, V value) {
        V lastEl;
        long startTime = System.nanoTime();

        if (lruCacheImp.getLimit() == lruCacheImp.getSize()) {
            lastEl = lruCacheImp.getLastElement();
            lruCacheImp.set(key, value);
            System.out.println("Удаленный элемент: " + lastEl);
        } else
            lruCacheImp.set(key, value);

        long endTime = System.nanoTime();
        System.out.println("Время выполнения: " + (endTime - startTime));
    }

    @Override
    public int getSize() {
        return lruCacheImp.getSize();
    }

    @Override
    public int getLimit() {
        return lruCacheImp.getLimit();
    }

    @Override
    public String toStringValue() {
        return lruCacheImp.toStringValue();
    }

    private void printHistory(){
        System.out.println("История обращений к ключам:");
        if(history.isEmpty())
            System.out.println("пусто");
        else{
            history.forEach((K val)-> System.out.println("\t* " + val));
        }
    }
}
