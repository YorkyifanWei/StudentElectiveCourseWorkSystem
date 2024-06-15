package com.secwsystem.ctrl.teacher;

import com.secwsystem.app.TeacherApplication;
import com.secwsystem.dao.impl.TeacherDAO;
import com.secwsystem.dao.pojo.TeacherPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 老师更改邮箱的控制器类。
 * 该类提供了更改邮箱的界面操作逻辑，包括确认修改和取消修改。
 */
public class TeacherChangeEmail {

    /**
     * 获取当前舞台。
     * @return 当前的舞台对象
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * 设置当前舞台。
     * @param stage 当前的舞台对象
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage; // 主舞台

    @FXML
    private TextField tEmail; // 新邮箱输入框

    @FXML
    private Button buttonSet; // 确认修改按钮

    @FXML
    private Button buttonCancel; // 取消修改按钮

    /**
     * 取消操作事件处理方法。
     * 点击取消按钮时，关闭当前窗口。
     */
    @FXML
    void cancelEvent() {
        stage.close();
    }

    /**
     * 确认修改操作事件处理方法。
     * 点击确认修改按钮时，检查新邮箱是否为空，然后确认修改并更新数据库中的邮箱信息。
     */
    @FXML
    void setEvent() {
        try {
            // 检查新邮箱是否为空
            if (tEmail.getText().trim().isEmpty()) {
                throw new TeacherException.PasswordNullException();
            }
            // 如果用户确认修改
            if (new TeacherApplication().showMessage("提示", "确定修改邮箱？", Alert.AlertType.CONFIRMATION, 1)) {
                // 获取登录控制器和教师DAO
                TeacherLogin controller = (TeacherLogin) TeacherContext.controllers
                        .get(TeacherLogin.class.getSimpleName());
                TeacherDAO teacherDAO = new TeacherDAO();
                TeacherPrivate teacherPrivate = teacherDAO.getPrivate(controller.getTid());
                // 更新教师的邮箱信息
                teacherPrivate.setTEmail(tEmail.getText());

                // 更新数据库中的邮箱信息
                if (teacherDAO.updatePrivate(teacherPrivate)) {
                    // 显示修改成功的提示信息，并关闭当前窗口
                    new TeacherApplication().showMessage("提示", "密码修改成功", Alert.AlertType.INFORMATION, 0);
                    stage.close();
                } else {
                    throw new com.secwsystem.ctrl.teacher.TeacherException.ChangePasswordException();
                }
            }
        } catch (TeacherException.PasswordNullException e) {
            // 显示新邮箱不能为空的错误提示
            new TeacherApplication().showMessage("邮箱修改失败", "新邮箱不能为空", Alert.AlertType.ERROR, 0);
        } catch (TeacherException.ChangePasswordException e) {
            // 显示邮箱修改失败的错误提示
            new TeacherApplication().showMessage("邮箱修改失败", "邮箱修改失败", Alert.AlertType.ERROR, 0);
        }
    }

}