package factory;

import lruCache.ILruCache;
import lruCache.LruCacheImp;
import lruCache.ProxyLruCache;

public class LruCacheStrIntFactory implements IFactory{
    @Override
    public ILruCache createLruCache() {
        return new ProxyLruCache<String, Integer>(new LruCacheImp());
    }
}
