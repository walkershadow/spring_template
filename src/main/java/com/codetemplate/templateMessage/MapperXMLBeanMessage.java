package com.codetemplate.templateMessage;

import java.util.List;

/**
 * 实体类
 */
public class MapperXMLBeanMessage extends TemplateMessage {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 实体对象类 全限定名
     */
    private String entityClass;

    /**
     * 持久层对象类 全限定名
     */
    private String daoClass;

    public MapperXMLBeanMessage(String entityName) {
        super(entityName);
    }

    public MapperXMLBeanMessage(String entityName,List<Property> properties) {
        super(entityName,properties);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public String getDaoClass() {
        return daoClass;
    }

    public void setDaoClass(String daoClass) {
        this.daoClass = daoClass;
    }
}

