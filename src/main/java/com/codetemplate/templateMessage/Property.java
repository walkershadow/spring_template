package com.codetemplate.templateMessage;

import com.codetemplate.enums.PropertyType;

/**
 * 实体对应的属性类
 */
public class Property {

    /**
     * 属性数据类型
     */
    private String javaType;

    /**
     * Java中_属性名称
     */
    private String javaPropertyName;

    /**
     * 数据库中_属性字段名称
     */
    private String dataBasePropertyName;

    /**
     * 数据库中_属性字段注释
     */
    private String remark;

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaPropertyName() {
        return javaPropertyName;
    }

    public void setJavaPropertyName(String javaPropertyName) {
        this.javaPropertyName = javaPropertyName;
    }

    public String getDataBasePropertyName() {
        return dataBasePropertyName;
    }

    public void setDataBasePropertyName(String dataBasePropertyName) {
        this.dataBasePropertyName = dataBasePropertyName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
