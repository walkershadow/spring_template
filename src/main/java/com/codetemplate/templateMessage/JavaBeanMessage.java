package com.codetemplate.templateMessage;

import java.util.List;

/**
 * 实体类
 */
public class JavaBeanMessage extends TemplateMessage {

    /**
     * 当前文件包名
     */
    private String filePackage;

    /**
     * java对象名
     */
    private String objectName;
    /**
     * 属性集合
     */
    private List<String> imports;

    public JavaBeanMessage(String entityName) {
        super(entityName);
    }

    public JavaBeanMessage(String entityName,List<Property> properties) {
        super(entityName,properties);
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getFilePackage() {
        return filePackage;
    }

    public void setFilePackage(String filePackage) {
        this.filePackage = filePackage;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }
}

