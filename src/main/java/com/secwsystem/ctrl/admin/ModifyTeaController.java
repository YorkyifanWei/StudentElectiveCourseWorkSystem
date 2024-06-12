package com.secwsystem.ctrl.admin;

import com.secwsystem.app.Admin;
import com.secwsystem.dao.impl.TeacherDAO;
import com.secwsystem.dao.pojo.TeacherPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyTeaController implements Initializable{


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage;

    @FXML
    private Button btn_set;

    @FXML
    private Button btn_cancel;

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
        MainController controller = (MainController) Context.controllers.get(MainController.class.getSimpleName());
        TeacherPrivate teacher = controller.getTeacher();
        TeaId.setText(teacher.getTid());
        TeaName.setText(teacher.getT_name());
        TeaSex.setText(teacher.getT_sex());
        TeaIdcard.setText(teacher.getT_idcard());
        TeaPhone.setText(teacher.getT_phone());
        TeaSchool.setText(teacher.getT_school());
        TeaTitle.setText(teacher.getT_title());
        TeaEmail.setText(teacher.getT_email());
        TeaAddress.setText(teacher.getT_address());
    }
    @FXML
    void CancelEvent() {
        stage.close();
    }

    @FXML
    void SetEvent() {
        try{
            MainController controller = (MainController) Context.controllers.get(MainController.class.getSimpleName());
            TeacherPrivate teacher = controller.getTeacher();
            TeacherDAO teacherDAO = new TeacherDAO();
            if(TextFieldIsNull()){
                throw new Exception.TextFieldNullException();
            }
            teacher.setTid(TeaId.getText().trim());
            teacher.setT_name(TeaName.getText().trim());
            teacher.setT_sex(TeaSex.getText().trim());
            teacher.setT_idcard(TeaIdcard.getText().trim());
            teacher.setT_phone(TeaPhone.getText().trim());
            teacher.setT_school(TeaSchool.getText().trim());
            teacher.setT_title(TeaTitle.getText().trim());
            teacher.setT_email(TeaEmail.getText().trim());
            teacher.setT_address(TeaAddress.getText().trim());
            if(teacherDAO.updatePrivate(teacher)){
                controller.ModifyTeaToTable(teacher);
                new Admin().showMessage("修改成功", "修改成功", Alert.AlertType.INFORMATION, 0);
                stage.close();
            }else{
                throw new Exception.ModifyException();
            }
        } catch (Exception.TextFieldNullException e) {
            new Admin().showMessage("修改失败", "请将信息填写完整", Alert.AlertType.ERROR, 0);
        } catch (Exception.ModifyException e) {
            new Admin().showMessage("修改异常", "修改失败,请重新修改", Alert.AlertType.ERROR, 0);
        }
    }

    boolean TextFieldIsNull() {
        return TeaId.getText().trim().isEmpty() || TeaName.getText().trim().isEmpty()
                || TeaSex.getText().trim().isEmpty() || TeaIdcard.getText().trim().isEmpty()
                || TeaPhone.getText().trim().isEmpty() || TeaSchool.getText().trim().isEmpty()
                || TeaTitle.getText().trim().isEmpty() || TeaEmail.getText().trim().isEmpty()
                || TeaAddress.getText().trim().isEmpty();
    }

}
