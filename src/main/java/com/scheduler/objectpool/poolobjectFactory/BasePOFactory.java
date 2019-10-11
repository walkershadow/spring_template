package com.scheduler.objectpool.poolobjectFactory;

import com.model.po.base.BasePO;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class BasePOFactory extends BasePooledObjectFactory<BasePO>{

    @Override
    public BasePO create() throws Exception {
        return new BasePO();
    }

    @Override
    public PooledObject<BasePO> wrap(BasePO basePO) {
        return new DefaultPooledObject<BasePO>(basePO);
    }
}
