package com.codetemplate;

import com.codetemplate.config.CodeTemplateConfig;
import com.codetemplate.enums.PropertyType;
import com.codetemplate.enums.TemplateType;
import com.codetemplate.templateMessage.JavaBeanMessage;
import com.codetemplate.templateMessage.MapperXMLBeanMessage;
import com.codetemplate.templateMessage.Property;
import com.codetemplate.templateMessage.TemplateMessage;
import com.codetemplate.utils.PathUtils;
import com.codetemplate.utils.TemplateStringUtils;
import com.google.common.base.CaseFormat;

import java.util.ArrayList;
import java.util.List;

public class TemplateMessageFactory {

    public static TemplateMessage getTemplateMessage(String entityName, List<Property> properties, TemplateType templateType) {
        switch (templateType) {
            case MAPPER:
                return getMapperXMLBeanMessage(entityName,properties,templateType);
            case ENTITY:
            case DAO:
            case SERVICE:
            case SERVICE_IMPL:
                return getJavaBeanMessage(entityName,properties,templateType);
            default:
                return null;
        }
    }

    private static JavaBeanMessage getJavaBeanMessage(String entityName, List<Property> properties, TemplateType templateType) {
        JavaBeanMessage message = new JavaBeanMessage(entityName, properties);

        String fileName = entityName + templateType.getNameSuffix() + templateType.getFileSuffix();

        message.setFilePath(CodeTemplateConfig.getJavaBeanBasePackage() + PathUtils.getFilePath(templateType));
        message.setFilePackage(PathUtils.getFilePackage(templateType));
        message.setFileName(fileName);
        message.setObjectName(TemplateStringUtils.classNameToObjectName(entityName));

        // 将引用添加到实体对象中
        message.setImports(getImports(entityName, templateType));
        return message;
    }


    private static MapperXMLBeanMessage getMapperXMLBeanMessage(String entityName, List<Property> properties, TemplateType templateType) {
        MapperXMLBeanMessage message = new MapperXMLBeanMessage(entityName, properties);

        String fileName = entityName + templateType.getNameSuffix() + templateType.getFileSuffix();

        message.setFilePath(PathUtils.getFilePath(templateType));
        message.setFileName(fileName);
        message.setDaoClass(PathUtils.getFilePackage(entityName, TemplateType.DAO));
        message.setEntityClass(PathUtils.getFilePackage(entityName, TemplateType.ENTITY));
        message.setTableName(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityName));

        return message;
    }

    private static List<String> getImports(String entityName, TemplateType fileType) {
        List<String> imports = new ArrayList<>();
        switch (fileType) {
            case ENTITY:
                break;
            case DAO:
                imports.add(PathUtils.getFilePackage(entityName, TemplateType.ENTITY));
                break;
            case SERVICE:
                imports.add(PathUtils.getFilePackage(entityName, TemplateType.ENTITY));
                break;
            case SERVICE_IMPL:
                imports.add(PathUtils.getFilePackage(entityName, TemplateType.ENTITY));
                imports.add(PathUtils.getFilePackage(entityName, TemplateType.DAO));
                imports.add(PathUtils.getFilePackage(entityName, TemplateType.SERVICE));
                break;
            case MAPPER:
                break;
            default:
                break;
        }
        return imports;
    }


}
