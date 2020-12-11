package com.kevin.base.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author kevin.yang
 */
public class CacheDemo {
    private Map<String, Object> cache = new HashMap<String, Object>();

    public static void main(String[] args) {

    }

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public Object getData(String key) {
        rwl.readLock().lock();
        Object value = null;
        try {
            value = cache.get(key);
            if (null == value) {
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    if (null == value) {
                        //实际是去数据库取数据
                        value = "aaa";
                    }
                } finally {
                    rwl.writeLock().unlock();
                }
                rwl.readLock().lock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }
        return value;
    }

}

