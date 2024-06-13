package com.secwsystem.ctrl.admin;

import com.secwsystem.app.Admin;
import com.secwsystem.dao.impl.AdminDAO;
import com.secwsystem.dao.pojo.AdminPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangePassword{

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
            //判断新密码是否非空，是则执行if
            if(a_new_password.getText().trim().isEmpty()){
                throw new AdminException.PasswordNullException();
            }
            if(new Admin().showMessage("提示","确定修改密码？", Alert.AlertType.CONFIRMATION,1)){
                //将新密码上传到数据库
                AdminLogin controller = (AdminLogin) AdminContext.controllers.get(AdminLogin.class.getSimpleName());
                AdminDAO adminDAO = new AdminDAO();
                AdminPrivate adminPrivate = adminDAO.getPrivate(controller.getaid());
                adminPrivate.setA_password(a_new_password.getText());
                if(adminDAO.updatePrivate(adminPrivate)){
                    //生成提示界面，关闭本界面
                    new Admin().showMessage("提示","密码修改成功", Alert.AlertType.INFORMATION,0);
                    stage.close();
                }else{
                    throw new AdminException.ChangePasswordException();
                }

            }
        } catch (AdminException.PasswordNullException e) {
            //抛出错误界面
            new Admin().showMessage("密码修改失败","新密码不能为空", Alert.AlertType.ERROR,0);
        } catch (AdminException.ChangePasswordException e) {
            new Admin().showMessage("密码修改失败","密码修改失败,请重新修改", Alert.AlertType.ERROR,0);
        }
    }

}
