package com.secwsystem.ctrl.student;

import com.secwsystem.app.StudentApplication;
import com.secwsystem.dao.impl.StudentDAO;
import com.secwsystem.dao.pojo.StudentPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 学生密码更改控制器类。
 * 该类负责处理学生密码更改窗口的逻辑操作。
 */
public class StudentChangePassword {

    /**
     * 获取当前窗口的Stage对象。
     *
     * @return Stage 当前窗口的Stage对象
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * 设置当前窗口的Stage对象。
     *
     * @param stage 当前窗口的Stage对象
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage; // 登录窗口的舞台

    @FXML
    private TextField sPassword; // 新密码文本框

    @FXML
    private Button buttonSet; // 确定按钮

    @FXML
    private Button buttonCancel; // 取消按钮

    /**
     * 取消操作事件处理方法。
     * 点击取消按钮时关闭窗口。
     */
    @FXML
    void cancelEvent() {
        stage.close();
    }

    /**
     * 确定操作事件处理方法。
     * 点击确定按钮时，执行密码更改操作。
     */
    @FXML
    void setEvent() {
        try {
            // 检查新密码是否为空
            if (sPassword.getText().trim().isEmpty()) {
                throw new StudentException.PasswordNullException();
            }
            // 如果用户确认修改密码
            if (new StudentApplication().showMessage("提示", "确定修改密码？", Alert.AlertType.CONFIRMATION, 1)) {
                // 获取登录控制器实例和学生DAO实例
                StudentLogin controller = (StudentLogin) StudentContext.controllers
                        .get(StudentLogin.class.getSimpleName());
                StudentDAO studentDAO = new StudentDAO();
                // 根据学生ID获取学生私密信息
                StudentPrivate studentPrivate = studentDAO.getPrivate(controller.getSid());
                // 更新学生密码
                studentPrivate.setSPassword(sPassword.getText());
                if (studentDAO.updatePrivate(studentPrivate)) {
                    // 密码更新成功，显示提示信息并关闭窗口
                    new StudentApplication().showMessage("提示", "密码修改成功", Alert.AlertType.INFORMATION, 0);
                    stage.close();
                } else {
                    // 密码更新失败，抛出异常
                    throw new com.secwsystem.ctrl.student.StudentException.ChangePasswordException();
                }
            }
        } catch (StudentException.PasswordNullException e) {
            // 新密码为空时显示错误提示
            new StudentApplication().showMessage("密码修改失败", "新密码不能为空", Alert.AlertType.ERROR, 0);
        } catch (StudentException.ChangePasswordException e) {
            // 密码修改失败时显示错误提示
            new StudentApplication().showMessage("密码修改失败", "密码修改失败", Alert.AlertType.ERROR, 0);
        }
    }

}