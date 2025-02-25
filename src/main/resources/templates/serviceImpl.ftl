package ${basePackage}.${serviceImplPackage};

import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${mapperPackage}.${className}Mapper;
import ${basePackage}.${servicePackage}.${className}Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import com.linyi.pig.common.model.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Optional;

/**
* @Author: linyi
* @Date: ${date}
* @ClassName: ${className}ServiceImpl
* @Version: 1.0
* @Description: ${tableComment} 服务实现层
*/
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@SuppressWarnings({"unchecked", "rawtypes"})
public class ${className}ServiceImpl extends ServiceImpl<${className}Mapper, ${className}> implements ${className}Service {

    @Autowired
    private ${className}Mapper ${className?uncap_first}Mapper;

    @Override
    public PageResult<${className}> ${className?uncap_first}Page(PageResponse pageResponse,${className} ${className?uncap_first}) {
        LambdaQueryWrapper<${className}> queryWrapper = new LambdaQueryWrapper<>();
        //TODO 需要补充条件查询

        //分页数据
        Page<${className}> page = new Page<>(pageResponse.getPageNum(),pageResponse.getPageSize());
        //查询数据
        Page<${className}> pageNew = ${className?uncap_first}Mapper.selectPage(page, queryWrapper);
        //返回分页数据
        return new PageResult<>(pageNew.getRecords(), pageNew.getTotal(), pageNew.getPages(), pageResponse.getPageNum(), pageResponse.getPageSize());
    }

    @Override
    public Boolean ${className?uncap_first}Add(${className} ${className?uncap_first}){
        //插入数据
        return ${className?uncap_first}Mapper.insert(${className?uncap_first}) > 0 ? true : false;
    }

    @Override
    public Boolean ${className?uncap_first}Update(${className} ${className?uncap_first}){
        //根据ID查询数据
        ${className} byId=this.getById(${className?uncap_first}.getId());
        //判断数据是否存在
        if(Optional.ofNullable(${className?uncap_first}).isEmpty()){
            log.error("数据不存在");
            return false;
        }
        //复制属性
        BeanUtils.copyProperties(${className?uncap_first}, byId);
        //修改数据
        return ${className?uncap_first}Mapper.updateById(byId) > 0 ? true : false;
    }
}
