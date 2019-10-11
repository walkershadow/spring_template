package com.scheduler.objectpool.poolobjectFactory;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class StringBufferFactory extends BasePooledObjectFactory<StringBuffer>{

    @Override
    public StringBuffer create() throws Exception {
        return new StringBuffer(16);
    }

    @Override
    public PooledObject<StringBuffer> wrap(StringBuffer stringBuffer) {
        return new DefaultPooledObject<StringBuffer>(stringBuffer);
    }
}
