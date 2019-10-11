package com.codetemplate.utils;

import com.codetemplate.config.CodeTemplateConfig;
import com.codetemplate.enums.TemplateType;
import org.apache.commons.lang3.StringUtils;

public class PathUtils {

    public static String getFilePackage(String entityName, TemplateType templateType) {
        return getFilePackage(templateType)+"." + entityName + templateType.getNameSuffix();
    }

    public static String getFilePackage(TemplateType templateType) {
        return getFilePathByTemplateType(templateType).replaceAll("/",".");
    }

    public static String getFilePath( TemplateType templateType) {
        return getFilePathByTemplateType(templateType);
    }

    private static String getFilePathByTemplateType(TemplateType templateType) {
        switch (templateType) {
            case ENTITY:
                return CodeTemplateConfig.getEntityPackage();
            case DAO:
                return CodeTemplateConfig.getDaoPackage();
            case SERVICE:
                return CodeTemplateConfig.getServiceInterfacePackage();
            case SERVICE_IMPL:
                return CodeTemplateConfig.getServiceImplementPackage();
            case MAPPER:
                return CodeTemplateConfig.getMapperPackage();
            default:
                return StringUtils.EMPTY;
        }
    }
}
