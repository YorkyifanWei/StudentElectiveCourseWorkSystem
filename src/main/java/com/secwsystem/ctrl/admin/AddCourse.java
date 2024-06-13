package com.secwsystem.ctrl.admin;

import com.secwsystem.app.Admin;
import com.secwsystem.dao.impl.CourseDAO;
import com.secwsystem.dao.pojo.Course;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddCourse {

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
    private TextField CouTeaId;

    @FXML
    private TextField CouType;

    @FXML
    private TextArea CouInfo;

    @FXML
    private TextField CouTime;

    @FXML
    private TextField CouLocation;

    @FXML
    private TextField CouInit;

    @FXML
    private TextField CouEnd;

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
    void SetEvent(){
        //把所有输入信息上传到数据库
        //我不知道后端数据格式，暂时没做防恶意输入，麻烦后端人员告知
        try{
            Course course = new Course();
            CourseDAO courseDAO = new CourseDAO();
            //判断是否有内容为空，有则执行if
            if(TextFieldIsNull()){
                throw new AdminException.TextFieldNullException();
            }
            if(courseDAO.getCourse(CouId.getText().trim()) != null){
                throw new AdminException.CourseExistException();
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
            course.addTeacher(CouTeaId.getText().trim());
            course.addStudent(null);
            if(courseDAO.addCourse(course)){
                AdminMainController controller = (AdminMainController) AdminContext.controllers.get(AdminMainController.class.getSimpleName());
                controller.AddCourseToTable(course);
                //弹出对话框，提示课程创建成功，关闭界面
                new Admin().showMessage("创建成功","课程创建成功！", Alert.AlertType.INFORMATION,0);
                stage.close();
            }else {
                throw new AdminException.AddException();
            }
        } catch (AdminException.TextFieldNullException e) {
            new Admin().showMessage("创建失败","请检查输入信息是否为空！", Alert.AlertType.ERROR,0);
        } catch (AdminException.CourseExistException e) {
            new Admin().showMessage("创建失败","该课程已存在！", Alert.AlertType.ERROR,0);
        } catch (AdminException.AddException e) {
            new Admin().showMessage("创建异常","课程创建失败,请重新尝试！", Alert.AlertType.ERROR,0);
        }
    }

    @FXML
    void CancelEvent(){
        stage.close();
    }

    boolean TextFieldIsNull(){
        return CouId.getText().trim().isEmpty() || CouName.getText().trim().isEmpty()
                || CouTeaId.getText().trim().isEmpty() || CouType.getText().trim().isEmpty()
                || CouInfo.getText().trim().isEmpty() || CouTime.getText().trim().isEmpty()
                || CouLocation.getText().trim().isEmpty() || CouInit.getText().trim().isEmpty()
                || CouEnd.getText().trim().isEmpty() || CouSchool.getText().trim().isEmpty()
                || CouPeriod.getText().trim().isEmpty() || CouCapacity.getText().trim().isEmpty();
    }
}
