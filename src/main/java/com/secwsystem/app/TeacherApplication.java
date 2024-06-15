package com.secwsystem.app;

import com.secwsystem.ctrl.teacher.*;
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
 * TeacherApplication 类继承自 Application 类，用于全局初始化和管理教师相关的应用程序状态。
 * 该类在应用程序的生命周期内只实例化一次，为教师提供了一种存储和访问全局状态的方式。
 */
public class TeacherApplication extends Application {

/**
 * 程序的入口点。
 * 调用launch方法启动应用程序，传入命令行参数args。
 * @param args 命令行参数，用于传递给应用程序的启动参数。
 */
public static void main(String[] args) {
    launch(args);
}

    /**
     * 当应用程序启动时，此方法被调用以初始化登录过程。
     * @param primaryStage 应用程序的主要舞台，此处未使用但必须传递以符合接口要求。
     */
    @Override
    public void start(Stage primaryStage) {
        initLogin();
    }

    /**
     * 初始化登录界面。
     * 该方法负责加载登录界面的FXML文件，设置界面的场景和标题，并显示登录窗口。
     * 它使用FXMLLoader来加载界面布局，通过设置控制器和舞台来确保界面与逻辑的交互。
     * 如果在加载过程中发生IO异常，将打印堆栈跟踪。
     */
    public void initLogin() {
        try {
            // 创建FXMLLoader实例，用于加载FXML文件
            FXMLLoader loader = new FXMLLoader();
            // 设置FXML文件的位置，使用相对路径从当前类的资源中加载
            loader.setLocation(getClass().getResource("/com/secwsystem/teacher/login_manager.fxml"));
            // 加载FXML文件，返回界面的根节点
            AnchorPane root = loader.load();
            // 创建一个新的场景，将加载的界面作为其内容
            Scene scene = new Scene(root);

            // 创建主舞台，用于显示登录界面
            Stage primaryStage = new Stage();
            // 设置舞台的标题
            primaryStage.setTitle("教师登录界面");
            // 设置舞台的场景为刚刚创建的场景
            primaryStage.setScene(scene);

            // 从loader中获取登录控制器实例
            TeacherLogin controller = loader.getController();
            // 设置控制器的舞台为刚刚创建的主舞台，以便控制器可以操作舞台
            controller.setStage(primaryStage);

            // 显示主舞台
            primaryStage.show();
        } catch (IOException e) {
            // 如果在加载FXML文件时发生IO异常，打印异常信息
            e.printStackTrace();
        }
    }

    /**
     * 主应用入口函数。
     * 该函数初始化并展示了教师端主界面的窗口。
     * 
     * @throws IOException 如果加载FXML文件时发生错误。
     */
    public void mainApp() throws IOException {
        // 创建FXMLLoader对象，用于加载界面布局文件。
        FXMLLoader loader = new FXMLLoader();
        // 设置加载的FXML文件路径。
        loader.setLocation(getClass().getResource("/com/secwsystem/teacher/main_manager.fxml"));
        // 通过loader加载FXML文件，得到界面根节点。
        AnchorPane root = loader.load();
        // 创建场景对象，设置场景的根节点为加载的界面。
        Scene scene = new Scene(root);

        // 创建新的舞台对象，用于展示主界面。
        Stage regStage = new Stage();
        // 设置舞台的标题。
        regStage.setTitle("教师端主界面");
        // 设置舞台的场景为之前创建的场景。
        regStage.setScene(scene);

        // 从loader中获取界面控制器对象。
        TeacherMainController controller = loader.getController();
        // 设置控制器中的舞台引用，以便控制器能操作舞台。
        controller.setStage(regStage);

        // 显示舞台。
        regStage.show();
    }

    /**
     * 弹出修改密码的窗口。
     * 
     * 该方法通过FXMLLoader加载一个特定的FXML文件，用于构建修改密码窗口的用户界面。
     * 它设置窗口的标题、场景，并将加载的界面设置为场景的内容。然后，获取界面控制器对象，
     * 为控制器设置当前舞台，最后显示窗口并等待用户操作。
     * 
     * @throws IOException 如果加载FXML文件时发生IO异常。
     */
    public void changePassword() throws IOException {
        // 创建FXMLLoader对象，用于加载FXML文件。
        FXMLLoader loader = new FXMLLoader();
        // 设置FXML文件的位置，该文件定义了修改密码窗口的布局。
        loader.setLocation(getClass().getResource("/com/secwsystem/teacher/change_password.fxml"));
        // 通过loader加载FXML文件，返回界面的根节点。
        AnchorPane root = loader.load();
        // 创建一个新的场景，将加载的界面作为场景的内容。
        Scene scene = new Scene(root);

        // 创建一个新的舞台，用于显示修改密码窗口。
        Stage stage = new Stage();

        // 设置舞台的标题。
        stage.setTitle("修改密码");
        // 设置舞台的场景为刚刚创建的场景。
        stage.setScene(scene);

        // 从loader中获取界面控制器对象。
        TeacherChangePassword controller = loader.getController();
        // 为控制器设置当前舞台，以便控制器可以操作舞台。
        controller.setStage(stage);

        // 显示舞台，并等待用户操作。
        stage.showAndWait();
    }

    /**
     * 弹出窗口用于更改电话号码。
     * 
     * 此方法通过FXMLLoader加载一个用户界面，该界面用于教师更改他们的电话号码。
     * 它首先设置用户界面的资源位置，然后加载界面并创建一个场景。
     * 接着，一个新阶段被创建并配置为显示这个场景。
     * 最后，控制器被获取并设置阶段，以便控制器可以访问和操作阶段属性，如标题和可见性。
     * 
     * @throws IOException 如果加载资源或界面时发生错误。
     */
    public void changePhoneNumber() throws IOException {
        // 初始化FXMLLoader，用于加载用户界面。
        FXMLLoader loader = new FXMLLoader();
        // 设置FXML资源的位置。
        loader.setLocation(getClass().getResource("/com/secwsystem/teacher/change_phonenumber.fxml"));
        // 通过loader加载FXML资源，得到根节点。
        AnchorPane root = loader.load();
        // 创建一个新的场景，设置根节点为加载的界面。
        Scene scene = new Scene(root);

        // 创建一个新的舞台，用于显示更改电话号码的窗口。
        Stage stage = new Stage();

        // 设置舞台的标题。
        stage.setTitle("修改手机号");
        // 设置舞台的场景为刚刚创建的场景。
        stage.setScene(scene);

        // 通过loader获取控制器实例。
        TeacherChangePhoneNumber controller = loader.getController();
        // 设置控制器的舞台为当前创建的舞台，以便控制器可以操作舞台。
        controller.setStage(stage);

        // 显示舞台，并等待用户操作完成后关闭舞台。
        stage.showAndWait();
    }

    /**
     * 弹出修改邮箱的窗口。
     * 
     * 此方法通过FXMLLoader加载一个用户界面，该界面用于教师更改其账户的电子邮件地址。
     * 它首先设置界面的资源位置，然后加载界面并创建一个Scene对象，该对象包含此界面。
     * 接下来，它创建一个Stage对象，并为这个窗口设置标题和场景。
     * 然后，它从FXMLLoader获取界面控制器，并将当前Stage对象传递给控制器，以便控制器可以访问它。
     * 最后，它显示这个窗口，并等待用户操作完成后窗口关闭。
     * 
     * @throws IOException 如果加载界面时发生IO错误，则抛出此异常。
     */
    public void changeEmail() throws IOException {
        // 初始化FXMLLoader，用于加载用户界面的定义。
        FXMLLoader loader = new FXMLLoader();
        // 设置用户界面的资源位置。
        loader.setLocation(getClass().getResource("/com/secwsystem/teacher/change_email.fxml"));
        // 通过loader加载用户界面，得到界面的根节点。
        AnchorPane root = loader.load();
        // 创建一个新的Scene对象，包含加载的用户界面。
        Scene scene = new Scene(root);

        // 创建一个新的Stage对象，用于显示修改邮箱的窗口。
        Stage stage = new Stage();

        // 设置窗口的标题。
        stage.setTitle("修改邮箱");
        // 设置窗口的场景为刚才创建的scene。
        stage.setScene(scene);

        // 从loader中获取界面控制器。
        TeacherChangeEmail controller = loader.getController();
        // 将当前的stage设置给控制器，以便控制器可以操作这个窗口。
        controller.setStage(stage);

        // 显示窗口，并等待用户操作。
        stage.showAndWait();
    }

    /**
     * 弹出一个新窗口，用于修改教师的办公室地址。
     * 
     * 该方法通过FXMLLoader加载一个特定的FXML文件，创建一个新的窗口，配置窗口的标题和内容，
     * 然后显示这个窗口，并等待用户操作完成后关闭窗口。
     * 
     * @throws IOException 如果加载FXML文件时发生错误，则抛出此异常。
     */
    public void changeAddress() throws IOException {
        // 创建FXMLLoader实例，用于加载FXML文件。
        FXMLLoader loader = new FXMLLoader();
        // 设置FXMLLoader的资源位置，指向修改地址的界面文件。
        loader.setLocation(getClass().getResource("/com/secwsystem/teacher/change_address.fxml"));
        // 使用FXMLLoader加载界面，返回界面的根节点。
        AnchorPane root = loader.load();
        // 创建一个新的Scene对象，设置其内容为加载的界面根节点。
        Scene scene = new Scene(root);

        // 创建一个新的Stage对象，用于表示一个新的窗口。
        Stage stage = new Stage();

        // 设置窗口的标题。
        stage.setTitle("修改办公室地址");
        // 设置窗口的内容为之前创建的Scene。
        stage.setScene(scene);

        // 从loader中获取到这个窗口的控制器对象。
        TeacherChangeAddress controller = loader.getController();
        // 设置这个窗口对象给控制器，以便控制器能操作窗口。
        controller.setStage(stage);

        // 显示窗口，并等待用户操作完成后窗口自动关闭。
        stage.showAndWait();
    }

    /**
     * 打开添加课程的窗口。
     * 该方法通过FXMLLoader加载添加课程的用户界面，并设置相应的舞台属性，最后显示这个舞台。
     * 使用FXMLLoader来加载UI资源，通过设置舞台的标题和场景，为用户提供一个明确的操作环境。
     * 同时，通过获取控制器实例，将舞台传递给控制器，以便控制器可以操作舞台属性，如关闭舞台。
     * 
     * @throws IOException 如果加载UI资源发生错误，则抛出此异常。
     */
    public void addCourse() throws IOException {
        // 初始化FXMLLoader，用于加载用户界面。
        FXMLLoader loader = new FXMLLoader();
        // 设置用户界面的资源路径。
        loader.setLocation(getClass().getResource("/com/secwsystem/teacher/add_course.fxml"));
        // 加载用户界面，返回根节点。
        AnchorPane root = loader.load();
        // 创建一个新的场景，设置根节点为加载的用户界面。
        Scene scene = new Scene(root);

        // 创建一个新的舞台，用于显示添加课程的窗口。
        Stage regStage = new Stage();

        // 设置舞台的标题。
        regStage.setTitle("课程详情");
        // 设置舞台的场景为加载的用户界面。
        regStage.setScene(scene);

        // 通过loader获取添加课程控制器的实例。
        TeacherAddCourse controller = loader.getController();
        // 将当前舞台设置给控制器，以便控制器可以操作舞台。
        controller.setStage(regStage);

        // 显示舞台。
        regStage.show();
    }

    /**
     * 显示一个包含特定标题、文本和类型的警告对话框。
     * 根据信号值决定是否需要用户确认操作。
     *
     * @param title 警告对话框的标题。
     * @param text 警告对话框中显示的文本内容。
     * @param type 警告对话框的类型，决定对话框的图标和按钮类型。
     * @param signal 控制对话框行为的信号，1 表示需要用户确认，其他值则不需要。
     * @return 如果对话框需要用户确认且用户点击了确认按钮，则返回 true；否则返回 false。
     */
    public boolean showMessage(String title, String text, Alert.AlertType type, int signal) {
        boolean flag = false;
        // 创建一个警告对话框实例，类型由参数 type 指定。
        Alert alert = new Alert(type);
        alert.setTitle(title);  // 设置对话框标题。
        alert.setHeaderText(null);  // 清空对话框的头信息。
        alert.setContentText(text);  // 设置对话框的文本内容。

        if (signal == 1) {
            // 如果信号值为 1，显示对话框并等待用户响应。
            Optional<ButtonType> result = alert.showAndWait();
            alert.setContentText("确认这样选择吗？");  // 在用户响应后，更改对话框文本为确认信息。
            // 根据用户的响应决定 flag 的值，如果用户点击了 OK 按钮，则 flag 为 true。
            flag = (result.get() == ButtonType.OK);
        } else {
            // 如果信号值不为 1，直接显示对话框并等待用户响应，不需要确认。
            alert.showAndWait();
        }
        return flag;  // 返回用户是否确认了操作的结果。
    }
}
