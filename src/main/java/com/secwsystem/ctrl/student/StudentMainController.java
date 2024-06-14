package com.secwsystem.ctrl.student;

import com.secwsystem.app.Student;
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

public class StudentMainController implements Initializable {

    @FXML
    private ListView<?> CourseList;

    @FXML
    private TextField a_main_classandcollege;

    @FXML
    private TextField a_main_email;

    @FXML
    private TextField a_main_idcard;

    @FXML
    private TextField a_main_name;

    @FXML
    private TextField a_main_phonenumber;

    @FXML
    private TextField a_main_sex;

    @FXML
    private TextField a_main_sid;

    @FXML
    private TextField a_main_password;

    @FXML
    private Button btn_alter;

    @FXML
    private Button btn_cancel;

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
    private TableColumn<Course, String> col_cid1;

    @FXML
    private TableColumn<Course, String> col_cid11;

    @FXML
    private TableColumn<Course, String> col_id1;

    @FXML
    private TableColumn<Course, String> col_id11;

    @FXML
    private TableColumn<Course, String> col_c_type1;

    @FXML
    private TableColumn<Course, String> col_c_type11;

    @FXML
    private TableView<Course> tableview1;

    @FXML
    private TableView<Course> tableview11;

    @FXML
    private Button drop_course;

    @FXML
    private Button choose_course;

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
    void change_email_event() throws IOException{
        new Student().ChangeEmail();
    }

    @FXML
    void change_password_event() throws IOException {
        new Student().ChangePassword();

    }

    @FXML
    void change_phonenumber_event() throws IOException{
        new Student().ChangePhonenumber();
    }

    @FXML
    void choose_course_event(ActionEvent event) {
        Course course = tableview1.getSelectionModel().getSelectedItem();
        int index = tableview1.getSelectionModel().getSelectedIndex();
        if(index < 0){
            new Student().showMessage("提示", "请选择课程", Alert.AlertType.WARNING, 0);
        }
        if(course.addStudent(a_main_sid.getText())){
            new Student().showMessage("提示", "选课成功", Alert.AlertType.INFORMATION, 0);
            tableview11.getItems().add(course);
            tableview1.refresh();
            new CourseDAO().addStudent(course.getCid(), a_main_sid.getText().trim());
        }else{
            new Student().showMessage("提示", "选课失败", Alert.AlertType.WARNING, 0);
        }

    }

    @FXML
    void drop_course_event(ActionEvent event) {
        Course course = tableview11.getSelectionModel().getSelectedItem();
        int index = tableview11.getSelectionModel().getSelectedIndex();
        if(index < 0){
            new Student().showMessage("提示", "请选择课程", Alert.AlertType.WARNING, 0);
        }
        if(course.deleteStudent(a_main_sid.getText())){
            new Student().showMessage("提示", "退课成功", Alert.AlertType.INFORMATION, 0);
            tableview11.getItems().remove(index);
            new CourseDAO().deleteStudent(course.getCid(), a_main_sid.getText().trim());
            tableview1.refresh();
            tableview11.refresh();
        }else{
            new Student().showMessage("提示", "退课失败", Alert.AlertType.WARNING, 0);
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StudentContext.controllers.put(this.getClass().getSimpleName(), this);
        //初始化页面
        StudentDAO studentDAO = new StudentDAO();
        StudentLogin controller = (StudentLogin) StudentContext.controllers.get(StudentLogin.class.getSimpleName());
        String sid = controller.getsid();
        a_main_sid.setText(sid);
        a_main_name.setText(studentDAO.getPublic(sid).getSName());
        a_main_classandcollege.setText(studentDAO.getPublic(sid).getSSchool()+studentDAO.getPublic(sid).getSClass());
        a_main_sex.setText(studentDAO.getPublic(sid).getSSex());
        a_main_phonenumber.setText(studentDAO.getPublic(sid).getSPhoneNumber());
        a_main_email.setText(studentDAO.getPublic(sid).getSEmail());
        a_main_idcard.setText(studentDAO.getPrivate(sid).getSIdcard());


//以下是查询所有课程
        CourseDAO courseDAO = new CourseDAO();
        ArrayList<Course> all_list1 = courseDAO.getAll();
        ObservableList<Course> all_list2 = FXCollections.observableArrayList();
        all_list2.addAll(all_list1);
        tableview1.setItems(all_list2);
        col_id1.setCellValueFactory(new PropertyValueFactory<Course,String>("id"));
        col_cid1.setCellValueFactory(new PropertyValueFactory<Course,String>("cid"));
        col_c_name1.setCellValueFactory(new PropertyValueFactory<Course,String>("c_name"));
        col_c_info1.setCellValueFactory(new PropertyValueFactory<Course,String>("c_info"));
        col_c_time1.setCellValueFactory(new PropertyValueFactory<Course,String>("c_time"));
        col_c_init1.setCellValueFactory(new PropertyValueFactory<Course,String>("c_init"));
        col_c_end1.setCellValueFactory(new PropertyValueFactory<Course,String>("c_end"));
        col_c_location1.setCellValueFactory(new PropertyValueFactory<Course,String>("c_location"));
        col_c_type1.setCellValueFactory(new PropertyValueFactory<Course,String>("c_type"));
        col_c_school1.setCellValueFactory(new PropertyValueFactory<Course,String>("c_school"));
        col_c_period1.setCellValueFactory(new PropertyValueFactory<Course,String>("c_period"));
        col_c_capacity1.setCellValueFactory(new PropertyValueFactory<Course,String>("c_capacity"));
        col_c_current1.setCellValueFactory(new PropertyValueFactory<Course,String>("c_current"));

//以下是查询所选课程
        ArrayList<Course> all_list3 = courseDAO.getCoursesFromStudent(sid);
        ObservableList<Course> all_list4 = FXCollections.observableArrayList();
        all_list4.addAll(all_list3);
        tableview11.setItems(all_list4);
        col_id11.setCellValueFactory(new PropertyValueFactory<Course,String>("id"));
        col_cid11.setCellValueFactory(new PropertyValueFactory<Course,String>("cid"));
        col_c_name11.setCellValueFactory(new PropertyValueFactory<Course,String>("c_name"));
        col_c_info11.setCellValueFactory(new PropertyValueFactory<Course,String>("c_info"));
        col_c_time11.setCellValueFactory(new PropertyValueFactory<Course,String>("c_time"));
        col_c_init11.setCellValueFactory(new PropertyValueFactory<Course,String>("c_init"));
        col_c_end11.setCellValueFactory(new PropertyValueFactory<Course,String>("c_end"));
        col_c_location11.setCellValueFactory(new PropertyValueFactory<Course,String>("c_location"));
        col_c_type11.setCellValueFactory(new PropertyValueFactory<Course,String>("c_type"));
        col_c_school11.setCellValueFactory(new PropertyValueFactory<Course,String>("c_school"));
        col_c_period11.setCellValueFactory(new PropertyValueFactory<Course,String>("c_period"));
        col_c_capacity11.setCellValueFactory(new PropertyValueFactory<Course,String>("c_capacity"));
        col_c_current11.setCellValueFactory(new PropertyValueFactory<Course,String>("c_current"));


    }
}
