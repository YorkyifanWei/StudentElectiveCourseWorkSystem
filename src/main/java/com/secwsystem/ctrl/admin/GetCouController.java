package com.secwsystem.ctrl.admin;

import com.secwsystem.app.Admin;
import com.secwsystem.dao.pojo.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GetCouController implements Initializable {

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
        MainController controller = (MainController) Context.controllers.get(MainController.class.getSimpleName());
        Course course = controller.getCourse();
        CouId.setText(course.getCid());
        CouName.setText(course.getC_name());
        CouType.setText(course.getC_type());
        CouInfo.setText(course.getC_info());
        CouTime.setText(course.getC_time());
        CouSchool.setText(course.getC_school());
        CouPeriod.setText(course.getC_period());
        CouInit.setText(course.getC_init());
        CouEnd.setText(course.getC_end());
        CouLocation.setText(course.getC_location());
        CouCurrent.setText(course.getC_current()+"/"+course.getC_capacity());
    }

    @FXML
    void GetStusEvent() throws IOException {
        new Admin().GetStusFromCou();
    }

    @FXML
    void GetTeasEvent() throws IOException {
        new Admin().GetTeasFromCou();
    }
}
