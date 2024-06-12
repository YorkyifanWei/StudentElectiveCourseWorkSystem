module StudentElectiveCourseWorkSystem {
    requires java.sql;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.xerial.sqlitejdbc;
    requires java.logging;
//    // 添加Jupiter依赖
//    requires org.junit.jupiter.api;

    opens com.secwsystem.app to javafx.graphics, javafx.fxml;
    opens com.secwsystem.ctrl.admin to javafx.graphics, javafx.fxml;
    opens com.secwsystem.ctrl.teacher to javafx.graphics, javafx.fxml;
    opens com.secwsystem.ctrl.student to javafx.graphics, javafx.fxml;
    opens com.secwsystem.dao.pojo to javafx.graphics, javafx.fxml;
    opens com.secwsystem.dao.impl to javafx.graphics, javafx.fxml;
    exports com.secwsystem.app;
    exports com.secwsystem.ctrl.admin;
    exports com.secwsystem.ctrl.teacher;
    exports com.secwsystem.ctrl.student;
    exports com.secwsystem.dao.pojo;
    exports com.secwsystem.dao.impl;
}