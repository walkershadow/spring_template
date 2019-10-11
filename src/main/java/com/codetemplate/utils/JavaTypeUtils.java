package com.codetemplate.utils;


import com.codetemplate.enums.PropertyType;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JavaTypeUtils {
    private static Map<Integer, String> typeMap;

    static {
        typeMap = new HashMap<>();

        typeMap.put(Types.INTEGER, PropertyType.INTEGER.getTypeName());
        typeMap.put(Types.TINYINT, PropertyType.TINYINT.getTypeName());
        typeMap.put(Types.BIGINT, PropertyType.LONG.getTypeName());
        typeMap.put(Types.BOOLEAN, PropertyType.BOOLEAN.getTypeName());
        typeMap.put(Types.DATE, PropertyType.DATE.getTypeName());
        typeMap.put(Types.DECIMAL, PropertyType.DECIMAL.getTypeName());
        typeMap.put(Types.DOUBLE, PropertyType.DOUBLE.getTypeName());
        typeMap.put(Types.FLOAT, PropertyType.FLOAT.getTypeName());
        typeMap.put(Types.VARCHAR, PropertyType.STRING.getTypeName());
        typeMap.put(Types.LONGVARCHAR, PropertyType.STRING.getTypeName());
        typeMap.put(Types.TIMESTAMP, PropertyType.TIMESTAMP.getTypeName());

    }


    public static String getJavaType(int jdbcTypeCode) {
        return typeMap.get(jdbcTypeCode);
    }
}
