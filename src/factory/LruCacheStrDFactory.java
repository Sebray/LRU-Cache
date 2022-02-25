package factory;

import lruCache.ILruCache;
import lruCache.LruCacheImp;
import lruCache.ProxyLruCache;

public class LruCacheStrDFactory implements IFactory{
    @Override
    public ILruCache createLruCache() {
        return new ProxyLruCache<String, Double>(new LruCacheImp());
    }
}
