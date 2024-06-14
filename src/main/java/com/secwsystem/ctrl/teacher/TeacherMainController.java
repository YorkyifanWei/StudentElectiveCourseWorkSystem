package com.secwsystem.ctrl.teacher;

import com.secwsystem.app.Teacher;
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
    private ListView<?> CourseList;

    @FXML
    private TextField a_main_address;

    @FXML
    private TextField a_main_email;

    @FXML
    private TextField a_main_idcard;

    @FXML
    private TextField a_main_name;

    @FXML
    private TextField a_main_school;

    @FXML
    private TextField a_main_sex;

    @FXML
    private TextField a_main_tid;

    @FXML
    private TextField a_main_title;
    @FXML
    private TextField a_main_phonenumber;


    @FXML
    private Button btn_alter;

    @FXML
    private Button btn_cancel;

    @FXML
    private MenuItem change_address;

    @FXML
    private MenuItem change_email;

    @FXML
    private MenuItem change_password;

    @FXML
    private MenuItem change_phonenumber;

    @FXML
    private TableColumn<Course, String> col_c_capacity1;

    @FXML
    private TableColumn<Course, String> col_c_capacity11;

    @FXML
    private TableColumn<Course, String> col_c_current1;

    @FXML
    private TableColumn<Course, String> col_c_current11;

    @FXML
    private TableColumn<Course, String> col_c_end1;

    @FXML
    private TableColumn<Course, String> col_c_end11;

    @FXML
    private TableColumn<Course, String> col_c_info1;

    @FXML
    private TableColumn<Course, String> col_c_info11;

    @FXML
    private TableColumn<Course, String> col_c_init1;

    @FXML
    private TableColumn<Course, String> col_c_init11;

    @FXML
    private TableColumn<Course, String> col_c_location1;

    @FXML
    private TableColumn<Course, String> col_c_location11;

    @FXML
    private TableColumn<Course, String> col_c_name1;

    @FXML
    private TableColumn<Course, String> col_c_name11;

    @FXML
    private TableColumn<Course, String> col_c_period1;

    @FXML
    private TableColumn<Course, String> col_c_period11;

    @FXML
    private TableColumn<Course, String> col_c_school1;

    @FXML
    private TableColumn<Course, String> col_c_school11;

    @FXML
    private TableColumn<Course, String> col_c_time1;

    @FXML
    private TableColumn<Course, String> col_c_time11;

    @FXML
    private TableColumn<Course, String> col_c_type1;

    @FXML
    private TableColumn<Course, String> col_c_type11;

    @FXML
    private TableColumn<Course, String> col_cid1;

    @FXML
    private TableColumn<Course, String> col_cid11;

    @FXML
    private TableColumn<Course, String> col_id1;

    @FXML
    private TableColumn<Course, String> col_id11;

    @FXML
    private TableView<Course> tableview1;

    @FXML
    private TableView<Course> tableview11;

    @FXML
    private Button teacher_add_course;

    @FXML
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void AlterEvent(ActionEvent event) {

    }

    @FXML
    void CancelEvent(ActionEvent event) {

    }

    @FXML
    void change_email_event() throws IOException {
        new Teacher().ChangeEmail();
    }

    @FXML
    void change_password_event() throws IOException {
        new Teacher().ChangePassword();

    }


    @FXML
    void change_phonenumber_event() throws IOException {
        new Teacher().ChangePhonenumber();
    }

    @FXML
    public void change_address_event() throws IOException {
        new Teacher().ChangeAddress();
    }

    public void teacher_add_course_event() throws IOException {
        new Teacher().AddCourse();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        com.secwsystem.ctrl.teacher.TeacherContext.controllers.put(this.getClass().getSimpleName(), this);
        //初始化页面
        TeacherDAO teacherDAO = new TeacherDAO();
        TeacherLogin controller = (TeacherLogin) TeacherContext.controllers.get(TeacherLogin.class.getSimpleName());
        String tid = controller.gettid();
        a_main_tid.setText(tid);
        a_main_name.setText(teacherDAO.getPublic(tid).getTName());
        a_main_title.setText(teacherDAO.getPublic(tid).getTTitle());
        a_main_school.setText(teacherDAO.getPublic(tid).getTSchool());
        a_main_sex.setText(teacherDAO.getPublic(tid).getTSex());
        a_main_phonenumber.setText(teacherDAO.getPublic(tid).getTPhoneNumber());
        a_main_email.setText(teacherDAO.getPublic(tid).getTEmail());
        a_main_address.setText(teacherDAO.getPublic(tid).getTAddress());
        a_main_idcard.setText(teacherDAO.getPrivate(tid).getTIdcard());


        CourseDAO courseDAO = new CourseDAO();

        ObservableList<Course> all_list1 = FXCollections.observableArrayList(courseDAO.getAll());

        tableview1.setItems(all_list1);
        col_id1.setCellValueFactory(new PropertyValueFactory<Course, String>("id"));
        col_cid1.setCellValueFactory(new PropertyValueFactory<Course, String>("cid"));
        col_c_name1.setCellValueFactory(new PropertyValueFactory<Course, String>("c_name"));
        col_c_info1.setCellValueFactory(new PropertyValueFactory<Course, String>("c_info"));
        col_c_time1.setCellValueFactory(new PropertyValueFactory<Course, String>("c_time"));
        col_c_init1.setCellValueFactory(new PropertyValueFactory<Course, String>("c_init"));
        col_c_end1.setCellValueFactory(new PropertyValueFactory<Course, String>("c_end"));
        col_c_location1.setCellValueFactory(new PropertyValueFactory<Course, String>("c_location"));
        col_c_type1.setCellValueFactory(new PropertyValueFactory<Course, String>("c_type"));
        col_c_school1.setCellValueFactory(new PropertyValueFactory<Course, String>("c_school"));
        col_c_period1.setCellValueFactory(new PropertyValueFactory<Course, String>("c_period"));
        col_c_capacity1.setCellValueFactory(new PropertyValueFactory<Course, String>("c_capacity"));
        col_c_current1.setCellValueFactory(new PropertyValueFactory<Course, String>("c_current"));

        ObservableList<Course> all_list2 = FXCollections.observableArrayList(courseDAO.getCoursesFromTeacher(tid));
        tableview11.setItems(all_list2);
        col_id11.setCellValueFactory(new PropertyValueFactory<Course, String>("id"));
        col_cid11.setCellValueFactory(new PropertyValueFactory<Course, String>("cid"));
        col_c_name11.setCellValueFactory(new PropertyValueFactory<Course, String>("c_name"));
        col_c_info11.setCellValueFactory(new PropertyValueFactory<Course, String>("c_info"));
        col_c_time11.setCellValueFactory(new PropertyValueFactory<Course, String>("c_time"));
        col_c_init11.setCellValueFactory(new PropertyValueFactory<Course, String>("c_init"));
        col_c_end11.setCellValueFactory(new PropertyValueFactory<Course, String>("c_end"));
        col_c_location11.setCellValueFactory(new PropertyValueFactory<Course, String>("c_location"));
        col_c_type11.setCellValueFactory(new PropertyValueFactory<Course, String>("c_type"));
        col_c_school11.setCellValueFactory(new PropertyValueFactory<Course, String>("c_school"));
        col_c_period11.setCellValueFactory(new PropertyValueFactory<Course, String>("c_period"));
        col_c_capacity11.setCellValueFactory(new PropertyValueFactory<Course, String>("c_capacity"));
        col_c_current11.setCellValueFactory(new PropertyValueFactory<Course, String>("c_current"));

    }
}
