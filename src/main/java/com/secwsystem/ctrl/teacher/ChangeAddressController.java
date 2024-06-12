package com.secwsystem.ctrl.teacher;

import com.secwsystem.app.Teacher;
import com.secwsystem.dao.impl.TeacherDAO;
import com.secwsystem.dao.pojo.TeacherPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangeAddressController {

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage;

    @FXML
    private TextField a_new_address;

    @FXML
    private Button btn_set;

    @FXML
    private Button btn_cancel;

    @FXML
    void CancelEvent() {
        stage.close();
    }

    @FXML
    void SetEvent() {
        try{
            if(a_new_address.getText().trim().isEmpty()){
                throw new Exception.PasswordNullException();
            }
            if(new Teacher().showMessage("提示","确定修改办公室地址？", Alert.AlertType.CONFIRMATION,1)){
                //将新密码上传到数据库
                TeacherLoginController controller = (TeacherLoginController) Context.controllers.get(TeacherLoginController.class.getSimpleName());
                TeacherDAO teacherDAO = new TeacherDAO();
                TeacherPrivate teacherPrivate = teacherDAO.getPrivate(controller.gettid());
                teacherPrivate.setT_address(a_new_address.getText());
                if(teacherDAO.updatePrivate(teacherPrivate)){
                    //生成提示界面，关闭本界面
                    new Teacher().showMessage("提示","密码修改成功", Alert.AlertType.INFORMATION,0);
                    stage.close();
                }else{
                    throw new com.secwsystem.ctrl.teacher.Exception.ChangePasswordException();
                }
            }
        } catch (Exception.PasswordNullException e) {
            new Teacher().showMessage("地址修改失败","新地址不能为空", Alert.AlertType.ERROR,0);
        } catch (Exception.ChangePasswordException e) {
            new Teacher().showMessage("地址修改失败","地址修改失败", Alert.AlertType.ERROR,0);
        }
    }

}
