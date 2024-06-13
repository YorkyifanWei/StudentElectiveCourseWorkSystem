package com.secwsystem.ctrl.admin;

import com.secwsystem.app.Admin;
import com.secwsystem.dao.impl.CourseDAO;
import com.secwsystem.dao.pojo.Course;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * 控制器类，用于添加课程的界面操作。
 */
public class AddCourse {

    /**
     * 获取当前窗口的Stage对象。
     * 
     * @return 返回当前窗口的Stage对象，用于对窗口进行进一步的操作或配置。
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * 设置当前的舞台对象。
     * 
     * 本方法用于将传入的舞台对象赋值给类内的stage属性。舞台（Stage）在图形用户界面中扮演着重要角色，
     * 它是顶级窗口，用于展示应用的主窗口或对话框等。通过设置不同的舞台，可以实现窗口状态的切换或管理，
     * 如设置窗口大小、位置、标题等。
     * 
     * @param stage 要设置的舞台对象，代表当前的应用窗口或对话框。
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage; // 主窗口舞台

    @FXML
    private TextField cid; // 课程ID输入框

    @FXML
    private TextField cName; // 课程名称输入框

    @FXML
    private TextField c_tid; // 教师ID输入框

    @FXML
    private TextField cType; // 课程类型输入框

    @FXML
    private TextArea cInfo; // 课程介绍文本区

    @FXML
    private TextField cTime; // 课程时间输入框

    @FXML
    private TextField cLocation; // 课程地点输入框

    @FXML
    private TextField cInit; // 课程开始时间输入框

    @FXML
    private TextField cEnd; // 课程结束时间输入框

    @FXML
    private TextField cSchool; // 课程所属学校输入框

    @FXML
    private TextField cPeriod; // 课程周期输入框

    @FXML
    private TextField cCapacity; // 课程容量输入框

    @FXML
    private Button buttonSet; // 确定按钮

    @FXML
    private Button buttonCancel; // 取消按钮

    /**
     * 点击确定按钮时触发的事件。
     * 用于检查输入信息，如果信息完整且课程不存在，则添加课程到数据库，
     * 并关闭当前窗口。
     */
    @FXML
    void setEvent(){
        try{
            // 创建课程对象并设置属性
            Course course = new Course();
            CourseDAO courseDAO = new CourseDAO();
            if(textFieldIsNull()){
                throw new AdminException.TextFieldNullException();
            }
            if(courseDAO.getCourse(cid.getText().trim()) != null){
                throw new AdminException.CourseExistException();
            }
            course.setCid(cid.getText().trim());
            course.setC_name(cName.getText().trim());
            course.setC_info(cInfo.getText().trim());
            course.setC_time(cTime.getText().trim());
            course.setC_init(cInit.getText().trim());
            course.setC_end(cEnd.getText().trim());
            course.setC_location(cLocation.getText().trim());
            course.setC_type(cType.getText().trim());
            course.setC_school(cSchool.getText().trim());
            course.setC_period(cPeriod.getText().trim());
            course.setC_capacity(cCapacity.getText().trim());
            course.setC_current("0");
            course.addTeacher(c_tid.getText().trim());
            course.addStudent(null);

            // 将课程添加到数据库
            if(courseDAO.addCourse(course)){
                AdminMainController controller = (AdminMainController) AdminContext.controllers.get(AdminMainController.class.getSimpleName());
                controller.AddCourseToTable(course);
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

    /**
     * 点击取消按钮时触发的事件。
     * 关闭当前窗口。
     */
    @FXML
    void cancelEvent(){
        stage.close();
    }

    /**
     * 检查所有文本字段是否为空。
     * @return 如果有任何文本字段为空，则返回true；否则返回false。
     */
    boolean textFieldIsNull(){
        return cid.getText().trim().isEmpty() || cName.getText().trim().isEmpty()
                || c_tid.getText().trim().isEmpty() || cType.getText().trim().isEmpty()
                || cInfo.getText().trim().isEmpty() || cTime.getText().trim().isEmpty()
                || cLocation.getText().trim().isEmpty() || cInit.getText().trim().isEmpty()
                || cEnd.getText().trim().isEmpty() || cSchool.getText().trim().isEmpty()
                || cPeriod.getText().trim().isEmpty() || cCapacity.getText().trim().isEmpty();
    }
}