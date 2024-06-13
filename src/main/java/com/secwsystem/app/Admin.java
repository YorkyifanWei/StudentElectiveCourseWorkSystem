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



    /**
     * 程序的入口点。
     * 调用launch方法启动应用程序，传入命令行参数args。
     * @param args 命令行参数，用于传递给应用程序的启动参数。
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * 当应用程序启动时，这个方法会被调用。
     * 它负责初始化登录界面。
     * 
     * @param primaryStage 应用程序的主要舞台，用于显示登录界面。
     */
    @Override
    public void start(Stage primaryStage) {
        initLogin();
    }

    /**
     * 初始化管理员登录界面。
     * 此方法负责加载登录界面的FXML文件，设置界面场景，并显示登录窗口。
     * 它使用FXMLLoader来加载界面，通过设置窗口标题和场景，为用户提供交互界面。
     * 同时，它还通过获取控制器实例，将主舞台传递给控制器，以便控制器可以操作窗口。
     */
    public void initLogin(){
        try{
            // 创建FXMLLoader实例，用于加载FXML文件
            FXMLLoader loader = new FXMLLoader();
            // 设置FXML文件的位置，使用相对路径从类路径中加载资源
            loader.setLocation(getClass().getResource("/com/secwsystem/admin/login_admin.fxml"));
            // 加载FXML文件，返回界面根节点
            AnchorPane root = loader.load();
            // 创建场景，并将加载的界面根节点设置为场景的内容
            Scene scene = new Scene(root);

            // 创建主舞台，并设置舞台的标题
            Stage primaryStage = new Stage();
            primaryStage.setTitle("管理员登录界面");
            // 设置舞台的场景为之前创建的场景
            primaryStage.setScene(scene);

            // 通过loader获取登录控制器实例
            AdminLogin controller = loader.getController();
            // 将主舞台设置给控制器，以便控制器可以操作舞台
            controller.setStage(primaryStage);

            // 显示主舞台
            primaryStage.show();
        } catch (IOException e) {
            // 打印堆栈跟踪，以记录和调试加载FXML文件时发生的任何IO异常
            e.printStackTrace();
        }
    }

    /**
     * 主应用入口函数，负责初始化和展示管理员主界面。
     * 
     * @throws IOException 如果加载FXML文件时发生错误。
     */
    public void mainApp() throws IOException {
        // 创建FXMLLoader对象，用于加载界面布局文件。
        FXMLLoader loader = new FXMLLoader();
        // 设置加载的FXML文件路径，使用相对路径从类路径中获取。
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/main_admin.fxml"));
        // 通过loader加载FXML文件，返回界面根节点。
        AnchorPane root = loader.load();
        // 创建Scene对象，设置场景为加载的界面根节点。
        Scene scene = new Scene(root);

        // 创建Stage对象，用于展示窗口。
        Stage stage = new Stage();
        // 设置窗口标题。
        stage.setTitle("管理员主界面");
        // 设置窗口的场景为之前创建的scene。
        stage.setScene(scene);

        // 从loader中获取控制器对象。
        AdminMainController controller = loader.getController();
        // 将创建的Stage对象设置给控制器，以便控制器可以操作窗口。
        controller.setStage(stage);

        // 显示窗口。
        stage.show();
    }

    /**
     * 弹出添加课程的窗口。
     * 该方法通过FXMLLoader加载添加课程的用户界面，并设置相应的舞台属性，最后显示这个舞台。
     * 这一过程用于在应用程序中启动一个新的窗口，用于添加新的课程信息。
     * 
     * @throws IOException 如果加载用户界面时发生IO错误，则抛出此异常。
     */
    public void addCourse() throws IOException {
        // 初始化FXMLLoader，用于加载用户界面的定义。
        FXMLLoader loader = new FXMLLoader();
        // 设置用户界面的资源路径，加载添加课程的FXML文件。
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/add_Cou_admin.fxml"));
        // 通过loader加载FXML文件，得到用户界面的根节点。
        AnchorPane root = loader.load();
        // 创建一个新的场景，设置其内容为加载的用户界面根节点。
        Scene scene = new Scene(root);

        // 创建一个新的舞台，用于显示添加课程的窗口。
        Stage stage = new Stage();

        // 设置舞台的标题。
        stage.setTitle("添加课程");
        // 设置舞台的场景为刚刚创建的场景。
        stage.setScene(scene);

        // 从loader中获取添加课程控制器实例。
        AddCourse controller = loader.getController();
        // 设置控制器中的舞台引用，以便控制器可以操作舞台。
        controller.setStage(stage);

        // 显示舞台。
        stage.show();
    }
    /**
     * 打开一个新窗口用于添加讲师。
     * 该方法通过FXMLLoader加载一个用户界面，配置窗口属性，并显示该窗口。
     * 使用者可以通过这个窗口界面添加新的讲师信息。
     *
     * @throws IOException 如果加载用户界面时发生IO错误，则抛出此异常。
     */
    public void addTeacher() throws IOException {
        // 创建FXMLLoader实例，用于加载用户界面的定义。
        FXMLLoader loader = new FXMLLoader();
        // 设置用户界面的资源路径，加载添加讲师的界面。
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/add_Tea_admin.fxml"));
        // 通过loader加载界面，得到界面的根节点。
        AnchorPane root = loader.load();
        // 创建一个新的场景，将加载的界面作为其内容。
        Scene scene = new Scene(root);

        // 创建一个新的舞台（窗口）。
        Stage stage = new Stage();

        // 设置舞台的标题。
        stage.setTitle("添加讲师");
        // 设置舞台的场景为刚刚创建的场景。
        stage.setScene(scene);

        // 从loader中获取控制器实例。
        AddTeacher controller = loader.getController();
        // 设置控制器中的舞台引用，以便控制器可以操作舞台。
        controller.setStage(stage);

        // 显示舞台。
        stage.show();
    }
    /**
     * 打开一个新窗口用于添加学生信息。
     * 此方法通过FXMLLoader加载一个新的用户界面，用于输入和提交学生信息。
     * 
     * @throws IOException 如果加载界面资源失败，则抛出此异常。
     */
    public void addStudent() throws IOException {
        // 创建FXMLLoader实例，用于加载用户界面资源。
        FXMLLoader loader = new FXMLLoader();
        // 设置用户界面资源的位置，指向添加学生界面的FXML文件。
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/add_Stu_admin.fxml"));
        // 通过loader加载界面资源，返回界面的根节点。
        AnchorPane root = loader.load();
        // 创建一个新的Scene对象，将加载的界面作为其内容。
        Scene scene = new Scene(root);

        // 创建一个新的Stage对象，用于展示新的窗口。
        Stage stage = new Stage();

        // 设置新窗口的标题。
        stage.setTitle("添加学生");
        // 设置新窗口的场景为刚刚创建的scene。
        stage.setScene(scene);

        // 从loader中获取添加学生界面的控制器实例。
        AddStudent controller = loader.getController();
        // 将当前stage设置给控制器，以便控制器可以操作窗口。
        controller.setStage(stage);

        // 显示新窗口。
        stage.show();
    }
    /**
     * 弹出一个窗口用于用户更改密码。
     * 此方法加载并显示一个FXML界面，该界面专门用于用户更改其账户密码。
     * 它首先初始化FXMLLoader，指定FXML文件的位置，然后加载界面并设置场景。
     * 接着，创建一个舞台，设置舞台的标题和场景，并将舞台设置为模态对话框模式显示。
     * 通过FXMLLoader获取到控制器实例，然后将当前舞台设置给控制器，以便控制器可以操作舞台。
     * 最后，显示对话框并等待用户操作完成。
     * 
     * @throws IOException 如果加载FXML文件时发生错误，则抛出此异常。
     */
    public void changePassword() throws IOException {
        // 初始化FXMLLoader，用于加载界面布局
        FXMLLoader loader = new FXMLLoader();
        // 设置FXML文件的位置
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/change_password.fxml"));
        // 加载FXML文件，得到界面根节点
        AnchorPane root = loader.load();
        // 创建场景，并将根节点设置为该场景的内容
        Scene scene = new Scene(root);

        // 创建一个新的舞台
        Stage stage = new Stage();

        // 设置舞台的标题
        stage.setTitle("修改密码");
        // 设置舞台的场景
        stage.setScene(scene);

        // 从loader中获取控制器实例
        ChangePassword controller = loader.getController();
        // 将当前舞台设置给控制器，以便控制器可以操作舞台
        controller.setStage(stage);

        // 显示舞台，并以模态方式等待用户操作
        stage.showAndWait();
    }
    /**
     * 弹出修改课程信息的窗口。
     * 该方法通过FXMLLoader加载修改课程的用户界面，并设置相应的舞台属性，最后显示这个舞台。
     * 使用者可以通过这个窗口对课程信息进行修改操作。
     * 
     * @throws IOException 如果加载用户界面时发生IO错误，则抛出此异常。
     */
    public void modifyCourse() throws IOException {
        // 创建FXMLLoader对象，用于加载用户界面。
        FXMLLoader loader = new FXMLLoader();
        // 设置用户界面的资源路径，加载修改课程的FXML文件。
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/modify_Cou_admin.fxml"));
        // 通过loader加载FXML文件，得到根节点AnchorPane。
        AnchorPane root = loader.load();
        // 创建一个新的场景，将加载的根节点设置为此场景的内容。
        Scene scene = new Scene(root);
        // 创建一个新的舞台，用于显示修改课程的窗口。
        Stage stage = new Stage();

        // 设置舞台的标题。
        stage.setTitle("课程修改");
        // 将创建的场景设置为舞台的场景。
        stage.setScene(scene);

        // 从loader中获取修改课程窗口的控制器对象。
        ModifyCourse controller = loader.getController();
        // 将创建的舞台设置给控制器，以便控制器可以操作舞台。
        controller.setStage(stage);

        // 显示舞台，并等待用户操作完毕。
        stage.showAndWait();
    }
    /**
     * 弹出修改讲师信息的窗口。
     * 该方法通过FXMLLoader加载修改讲师界面的FXML文件，设置界面标题，并显示这个窗口。
     * 使用者可以通过这个窗口对讲师信息进行修改操作。
     * 
     * @throws IOException 如果加载FXML文件时发生错误，则抛出此异常。
     */
    public void modifyTeacher() throws IOException {
        // 创建FXMLLoader对象，用于加载界面布局文件
        FXMLLoader loader = new FXMLLoader();
        // 设置FXML文件的路径，该路径为相对于类路径的路径
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/modify_Tea_admin.fxml"));
        // 通过loader加载界面布局文件，返回布局根节点
        AnchorPane root = loader.load();
        // 创建新的场景，将加载的界面作为场景的内容
        Scene scene = new Scene(root);
        // 创建新的舞台，用于显示修改讲师信息的窗口
        Stage stage = new Stage();

        // 设置舞台的标题
        stage.setTitle("讲师修改");
        // 设置舞台的场景为刚刚创建的场景
        stage.setScene(scene);

        // 从loader中获取控制器对象
        ModifyTeacher controller = loader.getController();
        // 将舞台设置到控制器中，以便控制器可以操作舞台
        controller.setStage(stage);

        // 显示舞台，并等待用户操作完成后关闭舞台
        stage.showAndWait();
    }
    /**
     * 弹出修改学生信息的窗口。
     * 该方法通过FXMLLoader加载修改学生信息的用户界面，并设置相应的窗口标题和场景。
     * 最后，显示窗口并等待用户操作完毕。
     * 
     * @throws IOException 如果加载用户界面时发生IO异常。
     */
    public void modifyStudent() throws IOException {
        // 创建FXMLLoader对象，用于加载用户界面。
        FXMLLoader loader = new FXMLLoader();
        // 设置用户界面的资源路径。
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/modify_Stu_admin.fxml"));
        // 通过loader加载用户界面，得到根节点。
        AnchorPane root = loader.load();
        // 创建新的场景，设置根节点为加载的用户界面。
        Scene scene = new Scene(root);
        // 创建新的舞台，用于显示修改学生信息的窗口。
        Stage stage = new Stage();

        // 设置舞台的标题。
        stage.setTitle("学生修改");
        // 设置舞台的场景为刚才创建的场景。
        stage.setScene(scene);

        // 从loader中获取控制器对象。
        ModifyStudent controller = loader.getController();
        // 设置控制器中的舞台引用，以便控制器可以操作舞台。
        controller.setStage(stage);

        // 显示舞台，并等待用户操作完毕。
        stage.showAndWait();
    }
    /**
     * 弹出课程查询窗口
     * 
     * 本方法通过FXMLLoader加载一个FXML文件，该文件定义了课程查询窗口的用户界面。
     * 加载完成后，创建一个Scene对象，将加载得到的根节点设置为Scene的根节点。
     * 随后，创建一个Stage对象，设置其标题为“课程查询”，并将Scene设置为该Stage的场景。
     * 最后，显示这个Stage，并等待用户操作完成。
     * 
     * @throws IOException 如果加载FXML文件时发生IO错误，则抛出此异常。
     */
    public void getCourse() throws IOException {
        // 创建FXMLLoader对象，用于加载FXML文件
        FXMLLoader loader = new FXMLLoader();
        // 设置FXML文件的位置，该文件定义了课程查询窗口的界面布局
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/get_Cou_admin.fxml"));
        // 通过loader加载FXML文件，得到界面的根节点
        AnchorPane root = loader.load();
        // 创建Scene对象，设置其根节点为加载得到的界面根节点
        Scene scene = new Scene(root);
        // 创建Stage对象，用于展示课程查询窗口
        Stage stage = new Stage();
        // 设置Stage的标题
        stage.setTitle("课程查询");
        // 设置Stage的场景为之前创建的scene
        stage.setScene(scene);
        // 显示Stage，并等待用户操作
        stage.showAndWait();
    }
    /**
     * 弹出讲师查询窗口
     * 
     * 该方法通过FXMLLoader加载一个FXML文件，创建一个新的Stage窗口，并设置窗口的标题和内容，
     * 最后显示这个窗口。这个方法主要用于展示讲师查询的界面，用户可以通过这个界面进行讲师信息的查询。
     * 
     * @throws IOException 如果加载FXML文件时发生错误，则抛出此异常
     */
    public void getTeacher() throws IOException {
        // 创建FXMLLoader对象，用于加载FXML文件
        FXMLLoader loader = new FXMLLoader();
        // 设置FXMLLoader的资源位置，指向讲师查询界面的FXML文件
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/get_Tea_admin.fxml"));
        // 使用FXMLLoader加载界面，并返回加载后的根节点
        AnchorPane root = loader.load();
        // 创建一个新的Scene对象，用于包含加载后的界面
        Scene scene = new Scene(root);
        // 创建一个新的Stage对象，用于展示界面
        Stage stage = new Stage();

        // 设置Stage的标题
        stage.setTitle("讲师查询");
        // 设置Stage的内容为刚刚创建的Scene
        stage.setScene(scene);

        // 显示Stage，并等待用户操作完成后关闭窗口
        stage.showAndWait();
    }
    /**
     * 弹出学生查询窗口
     * 
     * 该方法通过FXMLLoader加载一个FXML文件，以此创建一个新的Stage窗口来展示学生查询界面。
     * 使用这个方法，用户可以可视化地查询学生信息。
     * 
     * @throws IOException 如果加载FXML文件时发生错误，则抛出此异常
     */
    public void getStudent() throws IOException {
        // 创建FXMLLoader实例，用于加载界面布局文件
        FXMLLoader loader = new FXMLLoader();
        // 设置FXML文件的路径，该路径是一个相对路径，指向com.secwsystem.admin.get_Stu_admin.fxml文件
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/get_Stu_admin.fxml"));
        // 通过loader加载FXML文件，返回一个AnchorPane对象，该对象是界面的根节点
        AnchorPane root = loader.load();
        // 创建一个新的Scene对象，将加载的界面作为其内容
        Scene scene = new Scene(root);
        // 创建一个新的Stage对象，用于展示新的窗口
        Stage stage = new Stage();

        // 设置窗口的标题
        stage.setTitle("学生查询");
        // 设置窗口的场景为刚刚创建的scene
        stage.setScene(scene);

        // 显示窗口，并等待用户操作，直到窗口关闭
        stage.showAndWait();
    }
    /**
     * 通过加载FXML文件来初始化并展示查看教师信息的窗口。
     * 此方法主要负责窗口的布局和展示，不涉及具体的业务逻辑处理。
     * 
     * @throws IOException 如果加载FXML文件时发生IO异常，则抛出此异常。
     */
    public void getTeacherFromCourse() throws IOException {
        // 创建FXMLLoader对象，用于加载FXML文件。
        FXMLLoader loader = new FXMLLoader();
        // 设置FXML文件的位置，该文件位于资源目录下，用于定义窗口的布局。
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/get_TeaFromCou_admin.fxml"));
        // 通过loader加载FXML文件，得到窗口的根节点。
        AnchorPane root = loader.load();

        // 创建新的场景对象，将加载的根节点设置为场景的内容。
        Scene scene = new Scene(root);
        // 创建新的舞台对象，用于展示场景。
        Stage stage = new Stage();

        // 设置舞台的标题。
        stage.setTitle("查看教师");
        // 将场景设置给舞台。
        stage.setScene(scene);

        // 显示舞台，并等待用户操作完成后关闭舞台。
        stage.showAndWait();
    }
    /**
     * 通过FXML加载界面，展示所有课下教师的列表。
     * 此方法用于打开一个新窗口，显示从课程中获取的教师列表。
     * 使用FXMLLoader加载界面资源，并设置界面标题和内容。
     * 最后，显示这个新窗口，并等待用户操作完成后关闭。
     * 
     * @throws IOException 如果加载界面资源时发生错误，则抛出此异常。
     */
    public void getTeachersFromCourse() throws IOException {
        // 初始化FXMLLoader，用于加载界面资源。
        FXMLLoader loader = new FXMLLoader();
        // 设置界面资源的位置，路径指向fxml文件。
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/get_TeasFromCou_admin.fxml"));
        // 通过loader加载界面，得到界面的根节点。
        AnchorPane root = loader.load();
        // 创建一个新的场景，将加载的界面作为其内容。
        Scene scene = new Scene(root);

        // 创建一个新的舞台（窗口），用于展示界面。
        Stage stage = new Stage();
        // 设置舞台的标题。
        stage.setTitle("查看课下教师");
        // 设置舞台的场景为刚刚创建的场景。
        stage.setScene(scene);

        // 显示舞台，并等待用户操作完成后关闭。
        stage.showAndWait();
    }
    /**
     * 通过FXML加载界面，展示从课程中获取的学生列表。
     * 此方法用于打开一个新窗口，显示指定课程的学生列表。
     * 使用FXMLLoader加载界面资源，并设置窗口的标题和内容。
     * 最后，显示这个窗口，并等待用户操作完成。
     *
     * @throws IOException 如果加载界面资源时发生错误。
     */
    public void getStudentFromCourse() throws IOException {
        // 创建FXMLLoader实例，用于加载界面资源。
        FXMLLoader loader = new FXMLLoader();
        // 设置界面资源的位置，路径相对于项目的根目录。
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/get_StuFromCou_admin.fxml"));
        // 加载界面资源，返回界面的根节点。
        AnchorPane root = loader.load();
        // 创建新的Scene对象，设置其内容为加载的界面根节点。
        Scene scene = new Scene(root);

        // 创建新的Stage对象，用于展示新窗口。
        Stage stage = new Stage();
        // 设置窗口的标题。
        stage.setTitle("查看课下学生");
        // 设置窗口的内容为之前创建的Scene对象。
        stage.setScene(scene);

        // 显示窗口，并等待用户操作完成。
        stage.showAndWait();
    }
    /**
     * 通过FXMLLoader加载界面，用于展示从特定课程中获取的学生列表。
     * 此方法初始化一个新的舞台，设置其标题和场景，然后展示这个舞台。
     * @throws IOException 如果加载界面时发生IO异常，则抛出此异常。
     */
    public void getStudentsFromCourse() throws IOException {
        // 创建FXMLLoader实例，用于加载界面资源。
        FXMLLoader loader = new FXMLLoader();
        // 设置界面资源的位置，路径指向com.secwsystem.admin.get_StusFromCou_admin.fxml。
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/get_StusFromCou_admin.fxml"));
        // 通过loader加载界面，返回界面的根节点。
        AnchorPane root = loader.load();
        // 创建新的场景，将加载的界面作为其内容。
        Scene scene = new Scene(root);

        // 创建新的舞台实例。
        Stage stage = new Stage();
        // 设置舞台的标题。
        stage.setTitle("查看课下学生");
        // 设置舞台的场景为刚刚创建的场景。
        stage.setScene(scene);

        // 显示舞台，并等待用户操作完成后关闭舞台。
        stage.showAndWait();
    }
    /**
     * 将教师添加到课程中的功能方法。
     * 该方法通过FXMLLoader加载用户界面资源，创建一个新的舞台，并设置相应的场景和标题，
     * 最后显示这个舞台并等待用户操作。
     * 
     * @throws IOException 如果加载用户界面资源时发生错误，则抛出此异常。
     */
    public void addTeacherIntoCourse() throws IOException {
        // 使用FXMLLoader加载添加教师到课程的用户界面资源
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/add_TeaInCou_admin.fxml"));
        // 加载用户界面资源并将其作为根节点
        AnchorPane root = loader.load();

        // 创建一个新的场景，并将加载的根节点设置为场景的内容
        Scene scene = new Scene(root);
        // 创建一个新的舞台
        Stage stage = new Stage();

        // 设置舞台的标题
        stage.setTitle("给课程添加教师");
        // 设置舞台的场景
        stage.setScene(scene);

        // 获取控制器实例
        AddTeacherIntoCourse controller = loader.getController();
        // 设置控制器中的舞台引用，以便控制器可以操作舞台
        controller.setStage(stage);

        // 显示舞台，并等待用户操作完成后关闭舞台
        stage.showAndWait();
    }
    /**
     * 将学生添加到课程中的功能方法。
     * 该方法通过FXMLLoader加载用户界面资源，创建一个新的舞台，并设置相应的场景和标题，
     * 最后显示这个舞台并等待用户操作。
     * 
     * @throws IOException 如果加载用户界面资源时发生错误，则抛出此异常。
     */
    public void addStudentIntoCourse() throws IOException {
        // 使用FXMLLoader加载添加学生到课程的用户界面资源。
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/secwsystem/admin/add_StuInCou_admin.fxml"));
        // 加载用户界面资源并将其作为根节点。
        AnchorPane root = loader.load();

        // 创建一个新的场景，并将加载的根节点设置为场景的内容。
        Scene scene = new Scene(root);
        // 创建一个新的舞台，并设置舞台的标题。
        Stage stage = new Stage();
        stage.setTitle("给课程添加学生");
        // 设置舞台的场景为刚刚创建的场景。
        stage.setScene(scene);

        // 从loader中获取控制器实例，并设置舞台给控制器。
        AddStudentIntoCourse controller = loader.getController();
        controller.setStage(stage);

        // 显示舞台，并等待用户操作。
        stage.showAndWait();
    }
    /**
     * 显示一个警告对话框，根据用户的选择返回布尔值。
     * 
     * @param title 警告对话框的标题。
     * @param text 警告对话框显示的文本内容。
     * @param type 警告对话框的类型，确定对话框的图标和按钮类型。
     * @param signal 用于决定是否需要用户确认的标志位。如果为1，则要求用户确认；否则，不需要用户确认。
     * @return 如果用户点击了确认按钮，则返回true；否则返回false。
     */
    public boolean showMessage(String title, String text, Alert.AlertType type, int signal){
        // 初始化返回值为false，表示默认情况下用户没有确认。
        boolean flag = false;
        // 创建一个警告对话框，类型由参数type指定。
        Alert alert = new Alert(type);
        // 设置对话框的标题。
        alert.setTitle(title);
        // 不显示默认的标题文本。
        alert.setHeaderText(null);
        // 设置对话框显示的文本内容。
        alert.setContentText(text);
        // 如果标志位I为1，要求用户确认操作。
        if(signal == 1) {
            // 显示对话框并等待用户操作。
            Optional<ButtonType> result = alert.showAndWait();
            // 修改对话框文本为确认信息。
            alert.setContentText("确认这样选择吗？");
            // 根据用户点击的按钮类型，更新返回值。
            flag = (result.get() == ButtonType.OK);
        }
        // 如果标志位I不为1，直接显示对话框，不需要用户确认。
        else
            alert.showAndWait();
        // 返回用户是否确认的操作结果。
        return flag;
    }
}
