package com.linyi.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: linyi
 * @Date: 2025/2/24
 * @ClassName: PageResult
 * @Version: 1.0
 * @Description: 分页结果类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    /**
     * 当前页码
     */
    private long pageNum;

    /**
     * 每页记录数
     */
    private long pageSize;

    /**
     * 当前页数据列表
     */
    private List<T> data;

    /**
     * 总记录数
     */
    private long total;
}
