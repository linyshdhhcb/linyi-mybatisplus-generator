package ${basePackage}.${controllerPackage};

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${servicePackage}.${className}Service;
import ${basePackage}.${entityPackage}.vo.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.linyi.pig.common.model.*;

import java.io.Serializable;
import java.util.List;


/**
* @Author: linyi
* @Date: ${date}
* @ClassName: ${className}Controller
* @Version: 1.0
* @Description: ${tableComment} 控制层
*/

@Tag(name = "${tableComment}管理模块")
@Slf4j
@Validated
@RestController
@RequestMapping("/${className?uncap_first}")
@SuppressWarnings({"unchecked", "rawtypes"})
public class ${className}Controller{

    @Autowired
    private ${className}Service ${className?uncap_first}Service;

    /**
     * 分页查询${tableComment}
     *
     * @param ${className?uncap_first}QueryVo 分页查询实体
     * @return Result<PageResult<${className}>> 返回分页数据
     */
    @Operation(summary = "分页查询${tableComment}")
    @PostMapping("/${className?uncap_first}Page")
    public Result<PageResult<${className}>> ${className?uncap_first}Page(@RequestBody ${className}QueryVo ${className?uncap_first}QueryVo) {
        return Result.success(${className?uncap_first}Service.${className?uncap_first}Page(${className?uncap_first}QueryVo));
    }

    /**
     * 新增${tableComment}
     *
     * @param ${className?uncap_first}AddVo 新增实体
     * @return Result<Boolean> 返回结果(true/false)
     */
    @Operation(summary = "新增${tableComment}")
    @PostMapping("/${className?uncap_first}Add")
    public Result<Boolean> ${className?uncap_first}Add(@RequestBody ${className}AddVo ${className?uncap_first}AddVo) {
        return Result.success(${className?uncap_first}Service.${className?uncap_first}Add(${className?uncap_first}AddVo));
    }

    /**
     * 根据主键ID删除${tableComment}
     *
     * @param id 主键id
     * @return Result<Boolean> 返回结果(true/false)
     */
    @Operation(summary = "根据主键ID删除${tableComment}")
    @DeleteMapping("${className?uncap_first}Delete")
    public Result<Boolean> ${className?uncap_first}Delete(@RequestParam Serializable id) {
        return Result.success(${className?uncap_first}Service.removeById(id));
    }

    /**
    * 根据主键ID批量删除${tableComment}
    *
    * @param ids 主键id集合
    * @return Result<Boolean> 返回结果(true/false)
    */
    @Operation(summary = "根据主键ID批量删除${tableComment}")
    @DeleteMapping("${className?uncap_first}ListDelete")
    public Result<Boolean> ${className?uncap_first}ListDelete(@RequestParam List<Serializable> ids) {
        return Result.success(${className?uncap_first}Service.removeByIds(ids));
        }

    /**
     * 根据主键ID修改${tableComment}
     *
     * @param ${className?uncap_first}UpdateVo 修改实体
     * @return Result<Boolean> 返回结果(true/false)
     */
    @Operation(summary = "根据主键ID修改${tableComment}")
    @PutMapping("${className?uncap_first}Update")
    public Result<Boolean> ${className?uncap_first}Update(@RequestBody ${className}UpdateVo ${className?uncap_first}UpdateVo) {
        return Result.success(${className?uncap_first}Service.${className?uncap_first}Update(${className?uncap_first}UpdateVo));
    }

    /**
     * 根据主键ID查询${tableComment}
     *
     * @param id 主键id
     * @return Result<${className}> 返回${tableComment}
     */
    @Operation(summary = "根据主键ID查询${tableComment}")
    @GetMapping("/getInfo")
    public Result<${className}> ${className?uncap_first}Update(@RequestParam Serializable id) {
        return Result.success(${className?uncap_first}Service.getById(id));
    }

}
