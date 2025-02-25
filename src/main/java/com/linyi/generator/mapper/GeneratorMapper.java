package com.linyi.generator.mapper;

import com.linyi.generator.entity.Column;
import com.linyi.generator.entity.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @Author: linyi
 * @Date: 2025/2/25
 * @ClassName: GeneratorMapper
 * @Version: 1.0
 * @Description:
 */
@Mapper
public interface GeneratorMapper {
    /**
     * 查询所有表
     * @param schemaName 数据库
     * @return
     */
    List<Table> getAllTable(String schemaName);

    /**
     * 查询所有列
     * @param databaseType 数据库类型
     * @param schemaName 数据库名
     * @param tableName 表名
     * @return
     */
    List<Column> getAllColumn(String databaseType, String schemaName, String tableName);
}
