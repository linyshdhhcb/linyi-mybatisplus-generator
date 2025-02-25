package com.linyi.generator.entity;

import lombok.Data;

/**
 * @Author: linyi
 * @Date: 2025/2/25
 * @ClassName: FieldType
 * @Version: 1.0
 * @Description: 字段类型
 */
@Data
public class FieldType {
    public static final String DATE = "date";
    public static final String DATETIME = "datetime";
    public static final String TIMESTAMP = "timestamp";
    public static final String DECIMAL = "decimal";
    public static final String NUMERIC = "numeric";
}
