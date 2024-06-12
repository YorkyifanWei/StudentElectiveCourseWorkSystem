package com.secwsystem.ctrl.admin;

import com.secwsystem.app.Admin;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GetStusFromCouController implements Initializable {

    @FXML
    private Label LabelName;

    @FXML
    private TableView<StudentPrivate> StusFromCouTable;

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

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_get;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Context.controllers.put(this.getClass().getSimpleName(), this);

        MainController controller = (MainController) Context.controllers.get("MainController");
        Course course = controller.getCourse();

        LabelName.setText(course.getC_name()+"下面的学生");

        StudentDAO studentDAO = new StudentDAO();
        if(course.getStudents()!=null && !course.getStudents().isEmpty()){
            ArrayList<StudentPrivate> studentPrivates = new ArrayList<>();
            for(int i=0;i<course.getStudents().size();i++){
                studentPrivates.add(studentDAO.getPrivate(course.getStudents().get(i).getId()));
            }
            ObservableList<StudentPrivate> students = FXCollections.observableArrayList(studentPrivates);
            StusFromCouTable.setItems(students);

            StuId.setCellValueFactory(new PropertyValueFactory<>("sid"));
            StuName.setCellValueFactory(new PropertyValueFactory<>("s_name"));
            StuSex.setCellValueFactory(new PropertyValueFactory<>("s_sex"));
            StuSchool.setCellValueFactory(new PropertyValueFactory<>("s_school"));
            StuClass.setCellValueFactory(new PropertyValueFactory<>("s_class"));
            StuEmail.setCellValueFactory(new PropertyValueFactory<>("s_email"));
        }
    }

    @FXML
    void AddEvent() throws IOException {
        new Admin().AddStuInCou();
    }

    @FXML
    void DeleteEvent() {
        try{
            StudentPrivate studentPrivate = StusFromCouTable.getSelectionModel().getSelectedItem();
            StudentDAO studentDAO = new StudentDAO();
            int index = StusFromCouTable.getSelectionModel().getSelectedIndex();
            if(index < 0){
                throw new Exception.NoSelectionException();
            }
            if(new Admin().showMessage("提示", "是否删除该学生", Alert.AlertType.CONFIRMATION, 1)){
                if(studentDAO.delete(studentPrivate.getSid())){
                    StusFromCouTable.getItems().remove(index);
                    new Admin().showMessage("提示", "删除成功", Alert.AlertType.INFORMATION, 0);
                }else {
                    throw new Exception.DeleteException();
                }
            }
        } catch (Exception.NoSelectionException e) {
            new Admin().showMessage("提示", "请选择要删除的学生", Alert.AlertType.WARNING, 0);
        } catch (Exception.DeleteException e) {
            new Admin().showMessage("删除异常", "删除失败,请重新删除", Alert.AlertType.ERROR, 0);
        }
    }

    @FXML
    void GetStuFromCouEvent() throws IOException {
        new Admin().GetStuFromCou();
    }

    StudentPrivate getStuFromCou(){
        return StusFromCouTable.getSelectionModel().getSelectedItem();
    }
    void AddStuToTable(StudentPrivate student){
        StusFromCouTable.getItems().add(student);
    }
}
