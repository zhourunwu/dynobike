package com.jeeplus.modules.sports.common.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

public final class EhcacheUtil
{
  private static final CacheManager cacheManager = CacheManager.getInstance();

  private static Cache cache = new Cache(new CacheConfiguration("systemCache", 5000).memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.FIFO).timeoutMillis(300L).timeToLiveSeconds(3600L));

  static { cacheManager.addCache(cache); }

  public static void putItem(String key, Object item) {
    if (cache.get(key) != null) {
      cache.remove(key);
    }
    Element element = new Element(key, item);
    cache.put(element);
  }
  public static void removeItem(String key) {
    cache.remove(key);
  }
  public static void updateItem(String key, Object value) {
    putItem(key, value);
  }
  public static Object getItem(String key) {
    Element element = cache.get(key);
    if (element != null)
    {
      return element.getObjectValue();
    }
    return null;
  }
}