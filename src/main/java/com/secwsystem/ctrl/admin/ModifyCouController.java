package com.secwsystem.ctrl.admin;

import com.secwsystem.dao.impl.CourseDAO;
import com.secwsystem.dao.pojo.Course;
import com.secwsystem.app.Admin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCouController implements Initializable {

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
    private TextField CouType;

    @FXML
    private TextArea CouInfo;

    @FXML
    private TextField CouTime;

    @FXML
    private TextField CouSchool;

    @FXML
    private TextField CouPeriod;

    @FXML
    private TextField CouCapacity;

    @FXML
    private Button btn_set;

    @FXML
    private Button btn_cancel;

    @FXML
    private TextField CouInit;

    @FXML
    private TextField CouEnd;

    @FXML
    private TextField CouLocation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainController controller = (MainController) Context.controllers.get(MainController.class.getSimpleName());
        Course course = controller.getCourse();
        CouId.setText(course.getCid());
        CouName.setText(course.getC_name());
        CouType.setText(course.getC_type());
        CouInfo.setText(course.getC_info());
        CouTime.setText(course.getC_time());
        CouSchool.setText(course.getC_school());
        CouPeriod.setText(course.getC_period());
        CouCapacity.setText(course.getC_capacity());
        CouInit.setText(course.getC_init());
        CouEnd.setText(course.getC_end());
        CouLocation.setText(course.getC_location());
    }

    @FXML
    void CancelEvent() {
        stage.close();
    }

    @FXML
    void SetEvent() {
        try{
            MainController controller = (MainController) Context.controllers.get(MainController.class.getSimpleName());
            Course course = controller.getCourse();
            CourseDAO courseDAO = new CourseDAO();
            if(TextFieldIsNull()){
                throw new Exception.TextFieldNullException();
            }
            course.setCid(CouId.getText().trim());
            course.setC_name(CouName.getText().trim());
            course.setC_type(CouType.getText().trim());
            course.setC_info(CouInfo.getText().trim());
            course.setC_time(CouTime.getText().trim());
            course.setC_school(CouSchool.getText().trim());
            course.setC_period(CouPeriod.getText().trim());
            course.setC_capacity(CouCapacity.getText().trim());
            course.setC_init(CouInit.getText().trim());
            course.setC_end(CouEnd.getText().trim());
            course.setC_location(CouLocation.getText().trim());

            if(courseDAO.updateCourse(course)){
                controller.ModifyCourseToTable(course);
                new Admin().showMessage("提示", "修改成功", Alert.AlertType.INFORMATION, 0);
                stage.close();
            }else{
                throw new Exception.ModifyException();
            }
        } catch (Exception.TextFieldNullException e) {
            new Admin().showMessage("修改失败", "修改不能为空", Alert.AlertType.ERROR, 0);
        } catch (Exception.ModifyException e) {
            new Admin().showMessage("修改异常", "修改失败,请重新修改", Alert.AlertType.ERROR, 0);
        }

    }
    boolean TextFieldIsNull(){
        return CouId.getText().trim().isEmpty() || CouName.getText().trim().isEmpty()
                || CouType.getText().trim().isEmpty() || CouInfo.getText().trim().isEmpty() || CouTime.getText().trim().isEmpty()
                || CouLocation.getText().trim().isEmpty() || CouInit.getText().trim().isEmpty()
                || CouEnd.getText().trim().isEmpty() || CouSchool.getText().trim().isEmpty()
                || CouPeriod.getText().trim().isEmpty() || CouCapacity.getText().trim().isEmpty();
    }
}

