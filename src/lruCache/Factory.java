package lruCache;

public class Factory {
    public LruCache createLruCache(Types typeK, Types typeV) {
        return switch (typeK) {//возвращаю кэш с определенным типом
            case Types.INTEGER -> switch (typeV) {
                case Types.INTEGER -> new ProxyLruCache<Integer, Integer>(new LruCacheImp<>());
                case Types.STRING -> new ProxyLruCache<Integer, String>(new LruCacheImp<>());
            };
            case Types.STRING -> switch (typeV) {
                case Types.STRING -> new ProxyLruCache<String, String>(new LruCacheImp<>());
                case Types.INTEGER -> new ProxyLruCache<String, Integer>(new LruCacheImp<>());
            };
        };
    }
}
