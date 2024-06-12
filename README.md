# StudentElectiveCourseWorkSystem

## 项目概览

本项目是一个采用JavaFX技术构建的桌面应用，集成了Maven作为项目管理工具，旨在通过模拟真实的学校选课流程，提供一个全面的学生、教师及管理员三方课程操作平台。系统后端依托于MySQL数据库，确保数据处理的高效与安全。

## 核心贡献者

- **后端开发者**：[邱健珏，YorkyifanWei]
- **前端开发者**：[韩硕，吕祖灿]
- **文档撰写**：[邱健珏，YorkyifanWei]

## 技术架构

- **编程语言**：Java
- **界面框架**：JavaFX
- **构建与依赖管理**：Maven
- **推荐开发环境**：IntelliJ IDEA, Eclipse（需搭配e(fx)clipse插件）

## 系统需求

- **JDK版本**：建议使用Oracle OpenJDK 21或更高版本，需兼容JavaFX。
- **Maven版本**：不低于4.0.0。

## 快速上手指南

### 克隆项目

``` bash
git clone https://github.com/YorkyifanWei/StudentElectiveCourseSystem.git
cd your-project-name
```

### IDE集成

- **IntelliJ IDEA**：通过“Open”菜单导入`pom.xml`启动项目。
- **Eclipse**：安装`e(fx)clipse`插件后，通过“File” > “Import” > “Maven” > “Existing Maven Projects”导入。

### 运行应用

在IDE中直接运行`src/main/java/com/scewsystem/app/`目录下对应的启动类，以启动学生、教师及管理员界面。

或使用命令行：

``` bash
mvn clean compile javafx:run
```

## 目录结构概览

- `src/main/java`：存放核心Java源代码。
- `src/main/resources`：包含FXML布局文件、`application.properties`等资源配置。
- `src/test/java`：测试代码区域。
- `pom.xml`：Maven配置文件，管理依赖与构建流程。
- `src/main/resources/application.properties`：数据库连接配置详情。

## 注意事项

- 确保JavaFX库已被系统环境正确识别，或在IDE设置中配置好JavaFX路径。
- 查阅`pom.xml`了解项目所依赖的JavaFX确切版本信息。
