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

/**
 * 学生应用入口类，继承自JavaFX的Application类。
 * 负责应用的初始化和界面展示。
 */
public class StudentApplication extends Application {

    /**
     * 应用程序的入口点。
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * 应用程序启动时调用的方法，用于初始化登录界面。
     * @param primaryStage 主舞台
     */
    @Override
    public void start(Stage primaryStage) {
        initLogin();
    }

    /**
     * 初始化登录界面。
     * 通过FXMLLoader加载登录界面的布局文件，并设置舞台和控制器。
     */
    public void initLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/secwsystem/student/StudentLogin.fxml"));
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

    /**
     * 打开主应用程序界面。
     * 通过FXMLLoader加载主界面的布局文件，并设置舞台和控制器。
     * @throws IOException 如果加载FXML文件失败抛出此异常
     */
    public void mainApp() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/student/StudentMain.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage regStage = new Stage();

        regStage.setTitle("学生端主界面");
        regStage.setScene(scene);

        StudentMainController controller = loader.getController();
        controller.setStage(regStage);

        regStage.show();
    }

    /**
     * 打开修改密码界面。
     * 通过FXMLLoader加载修改密码界面的布局文件，并设置舞台和控制器。
     * @throws IOException 如果加载FXML文件失败抛出此异常
     */
    public void changePassword() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/student/StudentChangePassword.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setTitle("修改密码");
        stage.setScene(scene);

        StudentChangePassword controller = loader.getController();
        controller.setStage(stage);

        stage.showAndWait();
    }

    /**
     * 打开修改手机号界面。
     * 通过FXMLLoader加载修改手机号界面的布局文件，并设置舞台和控制器。
     * @throws IOException 如果加载FXML文件失败抛出此异常
     */
    public void changePhoneNumber() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/student/StudentChangePhoneNumber.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setTitle("修改手机号");
        stage.setScene(scene);

        StudentChangePhoneNumber controller = loader.getController();
        controller.setStage(stage);

        stage.showAndWait();
    }

    /**
     * 打开修改邮箱界面。
     * 通过FXMLLoader加载修改邮箱界面的布局文件，并设置舞台和控制器。
     * @throws IOException 如果加载FXML文件失败抛出此异常
     */
    public void changeEmail() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/student/StudentChangeEmail.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setTitle("修改邮箱");
        stage.setScene(scene);

        StudentChangeEmail controller = loader.getController();
        controller.setStage(stage);

        stage.showAndWait();
    }

    /**
     * 显示消息提示框。
     * 根据信号量的不同，可能需要确认操作。
     * @param title 提示框标题
     * @param text 提示框文本内容
     * @param type 提示框类型
     * @param signal 信号量，用于决定是否需要确认
     * @return 用户是否确认了操作
     */
    public boolean showMessage(String title, String text, Alert.AlertType type, int signal) {
        boolean flag = false;
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        if (signal == 1) {
            Optional<ButtonType> result = alert.showAndWait();
            alert.setContentText("确认这样选择吗？");
            flag = (result.get() == ButtonType.OK);
        } else
            alert.showAndWait();
        return flag;
    }
}