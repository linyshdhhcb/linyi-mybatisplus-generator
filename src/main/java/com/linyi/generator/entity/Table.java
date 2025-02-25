package com.linyi.generator.entity;

import lombok.Data;

/**
 * @Author: linyi
 * @Date: 2025/2/25
 * @ClassName: Table
 * @Version: 1.0
 * @Description: 表属性
 */
@Data
public class Table {
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 数据量（行）
     */
    private Long dataRows;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
}
