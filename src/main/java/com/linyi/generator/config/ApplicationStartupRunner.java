package com.linyi.generator.config;

import com.linyi.generator.config.SysProperties;
import com.linyi.generator.entity.Table;
import com.linyi.generator.impl.GeneratorImpl;
import com.linyi.generator.mapper.GeneratorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    @Autowired
    private GeneratorMapper generatorMapper;

    /**
     * 负责启动代码生成流程
     *
     * @param args 应用程序启动参数，提供对应用程序参数的访问
     * @throws Exception 如果代码生成过程中发生错误，则抛出异常
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("请选择代码生成方式：1.生成全部 2.生成指定表");
        //java输入操作
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                // 生成全部表
                log.info("启动完成，开始生成全部表的代码...");
                generator.generateBySchema(sysProperties.getGenerator().getGeneratorPackage());
                break;
            case 2:
                // 遍历数据库中的所有表
                List<Table> tables = generatorMapper.getAllTable(sysProperties.getGenerator().getGeneratorPackage());
                tables.forEach(e->{
                    System.out.println(e.getName()+" --->"+e.getRemark());
                });
                System.out.print("请输入要生成的表名（多个表名用逗号分隔）：");

                String table = new Scanner(System.in).next();
                // 将输入的表名分割成列表
                List<String> list = Arrays.asList(table.split(","));
                    generator.generateBySchemaTable(sysProperties.getGenerator().getGeneratorPackage(),list);

                break;
            default:
                log.error("输入错误，请重新输入！");
                break;
        }
    }

}
