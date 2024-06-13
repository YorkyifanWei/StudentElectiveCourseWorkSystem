package com.secwsystem.ctrl.admin;

import com.secwsystem.dao.pojo.StudentPrivate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GetStudentFromCourse implements Initializable {

    @FXML
    private TextField StuId;

    @FXML
    private TextField StuPassword;

    @FXML
    private TextField StuName;

    @FXML
    private TextField StuSex;

    @FXML
    private TextField StuPhone;

    @FXML
    private TextField StuIdcard;

    @FXML
    private TextField StuSchool;

    @FXML
    private TextField StuClass;

    @FXML
    private TextField StuEmail;

    @FXML
    private TextField StuEntertime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GetStudentsFromCourse controller = (GetStudentsFromCourse) AdminContext.controllers.get(GetStudentsFromCourse.class.getSimpleName());
        StudentPrivate student = controller.getStuFromCou();
        StuId.setText(student.getSid());
        StuName.setText(student.getS_name());
        StuSex.setText(student.getS_sex());
        StuPhone.setText(student.getS_phone());
        StuIdcard.setText(student.getS_idcard());
        StuSchool.setText(student.getS_school());
        StuClass.setText(student.getS_class());
        StuEmail.setText(student.getS_email());
        StuEntertime.setText(student.getS_entertime());
        StuPassword.setText(student.getS_password());
    }
}
