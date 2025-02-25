package com.linyi.generator.impl;

import com.linyi.generator.config.GeneratorHelper;
import com.linyi.generator.config.SysProperties;
import com.linyi.generator.constant.GeneratorConstant;
import com.linyi.generator.entity.Column;
import com.linyi.generator.entity.GeneratorConfig;
import com.linyi.generator.entity.Table;
import com.linyi.generator.mapper.GeneratorMapper;
import com.linyi.generator.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.lang.management.LockInfo;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: linyi
 * @Date: 2025/2/25
 * @ClassName: GeneratorImpl
 * @Version: 1.0
 * @Description: 代码生成器实现类，用于根据数据库模式生成代码
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class GeneratorImpl {

    @Autowired
    private GeneratorHelper generatorHelper;

    @Autowired
    private GeneratorMapper generatorMapper;

    @Autowired
    private SysProperties sysProperties;

    /**
     * 指定的数据库模式中获取所有表
     *
     * @param schemaName 数据库模式名称，用于指定从哪个数据库模式中获取表信息
     */
    public void generateBySchema(String schemaName) {
        // 获取指定数据库模式下的所有表
        List<Table> tables = generatorMapper.getAllTable(schemaName);
        for (Table table : tables) {
            try {
                // 根据表名和表备注生成代码
                generate(schemaName, table.getName(), table.getRemark());
            } catch (Exception e) {
                log.error("代码生成异常...", e);
            }
        }
        log.info("所有表的代码生成已完成，{}", tables.toString());
        //结束
        System.exit(0);
    }

    @Transactional
    public void generateBySchemaTable(String schemaName, List<String> tableNames) {
        // 获取指定数据库模式下的所有表
        List<Table> tables = generatorMapper.getAllTable(schemaName);
        //获取表名转List
        List<String> tableList = tables.stream().map(Table::getName).collect(Collectors.toList());
        //检查输入的表名是否在数据库中存在
        if (isSubset(tableList, tableNames)) {
            log.info("输入的表名在数据库中存在，开始生成代码...");
        } else {
            log.error("输入的表名数据库中不存在");
            System.exit(0);
        }
        for (Table table : tables) {
            try {
                // 根据表名和表备注生成代码
                generate(schemaName, table.getName(), table.getRemark());
            } catch (Exception e) {
                log.error("代码生成异常...", e);
            }
        }
        log.info("所有表的代码生成已完成，{}", tables.toString());
        //结束
        System.exit(0);
    }

    /**
     * 根据数据库模式名、表名和备注生成代码文件
     *
     * @param schemaName 数据库模式名称
     * @param tableName  数据库表名称
     * @param remark     表的备注或描述
     * @throws Exception 如果生成过程中发生错误
     */
    private void generate(String schemaName, String tableName, String remark) throws Exception {
        // 获取系统属性中的代码生成配置
        GeneratorConfig generatorConfig = sysProperties.getGenerator();
        // 设置代码生成的表名
        generatorConfig.setTableName(tableName);
        // 将表名从下划线命名转换为驼峰命名，并设置为类名
        generatorConfig.setClassName(StringUtil.underscoreToCamel(tableName));
        // 设置表的备注作为类的注释
        generatorConfig.setTableComment(remark);
        // 获取每个表的所有列属性
        List<Column> columns = generatorMapper.getAllColumn(GeneratorConstant.DATABASE_TYPE, schemaName, tableName);
        // 根据列属性和配置信息生成实体类文件
        generatorHelper.generateEntityFile(columns, generatorConfig);
        // 根据列属性和配置信息生成Mapper接口文件
        generatorHelper.generateMapperFile(columns, generatorConfig);
        // 根据列属性和配置信息生成Mapper XML文件
        generatorHelper.generateMapperXmlFile(columns, generatorConfig);
        // 根据列属性和配置信息生成Service接口文件
        generatorHelper.generateServiceFile(columns, generatorConfig);
        // 根据列属性和配置信息生成Service实现类文件
        generatorHelper.generateServiceImplFile(columns, generatorConfig);
        // 根据列属性和配置信息生成Controller类文件
        generatorHelper.generateControllerFile(columns, generatorConfig);
    }

    public static boolean isSubset(List<?> a, List<?> b) {
        Set<?> setA = new HashSet<>(a);
        return setA.containsAll(b);
    }

}
