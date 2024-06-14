package com.secwsystem.ctrl.admin;

import com.secwsystem.app.AdminApplication;
import com.secwsystem.dao.pojo.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GetCourse implements Initializable {

    @FXML
    private TextField CouId;

    @FXML
    private TextField CouName;

    @FXML
    private TextField CouType;

    @FXML
    private TextArea CouInfo;

    @FXML
    private TextField CouTime;

    @FXML
    private TextField CouSchool;

    @FXML
    private TextField CouPeriod;

    @FXML
    private TextField CouCurrent;

    @FXML
    private Button btn_getTeas;

    @FXML
    private Button btn_getStus;

    @FXML
    private TextField CouInit;

    @FXML
    private TextField CouEnd;

    @FXML
    private TextField CouLocation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AdminMainController controller = (AdminMainController) AdminContext.controllers.get(AdminMainController.class.getSimpleName());
        Course course = controller.getCourse();
        CouId.setText(course.getCid());
        CouName.setText(course.getCName());
        CouType.setText(course.getCType());
        CouInfo.setText(course.getCInfo());
        CouTime.setText(course.getCTime());
        CouSchool.setText(course.getCSchool());
        CouPeriod.setText(course.getCPeriod());
        CouInit.setText(course.getCInti());
        CouEnd.setText(course.getCEnd());
        CouLocation.setText(course.getCLocation());
        CouCurrent.setText(course.getCCurrent()+"/"+course.getCCapacity());
    }

    @FXML
    void GetStusEvent() throws IOException {
        new AdminApplication().getStudentsFromCourse();
    }

    @FXML
    void GetTeasEvent() throws IOException {
        new AdminApplication().getTeachersFromCourse();
    }
}
