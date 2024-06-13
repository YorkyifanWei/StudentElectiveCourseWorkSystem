package com.secwsystem.ctrl.admin;

import com.secwsystem.app.Admin;
import com.secwsystem.dao.impl.CourseDAO;
import com.secwsystem.dao.impl.TeacherDAO;
import com.secwsystem.dao.pojo.Course;
import com.secwsystem.dao.pojo.TeacherPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTeacherIntoCourse {

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage;

    @FXML
    private TextField a_TeaInCou;

    @FXML
    private Button btn_set;

    @FXML
    private Button btn_cancel;

    @FXML
    void CancelEvent() {
        stage.close();
    }

    @FXML
    void SetEvent() {
        try{
            AdminMainController maincontroller = (AdminMainController) AdminContext.controllers.get(AdminMainController.class.getSimpleName());
            Course course = maincontroller.getCourse();
            CourseDAO courseDAO = new CourseDAO();
            TeacherDAO teacherDAO = new TeacherDAO();
            if(a_TeaInCou.getText().trim().isEmpty()){
                throw new AdminException.TextFieldNullException();
            }
            if(teacherDAO.getPrivate(a_TeaInCou.getText().trim()) == null){
                throw new AdminException.TeacherNotExistException();
            }
            if(courseDAO.addTeacher(course.getCid(), a_TeaInCou.getText().trim())){
                GetTeachersFromCourse getTeasFromCouController = (GetTeachersFromCourse) AdminContext.controllers.get("GetTeasFromCouController");
                TeacherPrivate teacher = teacherDAO.getPrivate(a_TeaInCou.getText().trim());
                getTeasFromCouController.AddTeaToTable(teacher);
                new Admin().showMessage("添加成功", "添加成功", Alert.AlertType.INFORMATION, 0);
                stage.close();
            }else{
                throw new AdminException.AddException();
            }
        } catch (AdminException.TextFieldNullException e) {
            new Admin().showMessage("添加失败", "教师账号名不可为空", Alert.AlertType.ERROR, 0);
        } catch (AdminException.TeacherNotExistException e) {
            new Admin().showMessage("添加失败", "教师不存在", Alert.AlertType.ERROR, 0);
        }catch(AdminException.AddException e){
            new Admin().showMessage("添加失败", "添加失败,请重新添加", Alert.AlertType.ERROR, 0);
        }

    }

}
