package ${entity.filePackage};

import lombok.Getter;
import lombok.Setter;
import java.util.*;
<#list entity.imports as import>
import ${import};
</#list>

/**
 * This code is generated by FreeMarker
 */
public class ${entity.entityName}PO{

<#list entity.properties as property>
    /**
     * ${property.remark}
     */
    @Getter
    @Setter
    private ${property.javaType} ${property.javaPropertyName};

</#list>

<#list entity.properties as property>
    public ${property.javaType} get${property.javaPropertyName?cap_first}() {
        return ${property.javaPropertyName};
    }

    public void set${property.javaPropertyName?cap_first}(${property.javaType} ${property.javaPropertyName}) {
        this.${property.javaPropertyName} = ${property.javaPropertyName};
    }

</#list>
}
