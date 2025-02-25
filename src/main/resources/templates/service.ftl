package ${basePackage}.${servicePackage};

import ${basePackage}.${entityPackage}.${className};
import com.linyi.pig.common.model.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @Author: linyi
* @Date: ${date}
* @ClassName: ${className}Service
* @Version: 1.0
* @Description: ${tableComment} 服务层
*/
public interface ${className}Service extends IService<${className}> {
    /**
     * 分页查询
     *
     * @param pageResponse 分页实体
     * @param ${className?uncap_first} 查询实体
     * @return PageResult<${className}>
     */
    PageResult<${className}> ${className?uncap_first}Page(PageResponse pageResponse,${className} ${className?uncap_first});

    /**
     * 新增
     *
     * @param ${className?uncap_first} ${className?uncap_first}
     * @return Boolean
     */
    Boolean ${className?uncap_first}Add(${className} ${className?uncap_first});

    /**
     * 修改
     *
     * @param ${className?uncap_first} ${className?uncap_first}
     * @return Boolean
     */
    Boolean ${className?uncap_first}Update(${className} ${className?uncap_first});
}
