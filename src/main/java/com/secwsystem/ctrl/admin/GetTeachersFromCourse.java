package com.secwsystem.ctrl.admin;

import com.secwsystem.app.AdminApplication;
import com.secwsystem.dao.impl.TeacherDAO;
import com.secwsystem.dao.pojo.Course;
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

public class GetTeachersFromCourse implements Initializable {

    @FXML
    private TableView<TeacherPrivate> TeasFromCouTable;

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
    private Label LabelName;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_get;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AdminContext.controllers.put(this.getClass().getSimpleName(), this);

        AdminMainController controller = (AdminMainController) AdminContext.controllers.get("MainController");
        Course course = controller.getCourse();

        LabelName.setText(course.getCName()+"下面的讲师");

        TeacherDAO teacherDAO = new TeacherDAO();
        if(course.getTeachers() != null && !course.getTeachers().isEmpty()){
            ArrayList<TeacherPrivate> teacherPrivates = new ArrayList<>();
            for(int i = 0;i < course.getTeachers().size();i++){
                teacherPrivates.add(teacherDAO.getPrivate(course.getTeachers().get(i).getId()));
            }
            ObservableList<TeacherPrivate> teachers = FXCollections.observableArrayList(teacherPrivates);
            TeasFromCouTable.setItems(teachers);

            TeaId.setCellValueFactory(new PropertyValueFactory<>("tid"));
            TeaName.setCellValueFactory(new PropertyValueFactory<>("t_name"));
            TeaSex.setCellValueFactory(new PropertyValueFactory<>("t_sex"));
            TeaSchool.setCellValueFactory(new PropertyValueFactory<>("t_school"));
            TeaTitle.setCellValueFactory(new PropertyValueFactory<>("t_title"));
            TeaAddress.setCellValueFactory(new PropertyValueFactory<>("t_address"));
            TeaEmail.setCellValueFactory(new PropertyValueFactory<>("t_email"));
        } else {
            TeasFromCouTable.setItems(FXCollections.emptyObservableList());
        }
    }

    @FXML
    void AddEvent() throws IOException {
        new AdminApplication().addTeacherIntoCourse();
    }

    @FXML
    void DeleteEvent() {
        try{
            TeacherPrivate teacher = TeasFromCouTable.getSelectionModel().getSelectedItem();
            TeacherDAO teacherDAO = new TeacherDAO();
            int index = TeasFromCouTable.getSelectionModel().getSelectedIndex();
            if(index < 0){
                throw new AdminException.NoSelectionException();
            }
            if(new AdminApplication().showMessage("提示", "是否删除该教师", Alert.AlertType.CONFIRMATION, 1)){
                if(teacherDAO.delete(teacher.getTid())){
                    TeasFromCouTable.getItems().remove(index);
                    new AdminApplication().showMessage("提示", "删除成功", Alert.AlertType.INFORMATION, 0);
                }else {
                    throw new AdminException.DeleteException();
                }
            }
        } catch (AdminException.NoSelectionException e) {
            new AdminApplication().showMessage("提示", "请选择要删除的教师", Alert.AlertType.WARNING, 0);
        } catch (AdminException.DeleteException e) {
            new AdminApplication().showMessage("删除异常", "删除失败,请重新删除", Alert.AlertType.ERROR, 0);
        }
    }

    @FXML
    void GetTeaFromCouEvent() throws IOException {
        new AdminApplication().getTeacherFromCourse();
    }

    TeacherPrivate getTeaFromCou(){
        return TeasFromCouTable.getSelectionModel().getSelectedItem();
    }
    void AddTeaToTable(TeacherPrivate teacher){
        TeasFromCouTable.getItems().add(teacher);
    }
}
