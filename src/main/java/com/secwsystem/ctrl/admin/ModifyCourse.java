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

public class ModifyCourse implements Initializable {

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
        AdminMainController controller = (AdminMainController) AdminContext.controllers.get(AdminMainController.class.getSimpleName());
        Course course = controller.getCourse();
        CouId.setText(course.getCid());
        CouName.setText(course.getCName());
        CouType.setText(course.getCType());
        CouInfo.setText(course.getCInfo());
        CouTime.setText(course.getCTime());
        CouSchool.setText(course.getCSchool());
        CouPeriod.setText(course.getCPeriod());
        CouCapacity.setText(course.getCCapacity());
        CouInit.setText(course.getCInti());
        CouEnd.setText(course.getCEnd());
        CouLocation.setText(course.getCLocation());
    }

    @FXML
    void CancelEvent() {
        stage.close();
    }

    @FXML
    void SetEvent() {
        try{
            AdminMainController controller = (AdminMainController) AdminContext.controllers.get(AdminMainController.class.getSimpleName());
            Course course = controller.getCourse();
            CourseDAO courseDAO = new CourseDAO();
            if(TextFieldIsNull()){
                throw new AdminException.TextFieldNullException();
            }
            course.setCid(CouId.getText().trim());
            course.setCName(CouName.getText().trim());
            course.setCType(CouType.getText().trim());
            course.setCInfo(CouInfo.getText().trim());
            course.setCTime(CouTime.getText().trim());
            course.setCSchool(CouSchool.getText().trim());
            course.setCPeriod(CouPeriod.getText().trim());
            course.setCCapacity(CouCapacity.getText().trim());
            course.setCInit(CouInit.getText().trim());
            course.setCEnd(CouEnd.getText().trim());
            course.setCLocation(CouLocation.getText().trim());

            if(courseDAO.updateCourse(course)){
                controller.ModifyCourseToTable(course);
                new Admin().showMessage("提示", "修改成功", Alert.AlertType.INFORMATION, 0);
                stage.close();
            }else{
                throw new AdminException.ModifyException();
            }
        } catch (AdminException.TextFieldNullException e) {
            new Admin().showMessage("修改失败", "修改不能为空", Alert.AlertType.ERROR, 0);
        } catch (AdminException.ModifyException e) {
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

