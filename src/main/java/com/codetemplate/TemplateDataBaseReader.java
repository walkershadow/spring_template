package com.codetemplate;

import com.codetemplate.config.CodeTemplateConfig;
import com.codetemplate.enums.TemplateType;
import com.codetemplate.templateMessage.Property;
import com.codetemplate.utils.JavaTypeUtils;
import com.codetemplate.utils.TemplateStringUtils;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.CaseFormat;
import reactor.util.CollectionUtils;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器
 */
public class TemplateDataBaseReader {

    public static void start() {
        System.out.println("开始连接数据库!");

        Connection connection = null;
        ResultSet rs = null;
        try {
            Class.forName(CodeTemplateConfig.getDriverClass());
            //获取数据库连接
            connection = DriverManager.getConnection(CodeTemplateConfig.getConnectionURL(),
                    CodeTemplateConfig.getUserName(),
                    CodeTemplateConfig.getPassword());
            //数据库元数据信息
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            //数据库-表集合
            ResultSet tableSet = databaseMetaData.getTables(connection.getCatalog(), "SCOTT", null, new String[]{"TABLE"});

            //遍历表
            while (tableSet.next()) {
                String tableName = tableSet.getString("TABLE_NAME");
                //过滤允许的表
                if (!CollectionUtils.isEmpty(CodeTemplateConfig.getTables())) {
                    if (!CodeTemplateConfig.getTables().contains(tableName)) {
                        continue;
                    }
                }
                //过滤不允许的表
                if (!CollectionUtils.isEmpty(CodeTemplateConfig.getBlackTables())) {
                    if (CodeTemplateConfig.getBlackTables().contains(tableName)) {
                        continue;
                    }
                }

                //数据库-表-字段集合
                ResultSet columnRet = databaseMetaData.getColumns(connection.getCatalog(), "SCOTT", tableName, null);
                //实体名
                String entityName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
                //属性列表
                List<Property> properties = getPropertyList(columnRet);

                //循环各类模版创建文件
                for (TemplateType templateType : TemplateType.values()) {
                    TemplateFileBuilder.createFile(TemplateMessageFactory.getTemplateMessage(entityName, properties, templateType), templateType);
                }
                System.out.println("数据表[" + tableSet.getString("TABLE_NAME") + "]各类文件创建成功!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据库对应所有数据表创建文件完毕!");
    }

    /**
     * 获取数据库-表中所有字段 组合为属性列表
     *
     * @param columnRet
     * @return
     * @throws SQLException
     */
    private static List<Property> getPropertyList(ResultSet columnRet) throws SQLException {
        List<Property> properties = new ArrayList<>();
        //遍历字段
        while (columnRet.next()) {//循环输出EMP表的列名以及其他信息
            Property property = new Property();
            //数据库字段名
            property.setDataBasePropertyName(columnRet.getString("COLUMN_NAME"));
            //java属性名
            property.setJavaPropertyName(TemplateStringUtils.DataBasePropertyToJavaProperty(columnRet.getString("COLUMN_NAME")));
            //java类型
            property.setJavaType(JavaTypeUtils.getJavaType(columnRet.getInt("DATA_TYPE")));
            //数据库注释
            property.setRemark(columnRet.getString("REMARKS"));

            properties.add(property);

            System.out.println(columnRet.getInt("DATA_TYPE"));
            System.out.println(JSONObject.toJSONString(property));
        }

        return properties;
    }

}