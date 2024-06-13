package com.secwsystem.ctrl.admin;

import com.secwsystem.app.Admin;
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

public class AdminLoginController implements Initializable {

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage;

    @FXML
    private TextField a_login_aid;

    @FXML
    private PasswordField a_login_password;

    @FXML
    private Button btn_reg;

    @FXML
    private Button btn_login;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Context.controllers.put(this.getClass().getSimpleName(), this);
    }

    public void LoginEvent() throws IOException {
        //这里需要数据库
        String a_id = a_login_aid.getText().trim();
        String a_password = a_login_password.getText().trim();
        try{
            //判断账号名非空，否则执行if
            if(a_login_aid.getText().trim().isEmpty()){
                throw new Exception.IdNullException();
            }
            //判断密码非空，否则执行if
            if(a_login_password.getText().trim().isEmpty()){
                throw new Exception.PasswordNullException();
            }
            //判断用户名是否存在，否则执行if
            if(!LoginImpl.loginAdmin(a_id,a_password)){
                throw new Exception.LoginErrorException();
            }
            stage.close();
            new Admin().MainApp();
        }catch(Exception.IdNullException e){
            new Admin().showMessage("登录异常","账号名不能为空!", Alert.AlertType.ERROR,0);
        }catch (Exception.PasswordNullException e){
            new Admin().showMessage("登录异常","密码不能为空!", Alert.AlertType.ERROR,0);
        }catch(Exception.LoginErrorException e){
            new Admin().showMessage("登录异常","登录失败，请重新登录!", Alert.AlertType.ERROR,0);
        }
    }

    String getaid(){
        return a_login_aid.getText().trim();
    }
}

