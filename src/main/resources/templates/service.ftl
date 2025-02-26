package ${basePackage}.${servicePackage};

import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${entityPackage}.vo.${className?uncap_first}.*;
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
     * @param ${className?uncap_first}QueryVo 分页查询实体
     * @return PageResult<${className}>
     */
    PageResult<${className}> ${className?uncap_first}Page(${className}QueryVo ${className?uncap_first}QueryVo);

    /**
     * 新增
     *
     * @param ${className?uncap_first}AddVo 新增实体
     * @return Boolean
     */
    Boolean ${className?uncap_first}Add(${className}AddVo ${className?uncap_first}AddVo);

    /**
     * 修改
     *
     * @param ${className?uncap_first}UpdateVo 修改实体
     * @return Boolean
     */
    Boolean ${className?uncap_first}Update(${className}UpdateVo ${className?uncap_first}UpdateVo);
}
