package com.codetemplate.utils;

import com.google.common.base.CaseFormat;

public class TemplateStringUtils {

    public static String classNameToObjectName(String className){
        return className.replaceFirst(String.valueOf(className.charAt(0)),String.valueOf(className.toLowerCase().charAt(0)));
    }

    public static String DataBasePropertyToJavaProperty(String dataBaseProperty){
       return   CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, dataBaseProperty);
    }

}
