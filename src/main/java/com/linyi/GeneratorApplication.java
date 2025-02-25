package com.linyi;

import com.linyi.generator.config.SysProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Author: linyi
 * @Date: 2025/2/25
 * @ClassName: GeneratorApplication
 * @Version: 1.0
 * @Description:
 */
@SpringBootApplication
@EnableConfigurationProperties({SysProperties.class})// 开启配置文件注入
public class GeneratorApplication {
}
