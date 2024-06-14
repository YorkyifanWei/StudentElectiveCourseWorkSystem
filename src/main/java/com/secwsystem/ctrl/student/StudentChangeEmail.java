package com.secwsystem.ctrl.student;

import com.secwsystem.app.Student;
import com.secwsystem.dao.impl.StudentDAO;
import com.secwsystem.dao.pojo.StudentPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentChangeEmail {

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage;

    @FXML
    private TextField a_new_email;

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
            if(a_new_email.getText().trim().isEmpty()){
                throw new StudentException.PasswordNullException();
            }
            if(new Student().showMessage("提示","确定修改邮箱？", Alert.AlertType.CONFIRMATION,1)){
                //将新密码上传到数据库
                StudentLogin controller = (StudentLogin) StudentContext.controllers.get(StudentLogin.class.getSimpleName());
                StudentDAO studentDAO = new StudentDAO();
                StudentPrivate studentPrivate = studentDAO.getPrivate(controller.getsid());
                studentPrivate.setSEmail(a_new_email.getText());
                if(studentDAO.updatePrivate(studentPrivate)){
                    //生成提示界面，关闭本界面
                    new Student().showMessage("提示","邮箱修改成功", Alert.AlertType.INFORMATION,0);
                    stage.close();
                }else{
                    throw new com.secwsystem.ctrl.student.StudentException.ChangePasswordException();
                }

            }
        } catch (StudentException.PasswordNullException e) {
            new Student().showMessage("邮箱修改失败","新邮箱不能为空", Alert.AlertType.ERROR,0);
        } catch (StudentException.ChangePasswordException e) {
            new Student().showMessage("邮箱修改失败","邮箱修改失败", Alert.AlertType.ERROR,0);
        }
    }

}
