package com.codetemplate.enums;

/**
 * 属性类型枚举类
 */
public enum TemplateType {
    ENTITY("Entity", "PO", ".java", "实体类"),
    DAO("Dao", "Mapper", ".java", "持久层"),
    SERVICE("Service", "Service", ".java", "服务层"),
    SERVICE_IMPL("ServiceImpl",  "ServiceImpl",".java", "服务层"),
    MAPPER("Mapper", "Mapper", ".xml", "SQL_MAPPER");

    private TemplateType(String templateName, String nameSuffix, String fileSuffix, String message) {
        this.templateName = templateName;
        this.nameSuffix = nameSuffix;
        this.fileSuffix = fileSuffix;
        this.message = message;
    }

    private String templateName;
    private String nameSuffix;
    private String fileSuffix;
    private String message;

    public String getTemplateName() {
        return templateName;
    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public String getMessage() {
        return message;
    }
}