package com.scheduler.objectpool;

import com.model.po.base.BasePO;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.scheduler.objectpool.poolobjectFactory.BasePOFactory;
import com.scheduler.objectpool.poolobjectFactory.StringBufferFactory;

import java.util.HashMap;
import java.util.Map;

public class ObjectPoolFactory {
    private static Logger logger = LoggerFactory.getLogger(ObjectPoolFactory.class);

    private static Map<String,GenericObjectPool> poolMap = new HashMap<>();

    public static <T> GenericObjectPool getPool(Class<T> clazz) {
        if (!poolMap.keySet().contains(clazz.getSimpleName())) {
            logger.info("不存在该对象池:" + clazz.getSimpleName());
            return null;
        }
        return poolMap.get(clazz.getSimpleName());
    }


    public static <T> void createObjectPool(Class<T> clazz, BasePooledObjectFactory<T> factory) {
        poolMap.put(clazz.getSimpleName(), new GenericObjectPool<T>(factory, new GenericObjectPoolConfig()));
    }

    public static <T> void createObjectPool(Class<T> clazz, BasePooledObjectFactory<T> factory, GenericObjectPoolConfig config) {
        poolMap.put(clazz.getSimpleName(), new GenericObjectPool<T>(factory, config));
    }

    public static void main(String[] args) throws Exception {
        createObjectPool(StringBuffer.class,new StringBufferFactory());
        createObjectPool(BasePO.class, new BasePOFactory());
        GenericObjectPool<StringBuffer> objectPool1 = getPool(StringBuffer.class);
        GenericObjectPool<StringBuffer> objectPool2 = getPool(BasePO.class);
        objectPool1.borrowObject();
        objectPool2.borrowObject();
    }

}
