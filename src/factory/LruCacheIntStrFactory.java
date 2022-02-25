package factory;

import lruCache.ILruCache;
import lruCache.LruCacheImp;
import lruCache.ProxyLruCache;

public class LruCacheIntStrFactory implements IFactory{
    @Override
    public ILruCache createLruCache() {
        return new ProxyLruCache<Integer, String>(new LruCacheImp<>());
    }
}
