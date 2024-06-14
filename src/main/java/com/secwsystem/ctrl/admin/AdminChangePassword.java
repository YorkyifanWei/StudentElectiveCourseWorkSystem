package com.secwsystem.ctrl.admin;

import com.secwsystem.app.AdminApplication;
import com.secwsystem.dao.impl.AdminDAO;
import com.secwsystem.dao.pojo.AdminPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 控制器类，用于处理管理员修改密码的逻辑。
 */
public class AdminChangePassword {

    /**
     * 获取当前舞台。
     * @return 当前的Stage对象。
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * 设置当前舞台。
     * @param stage 要设置的Stage对象。
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage; // 主舞台

    @FXML
    private TextField aNewPassword; // 新密码文本框

    @FXML
    private Button buttonSet; // 确定按钮

    @FXML
    private Button buttonCancel; // 取消按钮

    /**
     * 取消操作，关闭当前舞台。
     */
    @FXML
    void cancelEvent() {
        stage.close();
    }

    /**
     * 确定修改密码的操作。
     * 验证新密码是否为空，如果为空则抛出PasswordNullException。
     * 如果不为空，则向用户确认修改密码，如果用户确认，则执行密码修改操作。
     * 如果密码修改成功，则显示成功消息并关闭当前舞台。
     * 如果密码修改失败，则抛出ChangePasswordException。
     */
    @FXML
    void setEvent() {
        try{
            // 判断新密码是否为空
            //判断新密码是否非空，是则执行if
            if (aNewPassword.getText().trim().isEmpty()) {
                throw new AdminException.PasswordNullException();
            }
            if (new AdminApplication().showMessage("提示","确定修改密码？", Alert.AlertType.CONFIRMATION,1)) {
                // 获取管理员登录控制器和DAO对象
                AdminLogin controller = (AdminLogin) AdminContext.controllers.get(AdminLogin.class.getSimpleName());
                AdminDAO adminDAO = new AdminDAO();
                AdminPrivate adminPrivate = adminDAO.getPrivate(controller.getAid());
                // 设置新密码并更新到数据库
                adminPrivate.setAPassword(aNewPassword.getText());
                if (adminDAO.updatePrivate(adminPrivate)) {
                    // 显示密码修改成功的消息并关闭当前舞台
                    new AdminApplication().showMessage("提示","密码修改成功", Alert.AlertType.INFORMATION,0);
                    stage.close();
                } else {
                    throw new AdminException.ChangePasswordException();
                }
            }
        } catch (AdminException.PasswordNullException e) {
            // 显示新密码为空的错误消息
            //抛出错误界面
            new AdminApplication().showMessage("密码修改失败","新密码不能为空", Alert.AlertType.ERROR,0);
        } catch (AdminException.ChangePasswordException e) {
            // 显示密码修改失败的错误消息
            new AdminApplication().showMessage("密码修改失败","密码修改失败,请重新修改", Alert.AlertType.ERROR,0);
        }
    }

}