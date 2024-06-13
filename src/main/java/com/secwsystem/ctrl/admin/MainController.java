package com.secwsystem.ctrl.admin;

import com.secwsystem.app.Admin;
import com.secwsystem.dao.impl.AdminDAO;
import com.secwsystem.dao.impl.CourseDAO;
import com.secwsystem.dao.impl.StudentDAO;
import com.secwsystem.dao.impl.TeacherDAO;
import com.secwsystem.dao.pojo.Course;
import com.secwsystem.dao.pojo.StudentPrivate;
import com.secwsystem.dao.pojo.TeacherPrivate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage;

    @FXML
    private TextField a_main_aid;

    @FXML
    private TextField a_main_name;

    @FXML
    private Button btn_alter;

    @FXML
    private Button btn_course;

    @FXML
    private Button btn_stu;

    @FXML
    private Button btn_tea;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_modify;

    @FXML
    private Button btn_get;

    @FXML
    private Label CourseLabel;

    @FXML
    private Label StuLabel;

    @FXML
    private Label TeaLabel;

    @FXML
    private TableView<Course> CouTable;

    @FXML
    private TableColumn<Course, String> CouId;

    @FXML
    private TableColumn<Course, String> CouName;

    @FXML
    private TableColumn<Course, String> CouType;

    @FXML
    private TableColumn<Course, Integer> CouPeriod;

    @FXML
    private TableColumn<Course, String> CouSchool;

    @FXML
    private TableColumn<Course, String> CouTime;

    @FXML
    private TableColumn<Course, String> CouLocation;

    @FXML
    private TableColumn<Course, Integer> CouCapacity;

    @FXML
    private TableView<TeacherPrivate> TeaTable;

    @FXML
    private TableColumn<TeacherPrivate, String> TeaId;

    @FXML
    private TableColumn<TeacherPrivate, String> TeaName;

    @FXML
    private TableColumn<TeacherPrivate, String> TeaSex;

    @FXML
    private TableColumn<TeacherPrivate, String> TeaSchool;

    @FXML
    private TableColumn<TeacherPrivate, String> TeaTitle;

    @FXML
    private TableColumn<TeacherPrivate, String> TeaAddress;

    @FXML
    private TableColumn<TeacherPrivate, String> TeaEmail;

    @FXML
    private TableView<StudentPrivate> StuTable;

    @FXML
    private TableColumn<StudentPrivate, String> StuId;

    @FXML
    private TableColumn<StudentPrivate, String> StuName;

    @FXML
    private TableColumn<StudentPrivate, String> StuSex;

    @FXML
    private TableColumn<StudentPrivate, String> StuSchool;

    @FXML
    private TableColumn<StudentPrivate, String> StuClass;

    @FXML
    private TableColumn<StudentPrivate, String> StuEmail;

    //这里需要读取个人信息到前面的TextField里
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Context.controllers.put(this.getClass().getSimpleName(), this);
        //初始化页面
        AdminDAO adminDAO = new AdminDAO();
        AdminLoginController controller = (AdminLoginController) Context.controllers.get(AdminLoginController.class.getSimpleName());
        String aid = controller.getaid();
        a_main_aid.setText(aid);
        a_main_name.setText(adminDAO.getPublic(aid).getA_name());
        //

        CourseDAO courseDAO = new CourseDAO();
        List<Course> courseList = courseDAO.getAllCourses();
        if(courseList != null && !courseList.isEmpty()){
            ObservableList<Course> courses = FXCollections.observableArrayList(courseList);
            CouTable.setItems(courses);
            CouId.setCellValueFactory(new PropertyValueFactory<Course,String>("cid"));
            CouName.setCellValueFactory(new PropertyValueFactory<Course,String>("c_name"));
            CouType.setCellValueFactory(new PropertyValueFactory<Course,String>("c_type"));
            CouPeriod.setCellValueFactory(new PropertyValueFactory<Course,Integer>("c_period"));
            CouSchool.setCellValueFactory(new PropertyValueFactory<Course,String>("c_school"));
            CouTime.setCellValueFactory(new PropertyValueFactory<Course,String>("c_time"));
            CouLocation.setCellValueFactory(new PropertyValueFactory<Course,String>("c_location"));
            CouCapacity.setCellValueFactory(new PropertyValueFactory<Course,Integer>("c_capacity"));
        }else{
            CouTable.setItems(FXCollections.emptyObservableList());
        }

        TeacherDAO teacherDAO = new TeacherDAO();
        List<TeacherPrivate> teachersList = teacherDAO.getAll();
        if (teachersList != null && !teachersList.isEmpty()) {
            ObservableList<TeacherPrivate> teachers = FXCollections.observableArrayList(teachersList);
            TeaTable.setItems(teachers);

            TeaId.setCellValueFactory(new PropertyValueFactory<>("tid"));
            TeaName.setCellValueFactory(new PropertyValueFactory<>("t_name"));
            TeaSex.setCellValueFactory(new PropertyValueFactory<>("t_sex"));
            TeaSchool.setCellValueFactory(new PropertyValueFactory<>("t_school"));
            TeaTitle.setCellValueFactory(new PropertyValueFactory<>("t_title"));
            TeaAddress.setCellValueFactory(new PropertyValueFactory<>("t_address"));
            TeaEmail.setCellValueFactory(new PropertyValueFactory<>("t_email"));
        } else {
            TeaTable.setItems(FXCollections.emptyObservableList());
        }

        StudentDAO studentDAO = new StudentDAO();
        List<StudentPrivate> studentList = studentDAO.getAll();
        if (studentList != null && !studentList.isEmpty()) {
            ObservableList<StudentPrivate> students = FXCollections.observableArrayList(studentList);
            StuTable.setItems(students);
            StuId.setCellValueFactory(new PropertyValueFactory<>("sid"));
            StuName.setCellValueFactory(new PropertyValueFactory<>("s_name"));
            StuSex.setCellValueFactory(new PropertyValueFactory<>("s_sex"));
            StuSchool.setCellValueFactory(new PropertyValueFactory<>("s_school"));
            StuClass.setCellValueFactory(new PropertyValueFactory<>("s_class"));
            StuEmail.setCellValueFactory(new PropertyValueFactory<>("s_email"));
        }else{
            StuTable.setItems(FXCollections.emptyObservableList());
        }
    }

    @FXML
    void AlterEvent() throws IOException {
        new Admin().changePassword();
    }

    //初始化所有子界面的可视化状态
    void setVisible() {
        CourseLabel.setVisible(false);
        CouTable.setVisible(false);
        StuLabel.setVisible(false);
        StuTable.setVisible(false);
        TeaLabel.setVisible(false);
        TeaTable.setVisible(false);
        btn_add.setVisible(true);
        btn_delete.setVisible(true);
        btn_modify.setVisible(true);
        btn_get.setVisible(true);
    }

    //以下三个都分别需要提取对应的数据表
    @FXML
    void CourseEvent() {
        setVisible();
        CourseLabel.setVisible(true);
        CouTable.setVisible(true);
        //提取课程的数据库

    }

    @FXML
    void StuEvent() {
        setVisible();
        StuLabel.setVisible(true);
        StuTable.setVisible(true);
        //提取学生的数据库

    }

    @FXML
    void TeaEvent() {
        setVisible();
        TeaTable.setVisible(true);
        TeaLabel.setVisible(true);
        //提取老师的数据库

    }

    @FXML
    void AddEvent() throws IOException {
        if (CouTable.isVisible()) {
            new Admin().addCourse();
        }
        if (TeaTable.isVisible()) {
            new Admin().addTeacher();
        }
        if (StuTable.isVisible()) {
            new Admin().addStudent();
        }
    }

    void AddCourseToTable(Course course){
        CouTable.getItems().add(course);
    }
    void AddTeaToTable(TeacherPrivate teacher){
        TeaTable.getItems().add(teacher);
    }
    void AddStuToTable(StudentPrivate student){
        StuTable.getItems().add(student);
    }

    @FXML
    void DeleteEvent() {
        try {
            if (CouTable.isVisible()) {
                Course course = CouTable.getSelectionModel().getSelectedItem();
                int index = CouTable.getSelectionModel().getSelectedIndex();
                if (index < 0) {
                    throw new Exception.NoSelectionException();
                }
                if (new Admin().showMessage("删除确认", "你确定删除" + course.getC_name() + "吗？", Alert.AlertType.CONFIRMATION, 1)) {
                    if (new CourseDAO().deleteCourse(course)) {
                        CouTable.getItems().remove(index);
                        CouTable.refresh();
                        new Admin().showMessage("删除成功", "删除成功", Alert.AlertType.INFORMATION, 0);
                    } else {
                        throw new Exception.DeleteException();
                    }
                }
            }
            if (TeaTable.isVisible()) {
                TeacherPrivate teacher = TeaTable.getSelectionModel().getSelectedItem();
                int index = TeaTable.getSelectionModel().getSelectedIndex();
                if (index < 0) {
                    throw new Exception.NoSelectionException();
                }
                if (new Admin().showMessage("删除确认", "你确定删除" + teacher.getT_name() + "吗？", Alert.AlertType.CONFIRMATION, 1)) {
                    if (new TeacherDAO().delete(teacher.getTid())) {
                        TeaTable.getItems().remove(index);
                        TeaTable.refresh();
                        new Admin().showMessage("删除成功", "删除成功", Alert.AlertType.INFORMATION, 0);
                    } else {
                        throw new Exception.DeleteException();
                    }
                }
            }
            if (StuTable.isVisible()) {
                StudentPrivate student = StuTable.getSelectionModel().getSelectedItem();
                int index = StuTable.getSelectionModel().getSelectedIndex();
                if (index < 0) {
                    throw new Exception.NoSelectionException();
                }
                if (new Admin().showMessage("删除确认", "你确定删除" + student.getS_name() + "吗？", Alert.AlertType.CONFIRMATION, 1)) {
                    if (new StudentDAO().delete(student.getSid())) {
                        StuTable.getItems().remove(index);
                        StuTable.refresh();
                        new Admin().showMessage("删除成功", "删除成功", Alert.AlertType.INFORMATION, 0);
                    } else {
                        throw new Exception.DeleteException();
                    }
                }
            }
        } catch (Exception.NoSelectionException e) {
            new Admin().showMessage("提示", "请选择要删除的对象", Alert.AlertType.WARNING, 0);
        } catch (Exception.DeleteException e) {
            new Admin().showMessage("删除失败", "删除失败，请重试", Alert.AlertType.ERROR, 0);
        }
    }

    @FXML
    void ModifyEvent() throws IOException {
        if (CouTable.isVisible()) {
            new Admin().modifyCourse();
        }
        if (TeaTable.isVisible()) {
            new Admin().modifyTeacher();
        }
        if (StuTable.isVisible()) {
            new Admin().modifyStudent();
        }
    }

    Course getCourse(){
        return CouTable.getSelectionModel().getSelectedItem();
    }
    TeacherPrivate getTeacher(){
        return TeaTable.getSelectionModel().getSelectedItem();
    }
    StudentPrivate getStudent(){
        return StuTable.getSelectionModel().getSelectedItem();
    }

    void ModifyCourseToTable(Course course){
        CouTable.getItems().set(CouTable.getSelectionModel().getSelectedIndex(),course);
    }
    void ModifyTeaToTable(TeacherPrivate teacher){
        TeaTable.getItems().set(TeaTable.getSelectionModel().getSelectedIndex(),teacher);
    }
    void ModifyStuToTable(StudentPrivate student){
        StuTable.getItems().set(StuTable.getSelectionModel().getSelectedIndex(),student);
    }

    @FXML
    void GetEvent() throws IOException {
        if (CouTable.isVisible()){
            new Admin().getCourse();
        }
        if (TeaTable.isVisible()){
            new Admin().getTeacher();
        }
        if (StuTable.isVisible()){
            new Admin().getStudent();
        }
    }


}
