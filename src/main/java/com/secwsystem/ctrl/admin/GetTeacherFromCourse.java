package com.secwsystem.ctrl.admin;

import com.secwsystem.dao.pojo.TeacherPrivate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GetTeacherFromCourse implements Initializable {

    @FXML
    private TextField TeaId;

    @FXML
    private TextField TeaPassword;

    @FXML
    private TextField TeaName;

    @FXML
    private TextField TeaSex;

    @FXML
    private TextField TeaIdcard;

    @FXML
    private TextField TeaPhone;

    @FXML
    private TextField TeaSchool;

    @FXML
    private TextField TeaTitle;

    @FXML
    private TextField TeaEmail;

    @FXML
    private TextField TeaAddress;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GetTeachersFromCourse controller = (GetTeachersFromCourse) AdminContext.controllers.get(GetTeachersFromCourse.class.getSimpleName());
        TeacherPrivate teacher = controller.getTeaFromCou();
        TeaId.setText(teacher.getTid());
        TeaName.setText(teacher.getT_name());
        TeaSex.setText(teacher.getT_sex());
        TeaIdcard.setText(teacher.getT_idcard());
        TeaPhone.setText(teacher.getT_phone());
        TeaSchool.setText(teacher.getT_school());
        TeaTitle.setText(teacher.getT_title());
        TeaEmail.setText(teacher.getT_email());
        TeaAddress.setText(teacher.getT_address());
        TeaPassword.setText(teacher.getT_password());
    }
}