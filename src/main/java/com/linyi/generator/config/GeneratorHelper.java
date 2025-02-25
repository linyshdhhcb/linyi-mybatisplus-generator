package com.linyi.generator.config;

import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Files;
import com.linyi.generator.constant.GeneratorConstant;
import com.linyi.generator.entity.Column;
import com.linyi.generator.entity.FieldType;
import com.linyi.generator.entity.GeneratorConfig;
import com.linyi.utils.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.linyi.generator.constant.GeneratorConstant.BASE_PATH;

/**
 * @Author: linyi
 * @Date: 2025/2/24
 * @ClassName: GeneratorHelper
 * @Version: 1.0
 * @Description: 代码生成辅助类
 */
@Slf4j
@Component
public class GeneratorHelper {

    /**
     * 根据列信息和配置生成实体类文件
     *
     * @param columns 列信息列表，用于生成实体类的属性
     * @param configure 生成器配置，包含生成实体类所需的信息
     * @throws Exception 如果生成文件过程中发生错误
     */
    public void generateEntityFile(List<Column> columns, GeneratorConfig configure) throws Exception {
        // Java文件后缀
        String suffix = GeneratorConstant.JAVA_FILE_SUFFIX;// .java
        // 获取实体类文件的路径
        String path = getFilePath(configure, configure.getEntityPackage(), suffix, false);
        // 实体类模板名称
        String templateName = GeneratorConstant.ENTITY_TEMPLATE;
        // 创建实体类文件对象
        File entityFile = new File(path);
        // 将配置对象转换为JSON对象，用于模板数据
        JSONObject data = toJsonObject(configure);
        // 默认没有Date和BigDecimal类型
        data.put("hasDate", false);
        data.put("hasBigDecimal", false);
        // 遍历列信息，转换为驼峰命名并检查特殊类型
        columns.forEach(c -> {
            // 将列名转为小写并转换为驼峰命名
            c.setField(StringUtil.underscoreToCamel(StringUtils.lowerCase(c.getName())));
            // 检查是否包含日期类型
            if (StringUtils.containsAny(c.getType(), FieldType.DATE, FieldType.DATETIME, FieldType.TIMESTAMP)) {
                data.put("hasDate", true);//遇到这三种类型都转换为Date
            }
            // 检查是否包含BigDecimal类型
            if (StringUtils.containsAny(c.getType(), FieldType.DECIMAL, FieldType.NUMERIC)) {
                data.put("hasBigDecimal", true);
            }
        });
        // 将列信息添加到模板数据
        data.put("columns", columns);
        // 调用生成文件方法，根据模板和数据生成实体类文件
        this.generateFileByTemplate(templateName, entityFile, data);
    }

    /**
     * 根据配置信息生成Mapper文件
     *
     * @param columns   列信息列表，用于生成Mapper文件中的相关代码
     * @param configure 配置信息对象，包含Mapper文件生成所需的配置信息
     * @throws Exception 如果生成过程中发生错误，则抛出异常
     */
    public void generateMapperFile(List<Column> columns, GeneratorConfig configure) throws Exception {
        // Mapper文件的后缀名常量
        String suffix = GeneratorConstant.MAPPER_FILE_SUFFIX;
        // 获取Mapper文件的路径
        String path = getFilePath(configure, configure.getMapperPackage(), suffix, false);
        // Mapper文件的模板名称常量
        String templateName = GeneratorConstant.MAPPER_TEMPLATE;
        // 创建Mapper文件对象
        File mapperFile = new File(path);
        // 根据模板生成Mapper文件
        generateFileByTemplate(templateName, mapperFile, toJsonObject(configure));
    }

    /**
     * 根据列信息和配置生成服务层文件
     *
     * @param columns 列信息列表，用于生成服务层代码中的注释等信息
     * @param configure 生成器配置对象，包含生成代码所需的各类配置信息
     * @throws Exception 如果生成文件过程中发生错误，则抛出异常
     */
    public void generateServiceFile(List<Column> columns, GeneratorConfig configure) throws Exception {
        // 服务层文件的后缀
        String suffix = GeneratorConstant.SERVICE_FILE_SUFFIX;
        // 获取服务层文件的完整路径
        String path = getFilePath(configure, configure.getServicePackage(), suffix, true);
        // 服务层模板的名称
        String templateName = GeneratorConstant.SERVICE_TEMPLATE;
        // 创建一个File对象表示服务层文件
        File serviceFile = new File(path);
        // 根据模板生成服务层文件，传入配置信息作为参数
        generateFileByTemplate(templateName, serviceFile, toJsonObject(configure));
    }
    /**
     * 根据列信息和配置生成服务实现类文件
     *
     * @param columns 列信息列表，用于生成服务实现类中的相关代码
     * @param configure 生成器配置对象，包含生成服务实现类所需的信息
     * @throws Exception 如果生成文件过程中发生错误，则抛出异常
     */
    public void generateServiceImplFile(List<Column> columns, GeneratorConfig configure) throws Exception {
        // 服务实现类文件名后缀
        String suffix = GeneratorConstant.SERVICEIMPL_FILE_SUFFIX;
        // 获取服务实现类文件的路径
        String path = getFilePath(configure, configure.getServiceImplPackage(), suffix, false);
        // 服务实现类模板名称
        String templateName = GeneratorConstant.SERVICEIMPL_TEMPLATE;
        // 创建服务实现类文件对象
        File serviceImplFile = new File(path);
        // 根据模板生成服务实现类文件
        generateFileByTemplate(templateName, serviceImplFile, toJsonObject(configure));
    }

    /**
     * 根据列信息和配置生成Controller文件
     *
     * @param columns 列信息列表，用于生成Controller中的数据模型
     * @param configure 代码生成配置对象，包含生成Controller所需的各种配置信息
     * @throws Exception 如果生成文件过程中发生错误，则抛出异常
     */
    public void generateControllerFile(List<Column> columns, GeneratorConfig configure) throws Exception {
        // Controller文件的后缀名常量
        String suffix = GeneratorConstant.CONTROLLER_FILE_SUFFIX;

        // 构造Controller文件的完整路径
        String path = getFilePath(configure, configure.getControllerPackage(), suffix, false);

        // Controller模板文件的名称常量
        String templateName = GeneratorConstant.CONTROLLER_TEMPLATE;

        // 日志输出Controller文件的保存路径
        log.warn("Controller文件地址："+path);

        // 根据路径创建File对象，用于后续的文件生成
        File controllerFile = new File(path);

        // 通过模板生成Controller文件，传入配置信息作为参数
        generateFileByTemplate(templateName, controllerFile, toJsonObject(configure));
    }

    /**
     * 根据配置信息生成Mapper XML文件
     *
     * @param columns 列信息列表，用于生成Mapper XML文件中的SQL语句
     * @param configure 配置信息，包括包名、路径等生成文件所需的信息
     * @throws Exception 如果文件生成过程中发生错误，则抛出异常
     */
    public void generateMapperXmlFile(List<Column> columns, GeneratorConfig configure) throws Exception {
        // Mapper XML文件的后缀名常量
        String suffix = GeneratorConstant.MAPPERXML_FILE_SUFFIX;
        // 获取Mapper XML文件的路径
        String path = getFilePath(configure, configure.getMapperXmlPackage(), suffix, false);
        // Mapper XML文件的模板名称常量
        String templateName = GeneratorConstant.MAPPERXML_TEMPLATE;
        // 创建Mapper XML文件对象
        File mapperXmlFile = new File(path);
        // 将配置信息转换为JSON对象，以便在模板中使用
        JSONObject data = toJsonObject(configure);

        // 遍历列信息列表，将列名转换为驼峰命名法，并存储在columns列表中
        columns.forEach(c -> c.setField(StringUtil.underscoreToCamel(StringUtils.lowerCase(c.getName()))));

        // 将列信息列表添加到数据JSON对象中，以便在模板中使用
        data.put("columns", columns);

        // 根据模板名称、文件路径和数据生成Mapper XML文件
        generateFileByTemplate(templateName, mapperXmlFile, data);
    }


    /**
     * 根据模板生成文件
     *
     * @param templateName 模板名称，用于获取对应的模板
     * @param file 要生成的文件，包括路径和文件名
     * @param data 模板需要的数据，用于替换模板中的占位符
     * @throws Exception 如果文件生成过程中发生错误，则抛出异常
     */
    @SuppressWarnings("UnstableApiUsage")
    private void generateFileByTemplate(String templateName, File file, Object data) throws Exception {
        // 获取指定名称的模板
        Template template = getTemplate(templateName);
        // 创建文件的父目录，确保文件路径存在
        Files.createParentDirs(file);
        // 创建文件输出流，用于写入生成的内容到文件
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        // 使用BufferedWriter包装OutputStreamWriter，提高写入效率
        try (Writer out = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8), 10240)) {
            // 根据模板数据写入
            template.process(data, out);
        } catch (Exception e) {
            // 捕获异常并记录错误日志
            String message = "代码生成异常";
            log.error(message, e);
            // 抛出自定义异常，便于上层调用者处理
            throw new Exception(message);
        }
    }

    /**
     * 根据模板名称获取模板对象
     * 本方法用于通过Freemarker模板引擎加载指定名称的模板文件
     * 它首先配置模板引擎，然后根据类路径加载模板文件
     *
     * @param templateName 模板文件的名称，用于识别特定的模板
     * @return Template 加载成功的模板对象，用于后续的处理和渲染
     * @throws Exception 如果模板加载过程中发生错误，如找不到模板文件或读取模板文件时出现问题，则抛出异常
     */
    private Template getTemplate(String templateName) throws Exception {
        // 初始化Freemarker配置对象，并指定Freemarker模板的版本
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_20);

        // 获取模版引擎地址，即模板文件所在的路径
        String templatePath = this.getClass().getResource("/generator/templates/").getPath();

        // 设置Freemarker配置对象的模板加载目录
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        // 设置默认的字符编码，确保模板文件能正确读取非ASCII字符
        configuration.setDefaultEncoding("UTF-8");
        // 设置模板处理异常的方式，这里选择忽略处理异常
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

        // 根据模板名称加载模板对象
        return configuration.getTemplate(templateName);
    }

    /**
     * 根据配置信息生成文件路径
     *
     * @param configure GeneratorConfig对象，包含生成代码所需的配置信息
     * @param packagePath 代码包路径，用于确定生成文件的目录
     * @param suffix 文件后缀，决定生成文件的类型
     * @param serviceInterface 是否为服务接口，用于特殊处理服务接口的路径
     * @return 返回生成文件的完整路径
     */
    private static String getFilePath(GeneratorConfig configure, String packagePath, String suffix, boolean serviceInterface) {

        // 拼接Java文件的基础路径
        String filePath = configure.getJavaPath() + GeneratorConstant.BASE_PATH;

        // 将包路径转换为文件路径 如：com.linyi. + . + （entity、mapper、controller、service）
        filePath += packageConvertPath(configure.getBasePackage() + "." + packagePath);
        log.info("基础包：" + filePath);

        // 如果后缀是mapper.xml，表示是Mapper XML文件，需要特殊处理路径
        if (GeneratorConstant.MAPPERXML_FILE_SUFFIX.equals(suffix)) {
            // 拼接资源文件的基础路径，并定位到mapper目录
            filePath = configure.getResourcesPath() + packageConvertPath(packagePath + "/mapper");
        }

        // 最后拼接上类名和后缀，完成文件路径的生成
        filePath += configure.getClassName() + suffix;

        // 返回生成的文件路径
        return filePath;
    }


    /**
     * 将Java包名转换为对应的文件路径格式
     * 此方法用于将Java包名称中的点('.')替换为斜线('/')，以用于文件系统路径的表示
     * 如果包名中包含点('.')，则将其替换为斜线('/')；否则，直接在包名前后添加斜线
     *
     * @param packageName Java包名，例如com.example.package
     * @return 转换后的文件路径格式，例如/com/example/package/
     */
    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

    /**
     * 将Java对象转换为JSONObject对象
     * 此方法用于将传入的Java对象序列化为JSON字符串，然后将该字符串反序列化为JSONObject对象
     * 使用此方法可以方便地将任意Java对象转换为可操作的JSON对象
     *
     * @param o 要转换为JSONObject的Java对象
     * @return 转换后的JSONObject对象
     */
    private JSONObject toJsonObject(Object o) {
        // 将Java对象序列化为JSON字符串
        String jsonString = JSONObject.toJSON(o).toString();
        // 将JSON字符串反序列化为JSONObject对象
        return JSONObject.parseObject(jsonString);
    }



}
