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
 * 老师修改地址控制器类。
 * 该类负责处理教师修改办公地址的用户界面交互。
 */
public class TeacherChangeAddress {

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
    private Stage stage; // 修改地址对话框的舞台

    @FXML
    private TextField tAddress; // 新地址文本字段

    @FXML
    private Button buttonSet; // 确定修改按钮

    @FXML
    private Button buttonCancel; // 取消按钮

    /**
     * 取消按钮点击事件处理方法。
     * 关闭修改地址对话框。
     */
    @FXML
    void cancelEvent() {
        stage.close();
    }

    /**
     * 确定修改按钮点击事件处理方法。
     * 验证新地址并将其更新到数据库中。
     */
    @FXML
    void setEvent() {
        try {
            // 验证新地址是否为空
            if (tAddress.getText().trim().isEmpty()) {
                throw new TeacherException.PasswordNullException();
            }
            // 如果用户确认修改，则进行数据库更新操作
            if (new TeacherApplication().showMessage("提示", "确定修改办公室地址？", Alert.AlertType.CONFIRMATION, 1)) {
                TeacherLogin controller = (TeacherLogin) TeacherContext.controllers
                        .get(TeacherLogin.class.getSimpleName());
                TeacherDAO teacherDAO = new TeacherDAO();
                TeacherPrivate teacherPrivate = teacherDAO.getPrivate(controller.getTid());
                teacherPrivate.setTAddress(tAddress.getText());
                // 更新教师的私有信息到数据库
                if (teacherDAO.updatePrivate(teacherPrivate)) {
                    // 修改成功，提示用户并关闭对话框
                    new TeacherApplication().showMessage("提示", "密码修改成功", Alert.AlertType.INFORMATION, 0);
                    stage.close();
                } else {
                    throw new com.secwsystem.ctrl.teacher.TeacherException.ChangePasswordException();
                }
            }
        } catch (TeacherException.PasswordNullException e) {
            // 新地址为空时，显示错误提示
            new TeacherApplication().showMessage("地址修改失败", "新地址不能为空", Alert.AlertType.ERROR, 0);
        } catch (TeacherException.ChangePasswordException e) {
            // 地址修改失败时，显示错误提示
            new TeacherApplication().showMessage("地址修改失败", "地址修改失败", Alert.AlertType.ERROR, 0);
        }
    }

}