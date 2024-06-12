package com.secwsystem.ctrl.admin;

import com.secwsystem.app.Admin;
import com.secwsystem.dao.impl.StudentDAO;
import com.secwsystem.dao.pojo.StudentPrivate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyStuController implements Initializable{
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage;

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
    private Button btn_set;

    @FXML
    private Button btn_cancel;

    @FXML
    private TextField StuEntertime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainController controller = (MainController) Context.controllers.get(MainController.class.getSimpleName());
        StudentPrivate student = controller.getStudent();
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

    @FXML
    void CancelEvent() {
        stage.close();
    }
    @FXML
    void SetEvent() {
        try{
            MainController controller = (MainController) Context.controllers.get(MainController.class.getSimpleName());
            StudentPrivate student = controller.getStudent();
            StudentDAO studentDAO = new StudentDAO();
            if (TextFieldIsNull()) {
                throw new Exception.TextFieldNullException();
            }
            student.setSid(StuId.getText().trim());
            student.setS_name(StuName.getText().trim());
            student.setS_sex(StuSex.getText().trim());
            student.setS_phone(StuPhone.getText().trim());
            student.setS_idcard(StuIdcard.getText().trim());
            student.setS_school(StuSchool.getText().trim());
            student.setS_class(StuClass.getText().trim());
            student.setS_email(StuEmail.getText().trim());
            student.setS_entertime(StuEntertime.getText().trim());
            if (studentDAO.updatePrivate(student)) {
                controller.ModifyStuToTable(student);
                new Admin().showMessage("修改成功", "修改成功", Alert.AlertType.INFORMATION, 0);
                stage.close();
            } else {
                throw new Exception.ModifyException();
            }
        } catch (Exception.TextFieldNullException e) {
            new Admin().showMessage("修改失败", "修改不能为空", Alert.AlertType.ERROR, 0);
        } catch (Exception.ModifyException e) {
            new Admin().showMessage("修改异常", "修改异常,请重新修改", Alert.AlertType.ERROR, 0);
        }
    }
    boolean TextFieldIsNull() {
        return StuId.getText().trim().isEmpty() || StuPassword.getText().trim().isEmpty() ||
                StuName.getText().trim().isEmpty() || StuSex.getText().trim().isEmpty() ||
                StuPhone.getText().trim().isEmpty() || StuIdcard.getText().trim().isEmpty() ||
                StuSchool.getText().trim().isEmpty() || StuClass.getText().trim().isEmpty() ||
                StuEmail.getText().trim().isEmpty();
    }
}


