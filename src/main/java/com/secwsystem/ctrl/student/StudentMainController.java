package com.secwsystem.ctrl.student;

import com.secwsystem.app.StudentApplication;
import com.secwsystem.dao.impl.CourseDAO;
import com.secwsystem.dao.impl.StudentDAO;
import com.secwsystem.dao.pojo.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * 主控制器类，用于学生信息管理系统的初始化。
 * 实现Initializable接口，以在初始化阶段执行特定的配置工作。
 */
public class StudentMainController implements Initializable {

    @FXML
    private ListView<?> courseList; // 用于显示课程列表的ListView控件

    @FXML
    private TextField sClassAndSchool; // 输入框用于填写班级和学校信息

    @FXML
    private TextField sEmail; // 输入框用于填写电子邮件地址

    @FXML
    private TextField sIdcard; // 输入框用于填写身份证号码

    @FXML
    private TextField sName; // 输入框用于填写姓名

    @FXML
    private TextField sPhoneNumber; // 输入框用于填写电话号码

    @FXML
    private TextField sSex; // 输入框用于填写性别

    @FXML
    private TextField sid; // 输入框用于填写学生ID

    @FXML
    private TextField sPassword; // 输入框用于填写密码

    @FXML
    private Button buttonAlter; // 修改按钮，用于触发学生信息的修改操作

    @FXML
    private Button buttonCancel; // 取消按钮，用于取消当前操作并返回上一界面

    @FXML
    private MenuItem changeEmail; // 菜单项用于触发电子邮件地址的修改操作

    @FXML
    private MenuItem changePassword; // 菜单项用于触发密码的修改操作

    @FXML
    private MenuItem changePhoneNumber; // 菜单项用于触发电话号码的修改操作

    @FXML
    private TableColumn<Course, String> columnCCapacity; // 控制台课程信息表格的列，用于显示课程容量

    @FXML
    private TableColumn<Course, String> columnSelectCCapacity; // 选课系统课程信息表格的列，用于显示课程容量

    @FXML
    private TableColumn<Course, String> columnCCurrent; // 控制台课程信息表格的列，用于显示当前选课人数

    @FXML
    private TableColumn<Course, String> columnSelectCCurrent; // 选课系统课程信息表格的列，用于显示当前选课人数

    @FXML
    private TableColumn<Course, String> columnCEnd; // 控制台课程信息表格的列，用于显示课程结束时间

    @FXML
    private TableColumn<Course, String> columnSelectCEnd; // 选课系统课程信息表格的列，用于显示课程结束时间

    @FXML
    private TableColumn<Course, String> columnCInfo; // 控制台课程信息表格的列，用于显示课程信息

    @FXML
    private TableColumn<Course, String> columnSelectCInfo; // 选课系统课程信息表格的列，用于显示课程信息

    @FXML
    private TableColumn<Course, String> columnCInit; // 控制台课程信息表格的列，用于显示课程初始化人数

    @FXML
    private TableColumn<Course, String> columnSelectCInit; // 选课系统课程信息表格的列，用于显示课程初始化人数

    @FXML
    private TableColumn<Course, String> columnCLocation; // 控制台课程信息表格的列，用于显示课程地点

    @FXML
    private TableColumn<Course, String> columnSelecrCLocation; // 选课系统课程信息表格的列，用于显示课程地点

    @FXML
    private TableColumn<Course, String> columnCName; // 控制台课程信息表格的列，用于显示课程名称

    @FXML
    private TableColumn<Course, String> columnSelectCName; // 选课系统课程信息表格的列，用于显示课程名称

    @FXML
    private TableColumn<Course, String> columnCPeriod; // 控制台课程信息表格的列，用于显示课程上课周期

    @FXML
    private TableColumn<Course, String> columnSelectCPeriod; // 选课系统课程信息表格的列，用于显示课程上课周期

    @FXML
    private TableColumn<Course, String> columnCSchool; // 控制台课程信息表格的列，用于显示课程所属学院

    @FXML
    private TableColumn<Course, String> columnSelectCSchool; // 选课系统课程信息表格的列，用于显示课程所属学院

    @FXML
    private TableColumn<Course, String> columnCTime; // 控制台课程信息表格的列，用于显示课程上课时间

    @FXML
    private TableColumn<Course, String> columnSelectCTime; // 选课系统课程信息表格的列，用于显示课程上课时间

    @FXML
    private TableColumn<Course, String> columnCid; // 控制台课程信息表格的列，用于显示课程ID

    @FXML
    private TableColumn<Course, String> columnSelectCid; // 选课系统课程信息表格的列，用于显示课程ID

    @FXML
    private TableColumn<Course, String> columnId; // 控制台学生信息表格的列，用于显示学生ID

    @FXML
    private TableColumn<Course, String> columnSelectId; // 选课系统学生信息表格的列，用于显示学生ID

    @FXML
    private TableColumn<Course, String> columnCType; // 控制台课程信息表格的列，用于显示课程类型

    @FXML
    private TableColumn<Course, String> columnSelectCType; // 选课系统课程信息表格的列，用于显示课程类型

    @FXML
    private TableView<Course> tableView; // 控制台显示课程信息的表格

    @FXML
    private TableView<Course> tableViewSelect; // 选课系统显示课程信息的表格

    @FXML
    private Button buttonDropCourse; // 用于用户触发退课操作。

    @FXML
    private Button buttonChooseCourse; // 用于用户触发选课操作。

    @FXML
    private Stage stage; // 用于在窗口操作中引用主窗口。

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
     * 处理更改事件的函数。
     * 此函数被设计为响应某种更改操作的事件，例如点击按钮或选择菜单项。
     * 目前函数体为空，但可以根据需要添加实现来处理事件。
     *
     * @param event 事件对象，携带关于事件的详细信息，如事件源、事件类型等。
     */
    @FXML
    void alterEvent(ActionEvent event) {

    }

    /**
     * 处理取消事件的函数。
     * 此函数被设计为响应某种取消操作的事件，例如点击取消按钮。
     * 当用户选择取消操作时，此函数将被调用，用于执行取消操作所需的任何逻辑。
     * 目前函数体为空，可能是因为具体的取消逻辑尚未实现或被移除。
     * 
     * @param event 事件对象，携带关于事件的详细信息。
     */
    @FXML
    void cancelEvent(ActionEvent event) {

    }

    /**
     * 触发更改邮箱的事件处理方法。
     * 此方法通过调用StudentApplication类的changeEmail方法来实现邮箱的更改。
     * 使用FXML注解，表明此方法是由FXML界面控制器调用的。
     * 
     * @throws IOException 如果在处理过程中发生I/O错误。
     */
    @FXML
    void changeEmailEvent() throws IOException {
        new StudentApplication().changeEmail();
    }

    /**
     * 触发更改密码的事件处理方法。
     * 此方法通过调用StudentApplication类的changePassword方法来实现密码更改的功能。
     * 使用@FXML注解表明该方法是由FXML控制器直接调用的。
     * 
     * @throws IOException 如果在更改密码过程中发生I/O错误。
     */
    @FXML
    void changePasswordEvent() throws IOException {
        new StudentApplication().changePassword();
    }

    /**
     * 处理更改电话号码的事件。
     * 当用户触发更改电话号码的操作时，此方法被调用。
     * 它通过创建一个新的StudentApplication实例并调用其changePhoneNumber方法来执行实际的电话号码更改操作。
     * 
     * @throws IOException 如果在处理电话号码更改过程中发生I/O错误。
     */
    @FXML
    void changePhoneNumberEvent() throws IOException {
        new StudentApplication().changePhoneNumber();
    }

    /**
     * 处理选择课程的事件。
     * 当用户在表格中选择课程并触发动作事件时，此方法被调用。
     * 它首先检查是否有课程被选中，如果没有，则显示警告消息。
     * 如果有课程被选中，则尝试为当前学生添加该课程。
     * 如果添加成功，则更新表格显示，刷新课程选择列表，并在数据库中记录学生的选课信息。
     * 如果添加失败，则显示警告消息。
     *
     * @param event 动作事件对象，由选课操作触发。
     */
    @FXML
    void chooseCourseEvent(ActionEvent event) {
        // 获取当前选中的课程项
        Course course = tableView.getSelectionModel().getSelectedItem();
        // 获取当前选中课程的索引
        int index = tableView.getSelectionModel().getSelectedIndex();
        // 检查是否选择了课程，如果没有选择，显示警告消息
        if (index < 0) {
            new StudentApplication().showMessage("提示", "请选择课程", Alert.AlertType.WARNING, 0);
        }
        // 尝试为学生添加课程，如果成功则进行后续操作，否则显示警告消息
        if (course.addStudent(sid.getText())) {
            // 添加成功，更新表格显示，刷新课程选择列表，并在数据库中记录学生的选课信息
            new StudentApplication().showMessage("提示", "选课成功", Alert.AlertType.INFORMATION, 0);
            tableViewSelect.getItems().add(course);
            tableView.refresh();
            new CourseDAO().addStudent(course.getCid(), sid.getText().trim());
        } else {
            // 添加失败，显示警告消息
            new StudentApplication().showMessage("提示", "选课失败", Alert.AlertType.WARNING, 0);
        }
    }

    /**
     * 处理退课事件。
     * 当用户从表格中选择一门课程并触发退课操作时，此方法被调用。
     * 首先，它检查是否有课程被选中，如果没有，则显示警告消息。
     * 其次，它尝试退课，并根据操作结果显示相应的信息消息。
     * 如果退课成功，它会从表格中移除该课程，并刷新表格数据。
     *
     * @param event 退课操作触发的事件。
     */
    @FXML
    void dropCourseEvent(ActionEvent event) {
        // 获取选中的课程。
        Course course = tableViewSelect.getSelectionModel().getSelectedItem();
        // 获取选中课程的索引。
        int index = tableViewSelect.getSelectionModel().getSelectedIndex();
        // 检查是否选择了课程，如果没有选择，显示警告消息。
        if (index < 0) {
            new StudentApplication().showMessage("提示", "请选择课程", Alert.AlertType.WARNING, 0);
            return;
        }
        // 尝试退课，如果成功，执行后续操作。
        if (course.deleteStudent(sid.getText())) {
            // 退课成功，从表格中移除该课程。
            new StudentApplication().showMessage("提示", "退课成功", Alert.AlertType.INFORMATION, 0);
            tableViewSelect.getItems().remove(index);
            // 从数据库中删除学生对该课程的报名记录。
            new CourseDAO().deleteStudent(course.getCid(), sid.getText().trim());
            // 刷新表格以更新显示。
            tableView.refresh();
            tableViewSelect.refresh();
        } else {
            // 退课失败，显示警告消息。
            new StudentApplication().showMessage("提示", "退课失败", Alert.AlertType.WARNING, 0);
        }
    }

    /**
     * 初始化界面，加载学生信息和课程信息。
     * @param url 页面的URL地址
     * @param resourceBundle 页面的资源包
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 注册当前控制器到学生上下文中
        StudentContext.controllers.put(this.getClass().getSimpleName(), this);

        // 获取学生登录控制器和学生ID
        StudentDAO studentDAO = new StudentDAO();
        StudentLogin controller = (StudentLogin) StudentContext.controllers.get(StudentLogin.class.getSimpleName());
        String sid = controller.getSid();

        // 在界面上显示学生信息
        this.sid.setText(sid);
        sName.setText(studentDAO.getPublic(sid).getSName());
        sClassAndSchool.setText(studentDAO.getPublic(sid).getSSchool() + studentDAO.getPublic(sid).getSClass());
        sSex.setText(studentDAO.getPublic(sid).getSSex());
        sPhoneNumber.setText(studentDAO.getPublic(sid).getSPhoneNumber());
        sEmail.setText(studentDAO.getPublic(sid).getSEmail());
        sIdcard.setText(studentDAO.getPrivate(sid).getSIdcard());

        // 加载所有课程信息到表格
        // 以下是查询所有课程
        CourseDAO courseDAO = new CourseDAO();
        ArrayList<Course> courses = courseDAO.getAll();
        ObservableList<Course> coursesView = FXCollections.observableArrayList();
        coursesView.addAll(courses);
        tableView.setItems(coursesView);
        // 设置表格列的数据显示绑定
        columnId.setCellValueFactory(new PropertyValueFactory<Course, String>("id"));
        columnCid.setCellValueFactory(new PropertyValueFactory<Course, String>("cid"));
        columnCName.setCellValueFactory(new PropertyValueFactory<Course, String>("c_name"));
        columnCInfo.setCellValueFactory(new PropertyValueFactory<Course, String>("c_info"));
        columnCTime.setCellValueFactory(new PropertyValueFactory<Course, String>("c_time"));
        columnCInit.setCellValueFactory(new PropertyValueFactory<Course, String>("c_init"));
        columnCEnd.setCellValueFactory(new PropertyValueFactory<Course, String>("c_end"));
        columnCLocation.setCellValueFactory(new PropertyValueFactory<Course, String>("c_location"));
        columnCType.setCellValueFactory(new PropertyValueFactory<Course, String>("c_type"));
        columnCSchool.setCellValueFactory(new PropertyValueFactory<Course, String>("c_school"));
        columnCPeriod.setCellValueFactory(new PropertyValueFactory<Course, String>("c_period"));
        columnCCapacity.setCellValueFactory(new PropertyValueFactory<Course, String>("c_capacity"));
        columnCCurrent.setCellValueFactory(new PropertyValueFactory<Course, String>("c_current"));

        // 加载学生选修的课程信息到另一个表格
        // 以下是查询所选课程
        ArrayList<Course> coursesSelect = courseDAO.getCoursesFromStudent(sid);
        ObservableList<Course> coursesSelectView = FXCollections.observableArrayList();
        coursesSelectView.addAll(coursesSelect);
        tableViewSelect.setItems(coursesSelectView);
        // 设置选修课程表格列的数据显示绑定
        columnSelectId.setCellValueFactory(new PropertyValueFactory<Course, String>("id"));
        columnSelectCid.setCellValueFactory(new PropertyValueFactory<Course, String>("cid"));
        columnSelectCName.setCellValueFactory(new PropertyValueFactory<Course, String>("c_name"));
        columnSelectCInfo.setCellValueFactory(new PropertyValueFactory<Course, String>("c_info"));
        columnSelectCTime.setCellValueFactory(new PropertyValueFactory<Course, String>("c_time"));
        columnSelectCInit.setCellValueFactory(new PropertyValueFactory<Course, String>("c_init"));
        columnSelectCEnd.setCellValueFactory(new PropertyValueFactory<Course, String>("c_end"));
        columnSelecrCLocation.setCellValueFactory(new PropertyValueFactory<Course, String>("c_location"));
        columnSelectCType.setCellValueFactory(new PropertyValueFactory<Course, String>("c_type"));
        columnSelectCSchool.setCellValueFactory(new PropertyValueFactory<Course, String>("c_school"));
        columnSelectCPeriod.setCellValueFactory(new PropertyValueFactory<Course, String>("c_period"));
        columnSelectCCapacity.setCellValueFactory(new PropertyValueFactory<Course, String>("c_capacity"));
        columnSelectCCurrent.setCellValueFactory(new PropertyValueFactory<Course, String>("c_current"));
    }
}
