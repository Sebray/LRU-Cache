package factory;

import lruCache.ILruCache;

public interface IFactory {
    ILruCache createLruCache();
}
