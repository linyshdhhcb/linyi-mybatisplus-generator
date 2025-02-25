package ${basePackage}.${entityPackage}.vo.${className?uncap_first};

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @Author: linyi
* @Date: ${date}
* @ClassName: ${className}AddVo
* @Version: 1.0
* @Description: ${tableComment}新增实体
*/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "${tableComment}新增实体")
public class ${className}AddVo implements Serializable {

}
