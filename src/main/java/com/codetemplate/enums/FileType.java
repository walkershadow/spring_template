package com.codetemplate.enums;

/**
 * 属性类型枚举类
 */
public enum FileType {

    JAVA_FILE(".java", "java文件"),
    XML_FILE(".xml",  "xml文件");

    private FileType(String suffix,String message) {
        this.suffix = suffix;
        this.message = message;
    }

    private String suffix;
    private String message;

    public String getSuffix() {
        return suffix;
    }

    public String getMessage() {
        return message;
    }
}