package com.linyi.generator.entity;

import lombok.Data;

/**
 * @Author: linyi
 * @Date: 2025/2/25
 * @ClassName: Column
 * @Version: 1.0
 * @Description: 字段属性
 */
@Data
public class Column {
    /**
     * 名称
     */
    private String name;
    /**
     * 是否为主键
     */
    private Boolean isKey;
    /**
     * 类型
     */
    private String type;

    /**
     * 注释
     */
    private String remark;
    /**
     * 属性名称
     */
    private String field;
}
