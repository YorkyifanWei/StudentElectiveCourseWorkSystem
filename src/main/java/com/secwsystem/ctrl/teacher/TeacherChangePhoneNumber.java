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
 * 老师更改电话号码的控制器类。
 * 该类处理教师更改其电话号码的用户界面交互和相应的数据库操作。
 */
public class TeacherChangePhoneNumber {

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
    private Stage stage; // 当前窗口的舞台

    @FXML
    private TextField tPhoneNumber; // 输入新电话号码

    @FXML
    private Button buttonSet; // 提交新的电话号码

    @FXML
    private Button buttonCancel; // 取消按钮，用于关闭窗口

    /**
     * 取消操作时关闭窗口。
     */
    @FXML
    void cancelEvent() {
        stage.close();
    }

    /**
     * 确定操作时，验证并更新教师的电话号码。
     * 首先检查新电话号码是否为空，然后向用户确认修改。
     * 如果用户确认，将更新数据库中的电话号码，并显示成功消息。
     * 如果发生任何异常，将显示相应的错误消息。
     */
    @FXML
    void setEvent() {
        try {
            // 验证新电话号码是否为空
            if (tPhoneNumber.getText().trim().isEmpty()) {
                throw new TeacherException.PasswordNullException();
            }
            // 如果用户确认修改
            if (new TeacherApplication().showMessage("提示", "确定修改手机号？", Alert.AlertType.CONFIRMATION, 1)) {
                // 获取当前登录的教师信息
                TeacherLogin controller = (TeacherLogin) TeacherContext.controllers
                        .get(TeacherLogin.class.getSimpleName());
                TeacherDAO teacherDAO = new TeacherDAO();
                TeacherPrivate teacherPrivate = teacherDAO.getPrivate(controller.getTid());
                // 更新教师的电话号码
                teacherPrivate.setTPhoneNumber(tPhoneNumber.getText());

                // 尝试更新数据库中的教师信息
                if (teacherDAO.updatePrivate(teacherPrivate)) {
                    // 显示成功消息并关闭窗口
                    new TeacherApplication().showMessage("提示", "密码修改成功", Alert.AlertType.INFORMATION, 0);
                    stage.close();
                } else {
                    throw new com.secwsystem.ctrl.teacher.TeacherException.ChangePasswordException();
                }
            }
        } catch (TeacherException.PasswordNullException e) {
            // 显示错误消息：新电话号码不能为空
            new TeacherApplication().showMessage("手机号修改失败", "新手机号不能为空", Alert.AlertType.ERROR, 0);
        } catch (TeacherException.ChangePasswordException e) {
            // 显示错误消息：修改电话号码时发生数据库错误
            new TeacherApplication().showMessage("手机号修改失败", "数据库错误", Alert.AlertType.ERROR, 0);
        }
    }
}