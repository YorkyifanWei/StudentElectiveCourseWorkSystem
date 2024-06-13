package com.secwsystem.ctrl.teacher;

import com.secwsystem.app.Teacher;
import com.secwsystem.dao.impl.CourseDAO;
import com.secwsystem.dao.pojo.Course;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCourseController {

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage;

    @FXML
    private TextField CouId;

    @FXML
    private TextField CouName;

    @FXML
    private TextField CouTea;

    @FXML
    private TextField CouType;

    @FXML
    private TextArea CouInfo;

    @FXML
    private TextField CouTime;

    @FXML
    private TextField CouLocation;

    @FXML
    private TextField CouSchool;

    @FXML
    private TextField CouInit;

    @FXML
    private TextField CouEnd;

    @FXML
    private TextField CouPeriod;

    @FXML
    private TextField CouCapacity;

    @FXML
    private Button btn_set;

    @FXML
    private Button btn_cancel;

    @FXML
    void GoToTeaEvent() {
        //待定
    }

    @FXML
    void SetEvent(){
        //把所有输入信息上传到数据库
        //我不知道后端数据格式，暂时没做防恶意输入，麻烦后端人员告知
        try{
            Course course = new Course();
            CourseDAO courseDAO = new CourseDAO();
            //判断是否有内容为空，有则执行if
            if(TextFieldIsNull()){
                throw new com.secwsystem.ctrl.teacher.Exception.TextFieldIsNullException();
            }
            if(courseDAO.getCourse(CouId.getText().trim()) != null){
                throw new Exception.CourseExistException();
            }
            course.setCid(CouId.getText().trim());
            course.setC_name(CouName.getText().trim());
            course.setC_info(CouInfo.getText().trim());
            course.setC_time(CouTime.getText().trim());
            course.setC_init(CouInit.getText().trim());
            course.setC_end(CouEnd.getText().trim());
            course.setC_location(CouLocation.getText().trim());
            course.setC_type(CouType.getText().trim());
            course.setC_school(CouSchool.getText().trim());
            course.setC_period(CouPeriod.getText().trim());
            course.setC_capacity(CouCapacity.getText().trim());
            course.setC_current("0");
            course.addStudent(null);
            new Teacher().showMessage("提示","课程创建成功！", Alert.AlertType.INFORMATION,0);
            stage.close();
        } catch (Exception.TextFieldIsNullException e) {
            new Teacher().showMessage("提示","请检查输入信息是否为空！", Alert.AlertType.INFORMATION,0);
        } catch (Exception.CourseExistException e) {
            new Teacher().showMessage("提示","课程已存在！", Alert.AlertType.INFORMATION,0);
        }
    }



    @FXML
    void CancelEvent(){
        stage.close();
    }

    boolean TextFieldIsNull(){
        return CouId.getText().trim().isEmpty() || CouName.getText().trim().isEmpty()
                || CouType.getText().trim().isEmpty()
                || CouInfo.getText().trim().isEmpty() || CouTime.getText().trim().isEmpty()
                || CouLocation.getText().trim().isEmpty() || CouInit.getText().trim().isEmpty()
                || CouEnd.getText().trim().isEmpty() || CouSchool.getText().trim().isEmpty()
                || CouPeriod.getText().trim().isEmpty() || CouCapacity.getText().trim().isEmpty();
    }
}
