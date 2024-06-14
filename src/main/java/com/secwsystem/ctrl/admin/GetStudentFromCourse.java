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
        StuName.setText(student.getSName());
        StuSex.setText(student.getSSex());
        StuPhone.setText(student.getSPhoneNumber());
        StuIdcard.setText(student.getSIdcard());
        StuSchool.setText(student.getSSchool());
        StuClass.setText(student.getSClass());
        StuEmail.setText(student.getSEmail());
        StuEntertime.setText(student.getSEntertime());
        StuPassword.setText(student.getSPassword());
    }
}
