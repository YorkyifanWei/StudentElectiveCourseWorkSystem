package com.secwsystem.ctrl.teacher;

import com.secwsystem.app.TeacherApplication;
import com.secwsystem.dao.impl.CourseDAO;
import com.secwsystem.dao.impl.TeacherDAO;
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
import java.util.ResourceBundle;

public class TeacherMainController implements Initializable {

    @FXML
    private ListView<?> courseList; // 教师课程列表视图

    @FXML
    private TextField tAddress; // 教师地址文本框

    @FXML
    private TextField tEmail; // 教师邮箱文本框

    @FXML
    private TextField tIdcard; // 教师身份证文本框

    @FXML
    private TextField tName; // 教师姓名文本框

    @FXML
    private TextField tSchool; // 教师所属学校文本框

    @FXML
    private TextField tSex; // 教师性别文本框

    @FXML
    private TextField tid; // 教师ID文本框

    @FXML
    private TextField tTitle; // 教师职称文本框

    @FXML
    private TextField tPhoneNumber; // 教师电话文本框

    @FXML
    private Button buttonAlter; // 修改信息按钮

    @FXML
    private Button buttonCancel; // 取消按钮

    @FXML
    private MenuItem changeAddress; // 修改地址菜单项

    @FXML
    private MenuItem changeEmail; // 修改邮箱菜单项

    @FXML
    private MenuItem changePassword; // 修改密码菜单项

    @FXML
    private MenuItem changePhoneNumber; // 修改电话菜单项

    @FXML
    private TableColumn<Course, String> columnCCapacity; // 课程容量列定义

    @FXML
    private TableColumn<Course, String> columnSelectCCapacity; // 选课容量列定义

    @FXML
    private TableColumn<Course, String> columnCCurrent; // 课程当前选课人数列定义

    @FXML
    private TableColumn<Course, String> columnSelectCCurrent; // 选课当前选课人数列定义

    @FXML
    private TableColumn<Course, String> columnCEnd; // 课程结束时间列定义

    @FXML
    private TableColumn<Course, String> columnSelectCEnd; // 选课结束时间列定义

    @FXML
    private TableColumn<Course, String> columnCInfo; // 课程信息列定义

    @FXML
    private TableColumn<Course, String> columnSelectCInfo; // 选课信息列定义

    @FXML
    private TableColumn<Course, String> columnCInit; // 课程开课时间列定义

    @FXML
    private TableColumn<Course, String> columnSelectCInit; // 选课开课时间列定义

    @FXML
    private TableColumn<Course, String> columnCLocation; // 课程地点列定义

    @FXML
    private TableColumn<Course, String> columnSelectCLocation; // 选课地点列定义

    @FXML
    private TableColumn<Course, String> columnCName; // 课程名称列定义

    @FXML
    private TableColumn<Course, String> columnSelectCName; // 选课名称列定义

    @FXML
    private TableColumn<Course, String> columnCPeriod; // 课程周期列定义

    @FXML
    private TableColumn<Course, String> columnSelectCPeriod; // 选课周期列定义

    @FXML
    private TableColumn<Course, String> columnCSchool; // 课程所属学院列定义

    @FXML
    private TableColumn<Course, String> columnSelectCSchool; // 选课所属学院列定义

    @FXML
    private TableColumn<Course, String> columnCTime; // 课程时间列定义

    @FXML
    private TableColumn<Course, String> columnSelectCTime; // 选课时间列定义

    @FXML
    private TableColumn<Course, String> columnCType; // 课程类型列定义

    @FXML
    private TableColumn<Course, String> columnSelectCType; // 选课类型列定义

    @FXML
    private TableColumn<Course, String> columnCid; // 课程ID列定义

    @FXML
    private TableColumn<Course, String> columnSelectCid; // 选课ID列定义

    @FXML
    private TableColumn<Course, String> columnId; // 课程ID列定义

    @FXML
    private TableColumn<Course, String> columnSelectId; // 选课ID列定义

    @FXML
    private TableView<Course> tableView; // 课程列表视图

    @FXML
    private TableView<Course> tableViewSelect; // 教师所授课程列表视图

    @FXML
    private Button buttonAddCourse; // 添加课程按钮

    @FXML
    private Stage stage; // 当前舞台

    /**
     * 获取当前舞台。
     *
     * @return 当前Stage对象
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * 设置当前舞台。
     *
     * @param stage 当前Stage对象
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // 修改事件处理方法
    @FXML
    void alterEvent(ActionEvent event) {}

    // 取消事件处理方法
    @FXML
    void cancelEvent(ActionEvent event) {}

    // 修改邮箱事件处理方法
    @FXML
    void changeEmailEvent() throws IOException {
        new TeacherApplication().changeEmail();
    }

    // 修改密码事件处理方法
    @FXML
    void changePasswordEvent() throws IOException {
        new TeacherApplication().changePassword();
    }

    // 修改电话事件处理方法
    @FXML
    void changePhoneNumberEvent() throws IOException {
        new TeacherApplication().changePhoneNumber();
    }

    // 修改地址事件处理方法
    @FXML
    void changeAddressEvent() throws IOException {
        new TeacherApplication().changeAddress();
    }

    /**
     * 触发教师添加课程的事件。
     * 此方法模拟教师应用程序中添加新课程的操作。当调用此方法时，它将启动教师应用程序并执行添加课程的功能。
     * 主要用于教学或测试环境中，以自动化或简化课程添加的过程。
     *
     * @throws IOException 如果在与教师应用程序交互过程中发生I/O错误，将抛出此异常。
     */
    public void teacherAddCourseEvent() throws IOException {
        new TeacherApplication().addCourse();
    }

    /**
     * 控制器初始化方法，用于在界面加载时获取教师信息和课程信息，并展示在界面上。
     * @param url 页面的URL地址，用于资源定位。
     * @param resourceBundle 页面的资源包，用于国际化等。
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 将当前控制器实例注册到TeacherContext中，以便于其他地方访问。
        com.secwsystem.ctrl.teacher.TeacherContext.controllers.put(this.getClass().getSimpleName(), this);

        // 初始化教师信息
        // 初始化教师信息
        TeacherDAO teacherDAO = new TeacherDAO();
        TeacherLogin controller = (TeacherLogin) TeacherContext.controllers.get(TeacherLogin.class.getSimpleName());
        String tid = controller.getTid();
        this.tid.setText(tid);
        tName.setText(teacherDAO.getPublic(tid).getTName());
        tTitle.setText(teacherDAO.getPublic(tid).getTTitle());
        tSchool.setText(teacherDAO.getPublic(tid).getTSchool());
        tSex.setText(teacherDAO.getPublic(tid).getTSex());
        tPhoneNumber.setText(teacherDAO.getPublic(tid).getTPhoneNumber());
        tEmail.setText(teacherDAO.getPublic(tid).getTEmail());
        tAddress.setText(teacherDAO.getPublic(tid).getTAddress());
        tIdcard.setText(teacherDAO.getPrivate(tid).getTIdcard());

        // 初始化课程信息
        CourseDAO courseDAO = new CourseDAO();
        ObservableList<Course> courses = FXCollections.observableArrayList(courseDAO.getAll());
        tableView.setItems(courses);

        // 为表格的每一列设置数据绑定
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

        // 初始化教师所教授的课程信息
        ObservableList<Course> coursesSelect = FXCollections.observableArrayList(courseDAO.getCoursesFromTeacher(tid));
        tableViewSelect.setItems(coursesSelect);

        // 为选中课程表格的每一列设置数据绑定
        columnSelectId.setCellValueFactory(new PropertyValueFactory<Course, String>("id"));
        columnSelectCid.setCellValueFactory(new PropertyValueFactory<Course, String>("cid"));
        columnSelectCName.setCellValueFactory(new PropertyValueFactory<Course, String>("c_name"));
        columnSelectCInfo.setCellValueFactory(new PropertyValueFactory<Course, String>("c_info"));
        columnSelectCTime.setCellValueFactory(new PropertyValueFactory<Course, String>("c_time"));
        columnSelectCInit.setCellValueFactory(new PropertyValueFactory<Course, String>("c_init"));
        columnSelectCEnd.setCellValueFactory(new PropertyValueFactory<Course, String>("c_end"));
        columnSelectCLocation.setCellValueFactory(new PropertyValueFactory<Course, String>("c_location"));
        columnSelectCType.setCellValueFactory(new PropertyValueFactory<Course, String>("c_type"));
        columnSelectCSchool.setCellValueFactory(new PropertyValueFactory<Course, String>("c_school"));
        columnSelectCPeriod.setCellValueFactory(new PropertyValueFactory<Course, String>("c_period"));
        columnSelectCCapacity.setCellValueFactory(new PropertyValueFactory<Course, String>("c_capacity"));
        columnSelectCCurrent.setCellValueFactory(new PropertyValueFactory<Course, String>("c_current"));

    }
}
