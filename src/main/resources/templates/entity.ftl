package ${basePackage}.${entityPackage};

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
<#if hasDate = true>
import java.util.Date;
</#if>
<#if hasBigDecimal = true>
import java.math.BigDecimal;
</#if>

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.NoArgsConstructor;

/**
* @Author: linyi
* @Date: ${date}
* @ClassName: ${className}
* @Version: 1.0
* @Description: ${tableComment}
*/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("${tableName}")
@Schema(name = "${tableComment}")
public class ${className} implements Serializable {


    private static final long serialVersionUID = 1L;

    <#if columns??>
        <#list columns as column>
    /**
     * ${column.remark}
     */
    <#if column.isKey = true>
    @TableId(value = "${column.name}", type = IdType.AUTO)
    <#else>
    @TableField("${column.name}")
    </#if>
    <#if (column.type = 'varchar' || column.type = 'text' || column.type = 'uniqueidentifier'
        || column.type = 'varchar2' || column.type = 'nvarchar' || column.type = 'VARCHAR2'
        || column.type = 'VARCHAR'|| column.type = 'CLOB' || column.type = 'char' || column.type = 'mediumtext')>
    @Schema(name = "${column.field?uncap_first}",description = "${column.remark}",type = "${column.type}")
    private String ${column.field?uncap_first};

    </#if>
    <#if column.type = 'timestamp' || column.type = 'date' || column.type = 'datetime'||column.type = 'TIMESTAMP' || column.type = 'DATE' || column.type = 'DATETIME'>
    @Schema(name = "${column.field?uncap_first}",description = "${column.remark}",type = "${column.type}")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date ${column.field?uncap_first};

    </#if>
    <#if column.type = 'int' || column.type = 'smallint'>
    @Schema(name = "${column.field?uncap_first}",description = "${column.remark}",type = "${column.type}")
    private Integer ${column.field?uncap_first};

    </#if>
    <#if column.type = 'bigint'>
    @Schema(name = "${column.field?uncap_first}",description = "${column.remark}",type = "${column.type}")
    private Long ${column.field?uncap_first};

    </#if>
<#--            新增Float类型-->
    <#if column.type = 'float'>
    @Schema(name = "${column.field?uncap_first}",description = "${column.remark}",type = "${column.type}")
    private Float ${column.field?uncap_first};

    </#if>
    <#if column.type = 'double'>
    @Schema(name = "${column.field?uncap_first}",description = "${column.remark}",type = "${column.type}")
    private Double ${column.field?uncap_first};

    </#if>
    <#if column.type = 'tinyint'>
    @Schema(name = "${column.field?uncap_first}",description = "${column.remark}",type = "${column.type}")
    private Byte ${column.field?uncap_first};

    </#if>
    <#if column.type = 'decimal' || column.type = 'numeric'>
    @Schema(name = "${column.field?uncap_first}",description = "${column.remark}",type = "${column.type}")
    private BigDecimal ${column.field?uncap_first};

    </#if>
    <#if column.type = 'bit'>
     @Schema(name = "${column.field?uncap_first}",description = "${column.remark}",type = "${column.type}")
     private boolean ${column.field?uncap_first};
    </#if>
        </#list>
    </#if>
}
