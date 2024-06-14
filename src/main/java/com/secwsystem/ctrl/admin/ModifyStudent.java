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

public class ModifyStudent implements Initializable{
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
        AdminMainController controller = (AdminMainController) AdminContext.controllers.get(AdminMainController.class.getSimpleName());
        StudentPrivate student = controller.getStudent();
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

    @FXML
    void CancelEvent() {
        stage.close();
    }
    @FXML
    void SetEvent() {
        try{
            AdminMainController controller = (AdminMainController) AdminContext.controllers.get(AdminMainController.class.getSimpleName());
            StudentPrivate student = controller.getStudent();
            StudentDAO studentDAO = new StudentDAO();
            if (TextFieldIsNull()) {
                throw new AdminException.TextFieldNullException();
            }
            student.setSid(StuId.getText().trim());
            student.setSName(StuName.getText().trim());
            student.setSSex(StuSex.getText().trim());
            student.setSPhoneNumber(StuPhone.getText().trim());
            student.setSIdcard(StuIdcard.getText().trim());
            student.setSSchool(StuSchool.getText().trim());
            student.setSClass(StuClass.getText().trim());
            student.setSEmail(StuEmail.getText().trim());
            student.setSEntertime(StuEntertime.getText().trim());
            if (studentDAO.updatePrivate(student)) {
                controller.ModifyStuToTable(student);
                new Admin().showMessage("修改成功", "修改成功", Alert.AlertType.INFORMATION, 0);
                stage.close();
            } else {
                throw new AdminException.ModifyException();
            }
        } catch (AdminException.TextFieldNullException e) {
            new Admin().showMessage("修改失败", "修改不能为空", Alert.AlertType.ERROR, 0);
        } catch (AdminException.ModifyException e) {
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


