package com.codetemplate.templateMessage;

import java.util.List;

/**
 * 基础类
 */
public class TemplateMessage {

    /**
     * 相关实体类名
     */
    private String entityName;

    /**
     * 当前文件路径
     */
    private String filePath;

    /**
     * 当前文件名
     */
    private String fileName;

    /**
     * 属性集合
     */
    private List<Property> properties;


    public TemplateMessage(String entityName) {
        this.entityName = entityName;
    }

    public TemplateMessage(String entityName,List<Property> properties) {
        this.entityName = entityName;
        this.properties = properties;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

}

