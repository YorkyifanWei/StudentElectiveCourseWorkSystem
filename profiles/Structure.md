# 项目结构示例文档

## 1. 项目概述

本项目为一个典型的Web应用程序，采用前后端分离架构，旨在实现一个在线教育平台，覆盖学生、教师和管理员三大用户群体的功能需求。项目采用Maven进行依赖管理，前端技术栈灵活选择，后端使用Spring Boot框架，数据库选用MySQL。

## 2. 项目目录结构

### 2.1 根目录
- .mvn
- database
  - StudentElectiveCourseWorkSystem.sql
- profiles
  - DBDesign.md
  - demand.md
  - DemandDesign.md
  - Structure.md

- src
  - main
    - java
      - com.secwsystem
        - app
          - Admin.java
          - Student.java
          - Teacher.java

        - ctrl
          - admin
          - student
          - teacher

        - dao
          - impl
            - AdminDAO.java
            - CourseDAO.java
            - LoginImpl.java
            - StudentDAO.java
            - TeacherDAO.java

          - pojo
            - AdminPrivate.java
            - AdminPublic.java
            - Course.java
            - DBConnection.java
            - IdName.java
            - StudentPrivate.java
            - StudentPublic.java
            - TeacherPrivate.java
            - TeacherPublic.java

          - DAOForAcount.java
          - DAOForCourse.java
          - Login.java

      - module-info.java

    - resourses
      - com.secwsystem
        - admin
          - add_Cou_admin.fxml
          - add_Stu_admin.fxml
          - add_Tea_admin.fxml
          - change_password.fxml
          - login_admin.fxml
          - main_admin.fxml
          - modify_Cou_admin.fxml
          - modify_Stu_admin.fxml

        - student
          - change_email.fxml
          - change_password.fxml
          - change_phonenumber.fxml
          - login_manager.fxml
          - main_manager.fxml

        - teacher
          - add_course.fxml
          - change_address.fxml
          - change_email.fxml
          - change_password.fxml
          - change_phonenumber.fxml
          - login_manager.fxml
          - main_

      - application.properties

  - test

- target
- .gitignore
- mvnw
- mvnw.cmd
- pom.xml
- README.md

## 3. 技术栈与工具

- **后端**：MySQL
- **前端**：JavaFX
- **构建工具**：Maven
- **版本控制**：Git
- **数据库管理**：DataDrip / Navicat

## 4. 开发流程

1. **环境搭建**：确保安装Java、MySQL、Maven、IDE（如IntelliJ IDEA）等环境。
2. **后端开发**：在`src/`目录下使用IDE启动Spring Boot项目，编写业务逻辑与数据库交互。
3. **前端开发**：在`frontend/`目录下运行前端项目，实现用户界面与交互逻辑。
4. **集成测试**：前后端联调，确保API通信无误。
5. **部署准备**：编写Dockerfile（如有必要）以容器化部署，或直接部署到服务器。
6. **文档编写**：维护`documentation/`下的文档，确保项目可维护性和扩展性。

## 5. 注意事项

- 保持代码风格一致，遵循团队编码规范。
- 定期同步分支，解决冲突，确保代码库健康。
- 注重代码复用，避免冗余。
- 定期备份数据库，尤其是在进行重大更改之前。
- 强烈建议使用单元测试和集成测试保障代码质量。
