package com.linyi.generator.config;


import com.linyi.generator.entity.GeneratorConfig;
import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: linyi
 * @Date: 2025/2/24
 * @ClassName: SysProperties
 * @Version: 1.0
 * @Description: 读取系统配置文件
 */
@SpringBootConfiguration
@PropertySource(value = {"classpath:system.properties"})// 注意,只能从.properts文件取值,yml不行
@ConfigurationProperties(prefix = "sys") //从全局配置文件取值
@Data
public class SysProperties {

    private Integer a;
    private GeneratorConfig generator;
}
