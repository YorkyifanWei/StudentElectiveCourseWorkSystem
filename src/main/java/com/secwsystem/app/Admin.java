package com.secwsystem.app;

import com.secwsystem.ctrl.admin.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Admin extends Application {



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
            loader.setLocation(getClass().getResource("/com/secwsystem/admin/login_admin.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("管理员登录界面");
            primaryStage.setScene(scene);

            AdminLoginController controller = loader.getController();
            controller.setStage(primaryStage);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MainApp() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/main_admin.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage Stage = new Stage();

        Stage.setTitle("管理员主界面");
        Stage.setScene(scene);

        MainController controller = loader.getController();
        controller.setStage(Stage);

        Stage.show();
    }

    public void AddCourse() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/add_Cou_admin.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage Stage = new Stage();

        Stage.setTitle("添加课程");
        Stage.setScene(scene);

        AddCouController controller = loader.getController();
        controller.setStage(Stage);

        Stage.show();
    }
    public void AddTea() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/add_Tea_admin.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage Stage = new Stage();

        Stage.setTitle("添加讲师");
        Stage.setScene(scene);

        AddTeaController controller = loader.getController();
        controller.setStage(Stage);

        Stage.show();
    }
    public void AddStu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/add_Stu_admin.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage Stage = new Stage();

        Stage.setTitle("添加学生");
        Stage.setScene(scene);

        AddStuController controller = loader.getController();
        controller.setStage(Stage);

        Stage.show();
    }

    public void ChangePassword() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/change_password.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage Stage = new Stage();

        Stage.setTitle("修改密码");
        Stage.setScene(scene);

        ChangePasswordController controller = loader.getController();
        controller.setStage(Stage);

        Stage.showAndWait();
    }

    public void ModifyCou() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/modify_Cou_admin.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Stage Stage = new Stage();

        Stage.setTitle("课程修改");
        Stage.setScene(scene);

        ModifyCouController controller = loader.getController();
        controller.setStage(Stage);

        Stage.showAndWait();
    }

    public void ModifyTea() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/modify_Tea_admin.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Stage Stage = new Stage();

        Stage.setTitle("讲师修改");
        Stage.setScene(scene);

        ModifyTeaController controller = loader.getController();
        controller.setStage(Stage);

        Stage.showAndWait();
    }

    public void ModifyStu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/modify_Stu_admin.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Stage Stage = new Stage();

        Stage.setTitle("学生修改");
        Stage.setScene(scene);

        ModifyStuController controller = loader.getController();
        controller.setStage(Stage);

        Stage.showAndWait();
    }
    public void GetCou() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/get_Cou_admin.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Stage Stage = new Stage();

        Stage.setTitle("课程查询");
        Stage.setScene(scene);

        Stage.showAndWait();
    }

    public void GetTea() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/get_Tea_admin.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Stage Stage = new Stage();

        Stage.setTitle("讲师查询");
        Stage.setScene(scene);

        Stage.showAndWait();
    }

    public void GetStu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/get_Stu_admin.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Stage Stage = new Stage();

        Stage.setTitle("学生查询");
        Stage.setScene(scene);

        Stage.showAndWait();
    }
    public void GetTeaFromCou() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/get_TeaFromCou_admin.fxml"));
        AnchorPane root = loader.load();

        Scene scene = new Scene(root);
        Stage Stage = new Stage();

        Stage.setTitle("查看教师");
        Stage.setScene(scene);

        Stage.showAndWait();
    }

    public void GetTeasFromCou() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/get_TeasFromCou_admin.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage Stage = new Stage();
        Stage.setTitle("查看课下教师");
        Stage.setScene(scene);

        Stage.showAndWait();
    }
    public void GetStuFromCou() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/get_StuFromCou_admin.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage Stage = new Stage();
        Stage.setTitle("查看课下学生");
        Stage.setScene(scene);

        Stage.showAndWait();
    }
    public void GetStusFromCou() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/get_StusFromCou_admin.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage Stage = new Stage();
        Stage.setTitle("查看课下学生");
        Stage.setScene(scene);

        Stage.showAndWait();
    }

    public void AddTeaInCou() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/add_TeaInCou_admin.fxml"));
        AnchorPane root = loader.load();

        Scene scene = new Scene(root);
        Stage Stage = new Stage();

        Stage.setTitle("给课程添加教师");
        Stage.setScene(scene);

        AddTeaInCouController controller = loader.getController();
        controller.setStage(Stage);

        Stage.showAndWait();
    }

    public void AddStuInCou() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/add_StuInCou_admin.fxml"));
        AnchorPane root = loader.load();

        Scene scene = new Scene(root);
        Stage Stage = new Stage();
        Stage.setTitle("给课程添加学生");
        Stage.setScene(scene);

        AddStuInCouController controller = loader.getController();
        controller.setStage(Stage);

        Stage.showAndWait();
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
