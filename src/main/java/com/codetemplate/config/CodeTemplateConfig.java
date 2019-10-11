package com.codetemplate.config;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class CodeTemplateConfig {
    private static final Logger logger = LoggerFactory.getLogger(CodeTemplateConfig.class);

    /**
     * 数据库连接驱动
     */
    private static String driverClass;
    /**
     * 数据库连接地址
     */
    private static String connectionURL;
    /**
     * 数据库用户名
     */
    private static String userName;
    /**
     * 数据库密码
     */
    private static String password;

    /**
     * 模版文件路径
     */
    private static String templateFilePath;

    /**
     * javaBean根路径
     */
    private static String javaBeanBasePackage;
    /**
     * 实体路径
     */
    private static String entityPackage;

    /**
     * sql mapper路径
     */
    private static String mapperPackage;

    /**
     * 持久层路径
     */
    private static String daoPackage;

    /**
     * 服务层接口路径
     */
    private static String serviceInterfacePackage;

    /**
     * 服务层实现路径
     */
    private static String serviceImplementPackage;

    /**
     * 允许创建文件的数据表
     */
    private static List<String> tables;

    /**
     * 不允许创建文件的数据表
     */
    private static List<String> blackTables;


    static {
        ResourceBundle bundle = null;
        try {
            bundle = ResourceBundle.getBundle("code_template");
        } catch (MissingResourceException e) {
            logger.error("找不到配置文件:code_template.properties", e);
        }

        if (null != bundle) {
            driverClass = bundle.getString("jdbc.driverClassName");
            connectionURL = bundle.getString("jdbc.url");
            userName = bundle.getString("jdbc.username");
            password = bundle.getString("jdbc.password");
            templateFilePath = bundle.getString("template.file.path");
            javaBeanBasePackage = bundle.getString("java.bean.base.package");
            entityPackage = bundle.getString("entity.package");
            daoPackage = bundle.getString("dao.package");
            serviceInterfacePackage = bundle.getString("service.interface.package");
            serviceImplementPackage = bundle.getString("service.implement.package");
            mapperPackage = bundle.getString("mapper.package");

            String tableFilter = bundle.getString("table.filter");
            if (StringUtils.isNotEmpty(tableFilter)) {
                tables =  Arrays.asList(tableFilter.split(","));;
            }
            String tableBlackFilter = bundle.getString("table.black.filter");
            if (StringUtils.isNotEmpty(tableBlackFilter)) {
                blackTables = Arrays.asList(tableBlackFilter.split(","));
            }
        }
    }


    public static String getDriverClass() {
        return driverClass;
    }

    public static String getConnectionURL() {
        return connectionURL;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }

    public static String getTemplateFilePath() {
        return templateFilePath;
    }

    public static String getJavaBeanBasePackage() {
        return javaBeanBasePackage;
    }

    public static String getEntityPackage() {
        return entityPackage;
    }

    public static String getMapperPackage() {
        return mapperPackage;
    }

    public static String getDaoPackage() {
        return daoPackage;
    }

    public static String getServiceInterfacePackage() {
        return serviceInterfacePackage;
    }

    public static String getServiceImplementPackage() {
        return serviceImplementPackage;
    }


    public static List<String> getTables() {
        return tables;
    }

    public static List<String> getBlackTables() {
        return blackTables;
    }
}
