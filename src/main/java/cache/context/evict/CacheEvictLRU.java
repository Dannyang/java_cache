package cache.context.evict;

import cache.ICacheEntry;
import cache.ICacheEvict;
import cache.ICacheEvictContext;
import cache.core.CacheEntry;

import java.security.Key;
import java.util.LinkedHashMap;
import java.util.Map;

public class CacheEvictLRU<K, V> extends LinkedHashMap<K, V> implements ICacheEvict<K, V> {

    private volatile boolean removeFlag = false;

    private Map.Entry<K, V> eldest = null;

    @Override
    public void updateKey(K key) {
        super.put(key, null);
    }

    @Override
    public void removeKey(K key) {
        super.remove(key);
    }

    public CacheEvictLRU() {
        super(16, 0.75f, true);
    }

    @Override
    public ICacheEntry<K, V> evict(ICacheEvictContext<K, V> context) {
        updateKey(context.key());
        ICacheEntry<K, V> result = null;
        if (context.limit() <= context.cache().size()) {
            this.removeFlag = Boolean.TRUE;
            V put = super.put(context.key(), null);
            K eldestKey = this.eldest.getKey();
            V theValRemoved = context.cache().remove(eldestKey);
            result = new CacheEntry<>(eldestKey, theValRemoved);
        } else {
            this.removeFlag = Boolean.FALSE;
        }
        return result;
    }

    // 重写这个方法后每次linkHashMap执行插入操作都会讲最近使用的元素放在队列头部
    // 且这个方法会在put方法的putVal时被调用，在上面执行evict方法时，执行了父类的put方法此时会将下面重写的方法进行调用
    // 此时this的eldest会被赋值
    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        this.eldest = eldest;
        return removeFlag;
    }

}
