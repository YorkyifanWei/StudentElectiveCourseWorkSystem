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
 * 学生更改电话号码的控制类。
 * 通过此类提供的方法，学生可以更改其在系统中的电话号码。
 */
public class StudentChangePhoneNumber {

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
    private Stage stage; // 主舞台

    @FXML
    private TextField sPhoneNumber; // 用于输入新电话号码的文本字段

    @FXML
    private Button buttonSet; // 确定按钮，用于提交新电话号码

    @FXML
    private Button buttonCancel; // 取消按钮，用于关闭窗口

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
     * 校验并处理提交的新电话号码，如果确认修改，则更新数据库中的电话号码。
     */
    @FXML
    void setEvent() {
        try {
            // 检查新电话号码是否为空
            if (sPhoneNumber.getText().trim().isEmpty()) {
                throw new StudentException.PasswordNullException();
            }
            // 如果用户确认修改，则进行数据库更新操作
            if (new StudentApplication().showMessage("提示", "确定修改手机号？", Alert.AlertType.CONFIRMATION, 1)) {
                StudentLogin controller = (StudentLogin) StudentContext.controllers
                        .get(StudentLogin.class.getSimpleName());
                StudentDAO studentDAO = new StudentDAO();
                StudentPrivate studentPrivate = studentDAO.getPrivate(controller.getSid());
                studentPrivate.setSPhoneNumber(sPhoneNumber.getText());
                // 更新数据库中的学生私密信息
                if (studentDAO.updatePrivate(studentPrivate)) {
                    // 更新成功，提示用户并关闭窗口
                    new StudentApplication().showMessage("提示", "密码修改成功", Alert.AlertType.INFORMATION, 0);
                    stage.close();
                } else {
                    throw new com.secwsystem.ctrl.student.StudentException.ChangePasswordException();
                }
            }
        } catch (StudentException.PasswordNullException e) {
            // 新电话号码为空时的错误提示
            new StudentApplication().showMessage("手机号修改失败", "新手机号不能为空", Alert.AlertType.ERROR, 0);
        } catch (StudentException.ChangePasswordException e) {
            // 更新数据库失败时的错误提示
            new StudentApplication().showMessage("手机号修改失败", "数据库错误", Alert.AlertType.ERROR, 0);
        }
    }
}