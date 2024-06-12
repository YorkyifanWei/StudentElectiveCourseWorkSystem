package com.secwsystem.ctrl.teacher;

import com.secwsystem.app.Teacher;
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

public class TeacherLoginController implements Initializable {

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage;

    @FXML
    private TextField a_login_tid;

    @FXML
    private PasswordField a_login_password;

    @FXML
    private Button btn_reg;

    @FXML
    private Button btn_login;


    public void LoginEvent() throws IOException {
        //这里需要数据库
        //TODO
        try{
            //判断账号名非空，否则执行if
            //TODO
            if(a_login_tid.getText().trim().isEmpty()){
                throw new Exception.IdNullException();
            }
            //判断密码非空，否则执行if
            //TODO
            if(a_login_password.getText().trim().isEmpty()){
                throw new Exception.PasswordNullException();
            }
            //判断用户名是否存在，否则执行if
            //TODO
           // if(){
                //throw new Exception.IdNotExistException();
            //}
            //判断密码是否正确，否则执行if
            //TODO
            //if(){
               //throw new Exception.PasswordErrorException();
           // }
            stage.close();
            new Teacher().MainApp();
        }catch(Exception.IdNullException e){
            new Teacher().showMessage("登录异常","账号名不能为空!", Alert.AlertType.ERROR,0);
        }catch (Exception.PasswordNullException e){
            new Teacher().showMessage("登录异常","密码不能为空!", Alert.AlertType.ERROR,0);
        }/*catch(Exception.IdNotExistException e){
            new Admin().showMessage("登录异常","账号不存在!", Alert.AlertType.ERROR,0);
        }catch(Exception.PasswordErrorException e){
            new Admin().showMessage("登录异常","密码错误,请重新输入!", Alert.AlertType.ERROR,0);
        }*/
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Context.controllers.put(this.getClass().getSimpleName(), this);
    }

    String gettid(){
        return a_login_tid.getText().trim();
    }
}
