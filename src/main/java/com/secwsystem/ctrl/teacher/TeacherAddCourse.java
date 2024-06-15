package com.secwsystem.ctrl.teacher;

import com.secwsystem.app.TeacherApplication;
import com.secwsystem.dao.impl.CourseDAO;
import com.secwsystem.dao.pojo.Course;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 教师添加课程的控制器类。
 * 该类通过FXML注解与UI元素绑定，负责处理添加课程的相关逻辑。
 */
public class TeacherAddCourse {

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
    private Stage stage; // 主舞台

    @FXML
    private TextField cid; // 课程ID输入框

    @FXML
    private TextField cName; // 课程名称输入框

    @FXML
    private TextField cType; // 课程类型输入框

    @FXML
    private TextArea cInfo; // 课程介绍文本区域

    @FXML
    private TextField cTime; // 课程时间输入框

    @FXML
    private TextField cLocation; // 课程地点输入框

    @FXML
    private TextField cSchool; // 课程所属学校输入框

    @FXML
    private TextField cInit; // 课程开始时间输入框

    @FXML
    private TextField cEnd; // 课程结束时间输入框

    @FXML
    private TextField cPeriod; // 课程周期输入框

    @FXML
    private TextField cCapacity; // 课程容量输入框

    @FXML
    private Button buttonSet; // 确定按钮

    @FXML
    private Button buttonCancel; // 取消按钮

    /**
     * 跳转到茶会事件的处理方法。
     * 此方法目前为空，待实现功能。
     * @FXML 标记此方法与FXML界面元素绑定，用于界面交互。
     */
    @FXML
    void goToTeacherEvent() {
        // 待实现功能
    }

    /**
     * 设置课程事件。此方法用于教师通过界面输入课程信息，并创建新课程。
     * 如果输入字段为空或课程已存在，则会抛出异常。
     * 成功创建课程后，会显示提示消息并关闭当前窗口。
     *
     * @throws TeacherException.TextFieldIsNullException 如果输入字段为空。
     * @throws TeacherException.CourseExistException     如果课程已存在。
     */
    @FXML
    void setEvent() {
        try {
            // 初始化课程对象和课程数据访问对象
            Course course = new Course();
            CourseDAO courseDAO = new CourseDAO();

            // 检查文本字段是否为空，如果为空则抛出异常
            if (textFieldIsNull()) {
                throw new com.secwsystem.ctrl.teacher.TeacherException.TextFieldIsNullException();
            }

            // 检查课程是否已存在，如果存在则抛出异常
            if (courseDAO.getCourse(cid.getText().trim()) != null) {
                throw new TeacherException.CourseExistException();
            }

            // 设置课程的各种属性，从界面文本字段获取信息
            course.setCid(cid.getText().trim());
            course.setCName(cName.getText().trim());
            course.setCInfo(cInfo.getText().trim());
            course.setCTime(cTime.getText().trim());
            course.setCInit(cInit.getText().trim());
            course.setCEnd(cEnd.getText().trim());
            course.setCLocation(cLocation.getText().trim());
            course.setCType(cType.getText().trim());
            course.setCSchool(cSchool.getText().trim());
            course.setCPeriod(cPeriod.getText().trim());
            course.setCCapacity(cCapacity.getText().trim());
            course.setCCurrent("0");

            // 添加一个空的学生列表到课程（表示尚未有学生报名）
            course.addStudent(null);

            // 显示成功消息并关闭当前窗口
            new TeacherApplication().showMessage("提示", "课程创建成功！", Alert.AlertType.INFORMATION, 0);
            stage.close();
        } catch (TeacherException.TextFieldIsNullException e) {
            // 如果文本字段为空，显示相应提示消息
            new TeacherApplication().showMessage("提示", "请检查输入信息是否为空！", Alert.AlertType.INFORMATION, 0);
        } catch (TeacherException.CourseExistException e) {
            // 如果课程已存在，显示相应提示消息
            new TeacherApplication().showMessage("提示", "课程已存在！", Alert.AlertType.INFORMATION, 0);
        }
    }

    /**
     * 取消事件处理方法。
     * 该方法通过关闭当前舞台来取消正在进行的事件或操作。
     * 使用@FXML注解表明该方法是由FXML控制器定义的，用于处理UI元素的事件。
     */
    @FXML
    void cancelEvent() {
        stage.close();
    }

    /**
     * 检查文本字段是否为空
     * 
     * 此方法用于验证多个文本字段是否为空或仅包含空格。这些字段可能代表不同类型的输入信息，
     * 如活动的ID、名称、类型等。如果任何一个字段为空或仅包含空格，则认为验证不通过。
     * 这种验证机制有助于确保输入数据的有效性和完整性。
     * 
     * @return boolean 返回true如果任何一个文本字段为空或仅包含空格，否则返回false。
     */
    boolean textFieldIsNull() {
        // 通过调用getText().trim().isEmpty()检查每个文本字段是否为空或仅包含空格
        // 使用逻辑或（||）连接每个检查，如果任何一个检查为true，则方法返回true
        return cid.getText().trim().isEmpty() || cName.getText().trim().isEmpty()
                || cType.getText().trim().isEmpty()
                || cInfo.getText().trim().isEmpty() || cTime.getText().trim().isEmpty()
                || cLocation.getText().trim().isEmpty() || cInit.getText().trim().isEmpty()
                || cEnd.getText().trim().isEmpty() || cSchool.getText().trim().isEmpty()
                || cPeriod.getText().trim().isEmpty() || cCapacity.getText().trim().isEmpty();
    }
}
