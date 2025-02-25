package com.linyi.generator.config;

import com.linyi.generator.config.SysProperties;
import com.linyi.generator.impl.GeneratorImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: linyi
 * @Date: 2025/2/25
 * @ClassName: ApplicationStartupRunner
 * @Version: 1.0
 * @Description: 代码生成器启动时运行的初始化任务
 */
@Slf4j
@Component
@Order(value = 1)
public class ApplicationStartupRunner implements ApplicationRunner {

    @Autowired
    private GeneratorImpl generator;

    @Autowired
    private SysProperties sysProperties;

    /**
     * 在应用程序启动完成后执行的初始化方法
     * 负责启动代码生成流程
     *
     * @param args 应用程序启动参数，提供对应用程序参数的访问
     * @throws Exception 如果代码生成过程中发生错误，则抛出异常
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("启动完成，开始代码生成...");

        // 使用系统属性中配置的生成包路径作为参数
        generator.generateBySchema(sysProperties.getGenerator().getGeneratorPackage());
    }
}
