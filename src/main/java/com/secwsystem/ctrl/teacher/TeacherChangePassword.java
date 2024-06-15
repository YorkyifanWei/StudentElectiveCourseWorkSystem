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
 * 老师修改密码的控制器类。
 * 该类负责处理教师修改密码的用户界面交互，包括确认修改和取消修改的操作。
 */
public class TeacherChangePassword {

    /**
     * 获取当前窗口的Stage对象。
     * 
     * @return 返回当前窗口的Stage对象，用于对窗口进行进一步的操作或配置。
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * 设置当前的舞台对象。
     * 
     * 本方法用于将传入的舞台对象赋值给类内的stage属性。舞台（Stage）在图形用户界面中扮演着重要角色，
     * 它是顶级窗口，用于展示应用的主窗口或对话框等。通过设置不同的舞台，可以实现窗口状态的切换或管理，
     * 如设置窗口大小、位置、标题等。
     * 
     * @param stage 要设置的舞台对象，代表当前的应用窗口或对话框。
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage; // 当前窗口的舞台对象

    @FXML
    private TextField tPassword; // 输入新密码

    @FXML
    private Button buttonSet; // 触发修改密码

    @FXML
    private Button buttonCancel; // 关闭窗口，取消修改密码

    /**
     * 取消修改密码操作的事件处理方法。
     * 点击取消按钮时，关闭当前窗口。
     */
    @FXML
    void cancelEvent() {
        stage.close();
    }

    /**
     * 确认修改密码操作的事件处理方法。
     * 验证新密码是否为空，如果为空则抛出异常；否则，提示用户确认修改，若用户确认，则执行密码修改操作。
     */
    @FXML
    void setEvent() {
        try {
            // 验证新密码是否为空
            if (tPassword.getText().trim().isEmpty()) {
                throw new TeacherException.PasswordNullException();
            }
            // 如果用户确认修改，则执行修改操作
            if (new TeacherApplication().showMessage("提示", "确定修改密码？", Alert.AlertType.CONFIRMATION, 1)) {
                TeacherLogin controller = (TeacherLogin) TeacherContext.controllers
                        .get(TeacherLogin.class.getSimpleName());
                TeacherDAO teacherDAO = new TeacherDAO();
                TeacherPrivate teacherPrivate = teacherDAO.getPrivate(controller.getTid());
                teacherPrivate.setTPassword(tPassword.getText());
                // 更新数据库中的密码
                if (teacherDAO.updatePrivate(teacherPrivate)) {
                    // 密码修改成功，提示用户并关闭窗口
                    new TeacherApplication().showMessage("提示", "密码修改成功", Alert.AlertType.INFORMATION, 0);
                    stage.close();
                } else {
                    throw new com.secwsystem.ctrl.teacher.TeacherException.ChangePasswordException();
                }
            }
        } catch (TeacherException.PasswordNullException e) {
            // 新密码为空时，提示用户错误信息
            new TeacherApplication().showMessage("密码修改失败", "新密码不能为空", Alert.AlertType.ERROR, 0);
        } catch (TeacherException.ChangePasswordException e) {
            // 密码修改失败时，提示用户错误信息
            new TeacherApplication().showMessage("密码修改失败", "密码修改失败", Alert.AlertType.ERROR, 0);
        }
    }
}