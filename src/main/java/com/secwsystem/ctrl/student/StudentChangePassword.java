package com.secwsystem.ctrl.student;

import com.secwsystem.app.StudentApplication;
import com.secwsystem.dao.impl.StudentDAO;
import com.secwsystem.dao.pojo.StudentPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentChangePassword {

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage;

    @FXML
    private TextField a_new_password;

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
            if(a_new_password.getText().trim().isEmpty()){
                throw new StudentException.PasswordNullException();
            }
            if(new StudentApplication().showMessage("提示","确定修改密码？", Alert.AlertType.CONFIRMATION,1)){
                //将新密码上传到数据库
                StudentLogin controller = (StudentLogin) StudentContext.controllers.get(StudentLogin.class.getSimpleName());
                StudentDAO studentDAO = new StudentDAO();
                StudentPrivate studentPrivate = studentDAO.getPrivate(controller.getsid());
                studentPrivate.setSPassword(a_new_password.getText());
                if(studentDAO.updatePrivate(studentPrivate)){
                    //生成提示界面，关闭本界面
                    new StudentApplication().showMessage("提示","密码修改成功", Alert.AlertType.INFORMATION,0);
                    stage.close();
                }else{
                    throw new com.secwsystem.ctrl.student.StudentException.ChangePasswordException();
                }
            }
        } catch (StudentException.PasswordNullException e) {
            new StudentApplication().showMessage("密码修改失败","新密码不能为空", Alert.AlertType.ERROR,0);
        } catch (StudentException.ChangePasswordException e) {
            new StudentApplication().showMessage("密码修改失败","密码修改失败", Alert.AlertType.ERROR,0);
        }
    }

}
