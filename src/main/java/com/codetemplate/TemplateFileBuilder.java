package com.codetemplate;

import com.codetemplate.enums.TemplateType;
import com.codetemplate.templateMessage.TemplateMessage;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 自动生成实体类客户端
 *
 * @author lvzb.software@qq.com
 */
public class TemplateFileBuilder {

    private final static String TEMPLATE_PARAM="entity";

    private static File javaFile = null;

    public static void createFile(TemplateMessage message, TemplateType fileType) {
        try {
            Configuration config = Configuration.getDefaultConfiguration();
            // 步骤一：指定 模板文件从何处加载的数据源，这里设置一个文件目录
            config.setDirectoryForTemplateLoading(new ClassPathResource("/").getFile());
            config.setObjectWrapper(new DefaultObjectWrapper());

            // 步骤二：获取 模板文件
            Template template = config.getTemplate(fileType.getTemplateName() + ".ftl");

            // 步骤三：创建 数据模型
            Map<String, Object> map = createTemplateFile(message,fileType);

            // 步骤四：合并 模板 和 数据模型
            // 创建.java类文件
            Writer javaWriter = new FileWriter(javaFile);
            template.process(map, javaWriter);
            javaWriter.flush();
            javaWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建数据模型
     *
     * @return
     */
    private static Map<String, Object> createTemplateFile(TemplateMessage message, TemplateType templateType) {
        Map<String, Object> map = new HashMap<String, Object>();
        javaFile = toFilename( message.getFilePath(), message.getFileName());
        map.put(TEMPLATE_PARAM, message);
        return map;
    }


    /**
     * 创建.java文件所在路径 和 返回.java文件File对象
     *
     * @param filePath 文件包名
     * @param fileName    文件名
     * @return
     */
    private static File toFilename(String filePath, String fileName) {
        filePath="."+filePath;
        File dir = new File(filePath);
        File file = new File(filePath, fileName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return file;
    }

}

