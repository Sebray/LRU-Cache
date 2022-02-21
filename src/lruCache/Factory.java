package lruCache;
public class Factory {
    public LruCache createLruCache(Types typeK, Types typeV) {
        return switch (typeK) {//возвращаю кэш с определенным типом
            case INTEGER -> switch (typeV) {
                case INTEGER -> new ProxyLruCache<Integer, Integer>(new LruCacheImp<>());
                case STRING -> new ProxyLruCache<Integer, String>(new LruCacheImp<>());
            };
            case STRING -> switch (typeV) {
                case STRING -> new ProxyLruCache<String, String>(new LruCacheImp<>());
                case INTEGER -> new ProxyLruCache<String, Integer>(new LruCacheImp<>());
            };
        };
    }
}
