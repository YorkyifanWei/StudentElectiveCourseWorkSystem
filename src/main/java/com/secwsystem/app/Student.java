package com.secwsystem.app;

import com.secwsystem.ctrl.student.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Student extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        initlogin();
    }

    public void initlogin(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/secwsystem/student/login_manager.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("学生登录界面");
            primaryStage.setScene(scene);

            StudentLogin controller = loader.getController();
            controller.setStage(primaryStage);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MainApp() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/student/main_manager.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage regStage = new Stage();

        regStage.setTitle("学生端主界面");
        regStage.setScene(scene);

        StudentMainController controller = loader.getController();
        controller.setStage(regStage);

        regStage.show();
    }

    public void ChangePassword() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/student/change_password.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setTitle("修改密码");
        stage.setScene(scene);

        ChangePassword controller = loader.getController();
        controller.setStage(stage);

        stage.showAndWait();
    }

    public void ChangePhonenumber() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/student/change_phonenumber.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setTitle("修改手机号");
        stage.setScene(scene);

        ChangePhoneNumber controller = loader.getController();
        controller.setStage(stage);

        stage.showAndWait();
    }

    public void ChangeEmail() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/student/change_email.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setTitle("修改邮箱");
        stage.setScene(scene);

        ChangeEmail controller = loader.getController();
        controller.setStage(stage);

        stage.showAndWait();
    }

    public boolean showMessage(String title, String text, Alert.AlertType type, int I){
        boolean boo=false;
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        if(I==1) {
            Optional<ButtonType> result=alert.showAndWait();
            alert.setContentText("确认这样选择吗？");
            boo=(result.get() == ButtonType.OK);
        }
        else
            alert.showAndWait();
        return boo;
    }
}
