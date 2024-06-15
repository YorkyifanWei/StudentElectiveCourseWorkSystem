package com.secwsystem.ctrl.student;

import com.secwsystem.app.StudentApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * 学生登录控制器，实现登录功能和界面初始化。
 */
public class StudentLogin implements Initializable {

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

    /**
     * 登录窗口的舞台对象。
     */
    @FXML
    private Stage stage;

    /**
     * 输入学号的文本字段。
     */
    @FXML
    private TextField sLoginSid;

    /**
     * 输入密码的密码字段。
     */
    @FXML
    private PasswordField sLoginPassword;

    /**
     * 注册按钮对象。
     */
    @FXML
    private Button buttonRegister;

    /**
     * 登录按钮对象。
     */
    @FXML
    private Button buttonLogin;

    /**
     * 处理登录按钮的点击事件。
     * 验证输入的学号和密码，如果有效则关闭当前窗口并打开主应用窗口。
     * 如果输入不合法，则显示相应的错误提示。
     * @throws IOException 如果打开主应用窗口时发生IO异常。
     */
    public void loginEvent() throws IOException {
        try {
            // 验证学号和密码是否为空
            if (sLoginSid.getText().trim().isEmpty()) {
                throw new StudentException.IdNullException();
            }
            if (sLoginPassword.getText().trim().isEmpty()) {
                throw new StudentException.PasswordNullException();
            }
            // 关闭登录窗口，打开主应用窗口
            stage.close();
            new StudentApplication().mainApp();
        } catch (StudentException.IdNullException e) {
            // 显示学号为空的错误提示
            new StudentApplication().showMessage("登录异常", "账号名不能为空!", Alert.AlertType.ERROR, 0);
        } catch (StudentException.PasswordNullException e) {
            // 显示密码为空的错误提示
            new StudentApplication().showMessage("登录异常", "密码不能为空!", Alert.AlertType.ERROR, 0);
        }
    }

    /**
     * 控制器初始化方法，由FXMLLoader调用。
     * 在界面加载后初始化控制器的属性。
     * @param url 页面的URL地址。
     * @param resourceBundle 页面的资源文件。
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 将当前控制器实例注册到学生上下文中的控制器映射中
        StudentContext.controllers.put(this.getClass().getSimpleName(), this);
    }

    /**
     * 获取输入的学号，去除前后空格。
     * @return 输入的学号字符串。
     */
    String getSid() {
        return sLoginSid.getText().trim();
    }
}