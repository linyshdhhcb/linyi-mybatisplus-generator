# Linyi MybatisPlus 代码生成器

## 项目介绍

Linyi MybatisPlus Generator 是一个基于 Spring Boot 和 MyBatis-Plus 的代码生成工具，用于快速生成基于 MyBatis-Plus 的项目代码，包括实体类、Mapper、Service、Controller 以及相关的 VO 对象和 XML 文件，极大地提高开发效率。

## 功能特点

- 自动生成实体类（Entity）、Mapper 接口、Mapper XML、Service 接口、Service 实现类、Controller
- 额外生成 AddVo、UpdateVo、QueryVo 等常用的数据传输对象
- 支持自定义模板，灵活配置生成规则
- 基于 Freemarker 模板引擎，生成代码风格统一
- 支持数据库表前缀自动去除
- 适配 MySQL 数据库

## 环境要求

- JDK 8+
- Maven 3.6+
- MySQL 5.7+
- Spring Boot 2.3.3.RELEASE

## 快速开始

### 1. 克隆项目

```bash
git clone https://github.com/yourusername/linyi-mybatisplus-generator.git
cd linyi-mybatisplus-generator
```

### 2. 配置数据库连接

修改 `src/main/resources/application.yml` 文件中的数据库连接信息：

```yaml
spring:
  datasource:
    druid:
      driverClassName: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/your_database?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
      username: your_username
      password: your_password
```

### 3. 配置生成器属性

修改 `src/main/resources/system.properties` 文件，配置代码生成的包名和其他属性：

```properties
# 生成器包名（生成的文件将放在此包下）
sys.generator.generatorPackage=your_project_name

# 作者姓名（会显示在生成的代码注释中）
sys.generator.author=your_name

# 基础包名（生成的代码的包前缀）
sys.generator.basePackage=com.yourcompany.project

# 各类文件的子包名配置
sys.generator.entityPackage=entity
sys.generator.mapperPackage=mapper
sys.generator.servicePackage=service
sys.generator.serviceImplPackage=service.impl
sys.generator.controllerPackage=controller

# mapper.xml文件的包名
sys.generator.mapperXmlPackage=mapper
```

### 4. 启动应用

运行 `GeneratorApplication` 主类，启动生成器应用：

```bash
mvn spring-boot:run
```

或者在 IDE 中直接运行 `com.linyi.GeneratorApplication` 类。

### 5. 访问生成器界面

应用启动后，访问 `http://localhost:8888` 进入代码生成器操作界面。

## 代码生成使用方法

### 通过界面生成代码

1. 在浏览器中打开 `http://localhost:8888`
2. 选择需要生成代码的数据库表
3. 配置生成选项（如是否去除表前缀等）
4. 点击"生成代码"按钮
5. 生成的代码将保存到配置的输出目录中

### 生成代码目录结构

生成的代码将按照以下结构组织：

```
com.yourcompany.project
├── controller
│   └── XxxController.java
├── entity
│   ├── Xxx.java
│   ├── XxxAddVo.java
│   ├── XxxUpdateVo.java
│   └── XxxQueryVo.java
├── mapper
│   └── XxxMapper.java
└── service
    ├── XxxService.java
    └── impl
        └── XxxServiceImpl.java
```

resources 目录：

```
resources
└── mapper
    └── xxx
        └── XxxMapper.xml
```

## 配置详解

### application.yml

主要配置数据库连接和服务器端口：

```yaml
server:
  port: 8888 # 应用端口号
spring:
  datasource:
    druid:
      driverClassName: com.mysql.cj.jdbc.Driver # 数据库驱动
      url: jdbc:mysql://localhost:3306/your_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai # 数据库URL
      username: root # 数据库用户名
      password: password # 数据库密码
```

### system.properties

配置代码生成的相关属性：

```properties
# 生成器包名
sys.generator.generatorPackage=your_project_name

# 作者
sys.generator.author=your_name

# 基础包名
sys.generator.basePackage=com.yourcompany.project

# 各类文件包名配置
sys.generator.entityPackage=entity
sys.generator.mapperPackage=mapper
sys.generator.servicePackage=service
sys.generator.serviceImplPackage=service.impl
sys.generator.controllerPackage=controller

# mapper.xml包名
sys.generator.mapperXmlPackage=mapper
```

## 自定义模板

模板文件位于 `src/main/resources/templates` 目录下，可以根据需求修改以下模板文件：

- `entity.ftl` - 实体类模板
- `entityAddVo.ftl` - 添加 VO 类模板
- `entityUpdateVo.ftl` - 更新 VO 类模板
- `entityQueryVo.ftl` - 查询 VO 类模板
- `mapper.ftl` - Mapper 接口模板
- `service.ftl` - Service 接口模板
- `serviceImpl.ftl` - Service 实现类模板
- `controller.ftl` - Controller 类模板
- `mapperXml.ftl` - Mapper XML 文件模板

## 开发扩展

### 添加新的模板

1. 在 `src/main/resources/templates` 目录下创建新的模板文件
2. 在 `GeneratorConstant` 类中添加相应的常量
3. 在 `GeneratorHelper` 类中添加对应的生成方法

### 支持新的数据库类型

1. 修改 `GeneratorConstant.DATABASE_TYPE` 常量
2. 根据需要调整数据类型映射逻辑

## 常见问题

### Q: 生成的代码中包含了表前缀，如何去除？

A: 在生成代码时，可以在界面上勾选"去除表前缀"选项，或者修改代码中的相关配置。

### Q: 如何自定义生成的代码风格？

A: 可以修改 `src/main/resources/templates` 目录下的模板文件。

### Q: 如何在生成的代码中添加额外的功能？

A: 可以修改对应的模板文件，或者在 `GeneratorHelper` 类中添加相应的逻辑。

## 贡献指南

欢迎贡献代码，提交问题和改进建议。贡献前请先 Fork 项目，然后提交 Pull Request。

## 许可证

本项目采用 [MIT 许可证](LICENSE)。
