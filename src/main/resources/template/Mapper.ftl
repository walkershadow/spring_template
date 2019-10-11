<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${entity.daoClass}">
    <resultMap id="BaseResultMap" type="${entity.entityClass}">
        <#list entity.properties as property>
        <result property="${property.javaPropertyName}" column="${property.dataBasePropertyName}"/>
        </#list>
    </resultMap>

    <sql id="baseSql">
       <#list entity.properties as property>
           ${property.dataBasePropertyName}<#if (property_has_next)>,</#if>
       </#list>
    </sql>

    <insert id="insert" keyProperty="id" useGeneratedKeys="true"
            parameterType="${entity.entityClass}">
            INSERT INTO ${entity.tableName}(
            <#list entity.properties as property>
                ${property.dataBasePropertyName}<#if (property_has_next)>,</#if>
            </#list>
            ) VALUES (
           <#list entity.properties as property>
                ${r"#{"}${property.javaPropertyName}}<#if (property_has_next)>,</#if>
           </#list>
            )
    </insert>





</mapper>