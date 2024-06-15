package com.secwsystem.ctrl.teacher;

import com.secwsystem.app.TeacherApplication;
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
 * 教师登录控制器，实现登录逻辑及界面初始化。
 */
public class TeacherLogin implements Initializable {

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
    private Stage stage; // 登录窗口舞台

    @FXML
    private TextField tLoginTid; // 教师ID输入框

    @FXML
    private PasswordField tLoginPassword; // 教师密码输入框

    @FXML
    private Button buttonRegister; // 注册按钮

    @FXML
    private Button buttonLogin; // 登录按钮

    /**
     * 处理登录事件。
     * 验证输入的教师ID和密码，若符合要求则关闭登录窗口并打开主应用窗口。
     * @throws IOException 如果发生IO异常。
     */
    public void loginEvent() throws IOException {
        // 这里需要数据库
        try {
            // 验证教师ID非空
            // 判断账号名非空，否则执行if
            if (tLoginTid.getText().trim().isEmpty()) {
                throw new TeacherException.IdNullException();
            }
            // 验证密码非空
            // 判断密码非空，否则执行if
            if (tLoginPassword.getText().trim().isEmpty()) {
                throw new TeacherException.PasswordNullException();
            }
            // 登录验证成功，关闭登录窗口并打开主应用窗口
            stage.close();
            new TeacherApplication().mainApp();
        } catch (TeacherException.IdNullException e) {
            // 显示账号名不能为空的错误提示
            new TeacherApplication().showMessage("登录异常", "账号名不能为空!", Alert.AlertType.ERROR, 0);
        } catch (TeacherException.PasswordNullException e) {
            // 显示密码不能为空的错误提示
            new TeacherApplication().showMessage("登录异常", "密码不能为空!", Alert.AlertType.ERROR, 0);
        } /*
           * catch(Exception.IdNotExistException e){
           * new Admin().showMessage("登录异常","账号不存在!", Alert.AlertType.ERROR,0);
           * }catch(Exception.PasswordErrorException e){
           * new Admin().showMessage("登录异常","密码错误,请重新输入!", Alert.AlertType.ERROR,0);
           * }
           */
    }

    /**
     * 初始化控制器。
     * 将当前控制器实例注册到教师应用的上下文环境中。
     * @param url URL资源。
     * @param resourceBundle 资源包。
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TeacherContext.controllers.put(this.getClass().getSimpleName(), this);
    }

    /**
     * 获取教师ID输入框中的文本。
     * @return 教师ID文本。
     */
    String getTid() {
        return tLoginTid.getText().trim();
    }
}