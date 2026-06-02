# code-generator

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Language](https://img.shields.io/badge/language-Java-orange.svg)](https://github.com/udbwcso/code-generator)

一个基于 Java 开发的高效代码生成器工具，旨在告别枯燥的重复劳动，一键生成项目基础架构代码（如 Controller、Service、Mapper、Entity、DTO 等），让你可以把更多精力投入到核心业务逻辑中。

目前项目采用 Maven 进行模块化管理，核心生成逻辑与模板配置均封装在 `module-generator` 模块中。

---

## 🚀 特性

* **多模板支持**：支持主流的模板引擎（如 Velocity、Freemarker、Thymeleaf），可轻松自定义专属的代码生成样式。
* **配置灵活**：支持一键配置数据库连接、目标包名、作者信息、是否覆盖已有文件以及实体类的前后缀。
* **多模块适配**：基于 Maven 模块化设计，核心逻辑独立在 `module-generator` 中，极易嵌入到其他微服务或单体工程中。
* **框架兼容**：默认支持生成适配 Spring Boot、MyBatis / MyBatis-Plus、Spring Data JPA 等主流技术栈的基底代码。

---

## 📁 项目结构

```text
code-generator
├── module-generator      # 代码生成器核心核心模块（包含生成逻辑与模板文件）
│   ├── src
│   │   ├── main
│   │   │   ├── java      # 核心生成器类、配置类
│   │   │   └── resources # 存放 .vm 或 .ftl 模板文件
│   └── pom.xml
├── pom.xml               # 父工程 Maven 依赖配置
└── README.md             # 项目说明文档
