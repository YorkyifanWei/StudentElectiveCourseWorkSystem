package com.secwsystem.ctrl.student;

import com.secwsystem.app.Student;
import com.secwsystem.dao.impl.StudentDAO;
import com.secwsystem.dao.pojo.StudentPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangePhonenumberController {

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage;

    @FXML
    private TextField a_new_phonenumber;

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
            if(a_new_phonenumber.getText().trim().isEmpty()){
                throw new Exception.PasswordNullException();
            }
            if(new Student().showMessage("提示","确定修改手机号？", Alert.AlertType.CONFIRMATION,1)){
                //将新手机号上传到数据库
                //TODO
                StudentLoginController controller = (StudentLoginController) Context.controllers.get(StudentLoginController.class.getSimpleName());
                StudentDAO studentDAO = new StudentDAO();
                StudentPrivate studentPrivate = studentDAO.getPrivate(controller.getsid());
                studentPrivate.setS_phone(a_new_phonenumber.getText());
                if(studentDAO.updatePrivate(studentPrivate)){
                    //生成提示界面，关闭本界面
                    new Student().showMessage("提示","密码修改成功", Alert.AlertType.INFORMATION,0);
                    stage.close();
                }else{
                    throw new com.secwsystem.ctrl.student.Exception.ChangePasswordException();
                }

            }
        } catch (Exception.PasswordNullException e) {
            new Student().showMessage("手机号修改失败","新手机号不能为空", Alert.AlertType.ERROR,0);
        } catch (Exception.ChangePasswordException e) {
            new Student().showMessage("手机号修改失败","数据库错误", Alert.AlertType.ERROR,0);
        }
    }

}
