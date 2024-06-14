package com.secwsystem.ctrl.admin;

import com.secwsystem.app.AdminApplication;
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

public class ModifyTeacher implements Initializable{


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
        AdminMainController controller = (AdminMainController) AdminContext.controllers.get(AdminMainController.class.getSimpleName());
        TeacherPrivate teacher = controller.getTeacher();
        TeaId.setText(teacher.getTid());
        TeaName.setText(teacher.getTName());
        TeaSex.setText(teacher.getTSex());
        TeaIdcard.setText(teacher.getTIdcard());
        TeaPhone.setText(teacher.getTPhoneNumber());
        TeaSchool.setText(teacher.getTSchool());
        TeaTitle.setText(teacher.getTTitle());
        TeaEmail.setText(teacher.getTEmail());
        TeaAddress.setText(teacher.getTAddress());
    }
    @FXML
    void CancelEvent() {
        stage.close();
    }

    @FXML
    void SetEvent() {
        try{
            AdminMainController controller = (AdminMainController) AdminContext.controllers.get(AdminMainController.class.getSimpleName());
            TeacherPrivate teacher = controller.getTeacher();
            TeacherDAO teacherDAO = new TeacherDAO();
            if(TextFieldIsNull()){
                throw new AdminException.TextFieldNullException();
            }
            teacher.setTid(TeaId.getText().trim());
            teacher.setTName(TeaName.getText().trim());
            teacher.setTSex(TeaSex.getText().trim());
            teacher.setTIdcard(TeaIdcard.getText().trim());
            teacher.setTPhoneNumber(TeaPhone.getText().trim());
            teacher.setTSchool(TeaSchool.getText().trim());
            teacher.setTTitle(TeaTitle.getText().trim());
            teacher.setTEmail(TeaEmail.getText().trim());
            teacher.setTAddress(TeaAddress.getText().trim());
            if(teacherDAO.updatePrivate(teacher)){
                controller.ModifyTeaToTable(teacher);
                new AdminApplication().showMessage("修改成功", "修改成功", Alert.AlertType.INFORMATION, 0);
                stage.close();
            }else{
                throw new AdminException.ModifyException();
            }
        } catch (AdminException.TextFieldNullException e) {
            new AdminApplication().showMessage("修改失败", "请将信息填写完整", Alert.AlertType.ERROR, 0);
        } catch (AdminException.ModifyException e) {
            new AdminApplication().showMessage("修改异常", "修改失败,请重新修改", Alert.AlertType.ERROR, 0);
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
