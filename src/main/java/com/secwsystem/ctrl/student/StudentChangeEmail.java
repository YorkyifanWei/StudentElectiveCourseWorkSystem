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
 * 控制器类，用于处理学生更改邮箱的逻辑。
 */
public class StudentChangeEmail {

    /**
     * 获取当前舞台。
     *
     * @return 当前的Stage对象
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * 设置当前舞台。
     *
     * @param stage 要设置的Stage对象
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage; // 主舞台

    @FXML
    private TextField sEmail; // 新邮箱文本框

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
     * 确定操作，执行邮箱更改逻辑。
     */
    @FXML
    void setEvent() {
        try {
            // 检查新邮箱是否为空
            if (sEmail.getText().trim().isEmpty()) {
                throw new StudentException.PasswordNullException();
            }
            // 如果用户确认修改，则执行修改操作
            if (new StudentApplication().showMessage("提示", "确定修改邮箱？", Alert.AlertType.CONFIRMATION, 1)) {
                // 通过StudentContext获取登录控制器实例
                StudentLogin controller = (StudentLogin) StudentContext.controllers
                        .get(StudentLogin.class.getSimpleName());
                // 创建学生DAO实例
                StudentDAO studentDAO = new StudentDAO();
                // 根据登录学生ID获取学生私有信息
                StudentPrivate studentPrivate = studentDAO.getPrivate(controller.getSid());
                // 更新学生的邮箱
                studentPrivate.setSEmail(sEmail.getText());
                // 通过DAO更新学生私有信息
                if (studentDAO.updatePrivate(studentPrivate)) {
                    // 如果更新成功，提示用户并关闭当前舞台
                    // 生成提示界面，关闭本界面
                    new StudentApplication().showMessage("提示", "邮箱修改成功", Alert.AlertType.INFORMATION, 0);
                    stage.close();
                } else {
                    // 如果更新失败，抛出异常
                    throw new com.secwsystem.ctrl.student.StudentException.ChangePasswordException();
                }
            }
        } catch (StudentException.PasswordNullException e) {
            // 处理新邮箱为空的异常，提示用户
            new StudentApplication().showMessage("邮箱修改失败", "新邮箱不能为空", Alert.AlertType.ERROR, 0);
        } catch (StudentException.ChangePasswordException e) {
            // 处理邮箱修改失败的异常，提示用户
            new StudentApplication().showMessage("邮箱修改失败", "邮箱修改失败", Alert.AlertType.ERROR, 0);
        }
    }

}