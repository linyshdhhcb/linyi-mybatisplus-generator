package ${basePackage}.${entityPackage}.vo.${className?uncap_first};


import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.linyi.pig.common.model.*;

/**
* @Author: linyi
* @Date: ${date}
* @ClassName: ${className}QueryVo
* @Version: 1.0
* @Description: ${tableComment}查询实体
*/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "${tableComment}查询实体")
public class ${className}QueryVo extends PageResponse implements Serializable {

}
