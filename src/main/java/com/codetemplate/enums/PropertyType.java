package com.codetemplate.enums;

/**
 * 属性类型枚举类
 */
public enum PropertyType {
    BYTE("Byte"),
    SHORT("Short"),
    INT("Integer"),
    TINYINT("Integer"),
    INTEGER("Integer"),
    LONG("Long"),
    BOOLEAN("Boolean"),
    FLOAT("Float"),
    DOUBLE("Double"),
    STRING("String"),
    DATE("Date"),
    TIMESTAMP("Date"),
    DECIMAL("Decimal");

    private PropertyType(String typeName) {
        this.typeName = typeName;
    }

    private String typeName;

    public java.lang.String getTypeName() {
        return typeName;
    }
}