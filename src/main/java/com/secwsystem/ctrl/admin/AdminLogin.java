package com.secwsystem.ctrl.admin;

import com.secwsystem.app.AdminApplication;
import com.secwsystem.dao.impl.LoginImpl;
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
 * 管理员登录控制器，实现初始化接口。
 * 负责管理员登录流程的处理，包括界面初始化和登录验证。
 */
public class AdminLogin implements Initializable {

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
     * 登录窗口的舞台。
     */
    @FXML
    private Stage stage;

    /**
     * 账号ID输入字段。
     */
    @FXML
    private TextField aLoginAid;

    /**
     * 密码输入字段。
     */
    @FXML
    private PasswordField aLoginPassword;

    /**
     * 注册按钮。
     */
    @FXML
    private Button buttonRegister;

    /**
     * 登录按钮。
     */
    @FXML
    private Button buttonLogin;

    /**
     * 控制器初始化方法。
     * 在FXML加载完成后调用，用于执行初始化逻辑。
     *
     * @param url           FXML文件的URL。
     * @param resourceBundle FXML文件的资源包。
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AdminContext.controllers.put(this.getClass().getSimpleName(), this);
    }
    /**
     * 处理登录事件的方法。
     * 校验输入的账号和密码，并尝试进行登录操作。如果登录失败，显示相应的错误提示。
     *
     * @throws IOException 如果启动主应用程序发生错误。
     */
    public void loginEvent() throws IOException {
        String aid = aLoginAid.getText().trim();
        String aPassword = aLoginPassword.getText().trim();
        try {
            // 校验账号和密码是否为空
            if (aLoginAid.getText().trim().isEmpty()) {
                throw new AdminException.IdNullException();
            }
            if (aLoginPassword.getText().trim().isEmpty()) {
                throw new AdminException.PasswordNullException();
            }
            // 尝试登录，如果失败抛出异常
            if (!LoginImpl.loginAdmin(aid, aPassword)) {
                throw new AdminException.LoginErrorException();
            }
            // 登录成功，关闭登录窗口，打开主应用程序
            stage.close();
            new AdminApplication().mainApp();
        } catch (AdminException.IdNullException e) {
            new AdminApplication().showMessage("登录异常", "账号名不能为空!", Alert.AlertType.ERROR, 0);
        } catch (AdminException.PasswordNullException e) {
            new AdminApplication().showMessage("登录异常", "密码不能为空!", Alert.AlertType.ERROR, 0);
        } catch (AdminException.LoginErrorException e) {
            new AdminApplication().showMessage("登录异常", "登录失败，请重新登录!", Alert.AlertType.ERROR, 0);
        }
    }
    /**
     * 获取输入的账号ID。
     *
     * @return 输入的账号ID。
     */
    String getAid() {
        return aLoginAid.getText().trim();
    }
}