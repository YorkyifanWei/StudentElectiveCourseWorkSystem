package com.secwsystem.ctrl.admin;

import com.secwsystem.dao.impl.CourseDAO;
import com.secwsystem.dao.pojo.Course;
import com.secwsystem.app.AdminApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * ModifyCourse 类实现了 Initializable 接口，负责处理课程信息的修改界面功能。
 */
public class ModifyCourse implements Initializable {

    // 获取当前窗口的Stage对象
    public Stage getStage() {
        return stage;
    }

    // 设置当前窗口的Stage对象
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage; // 当前窗口的Stage对象

    @FXML
    private TextField cid; // 课程ID

    @FXML
    private TextField cName; // 课程名称

    @FXML
    private TextField cType; // 课程类型

    @FXML
    private TextArea cInfo; // 课程详细信息

    @FXML
    private TextField cTime; // 上课时间

    @FXML
    private TextField cSchool; // 学校名称

    @FXML
    private TextField cPeriod; // 课程周期

    @FXML
    private TextField cCapacity; // 课程容量

    @FXML
    private TextField cInit; // 开课时间

    @FXML
    private TextField cEnd; // 结束时间

    @FXML
    private TextField cLocation; // 上课地点

    @FXML
    private Button buttonSet; // 确认修改按钮

    @FXML
    private Button buttonCancel; // 取消修改按钮

    /**
     * 初始化界面控件的值，用于展示课程的详细信息。
     * 此方法在用户界面加载时调用，用于填充课程的相关字段。
     * 
     * @param url 用于加载资源的URL，此处未使用。
     * @param resourceBundle 用于国际化等的资源包，此处未使用。
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 从AdminContext中获取AdminMainController实例
        AdminMainController controller = (AdminMainController) AdminContext.controllers
                .get(AdminMainController.class.getSimpleName());

        // 从控制器中获取当前操作的课程对象
        Course course = controller.getCourse();

        // 将课程的各个属性值赋给界面的对应控件
        cid.setText(course.getCid());
        cName.setText(course.getCName());
        cType.setText(course.getCType());
        cInfo.setText(course.getCInfo());
        cTime.setText(course.getCTime());
        cSchool.setText(course.getCSchool());
        cPeriod.setText(course.getCPeriod());
        cCapacity.setText(course.getCCapacity());
        cInit.setText(course.getCInti());
        cEnd.setText(course.getCEnd());
        cLocation.setText(course.getCLocation());
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
     * 设置事件处理方法，用于更新课程信息。
     * 该方法从文本字段获取新的课程信息，更新数据库中的相应课程，并在成功更新后关闭当前窗口。
     * 如果文本字段为空，或者更新课程失败，将抛出相应的异常并显示错误消息。
     *
     * @FXML 注解表示该方法与FXML文件中的某个元素关联。
     */
    @FXML
    void setEvent() {
        try {
            // 通过AdminContext获取AdminMainController实例
            AdminMainController controller = (AdminMainController) AdminContext.controllers
                    .get(AdminMainController.class.getSimpleName());
            // 获取当前控制器中的课程对象
            Course course = controller.getCourse();
            // 创建CourseDAO对象，用于数据库操作
            CourseDAO courseDAO = new CourseDAO();

            // 检查文本字段是否为空，如果为空则抛出TextFieldNullException
            if (textFieldIsNull()) {
                throw new AdminException.TextFieldNullException();
            }

            // 从文本字段中获取输入的课程信息，并设置到course对象中
            course.setCid(cid.getText().trim());
            course.setCName(cName.getText().trim());
            course.setCType(cType.getText().trim());
            course.setCInfo(cInfo.getText().trim());
            course.setCTime(cTime.getText().trim());
            course.setCSchool(cSchool.getText().trim());
            course.setCPeriod(cPeriod.getText().trim());
            course.setCCapacity(cCapacity.getText().trim());
            course.setCInit(cInit.getText().trim());
            course.setCEnd(cEnd.getText().trim());
            course.setCLocation(cLocation.getText().trim());

            // 更新数据库中的课程信息
            if (courseDAO.updateCourse(course)) {
                // 如果更新成功，更新表格中的课程信息，并显示成功消息
                controller.modifyCourseToTable(course);
                new AdminApplication().showMessage("提示", "修改成功", Alert.AlertType.INFORMATION, 0);
                stage.close();
            } else {
                // 如果更新失败，抛出ModifyException异常
                throw new AdminException.ModifyException();
            }
        } catch (AdminException.TextFieldNullException e) {
            // 处理文本字段为空的异常，显示错误消息
            new AdminApplication().showMessage("修改失败", "修改不能为空", Alert.AlertType.ERROR, 0);
        } catch (AdminException.ModifyException e) {
            // 处理更新失败的异常，显示错误消息
            new AdminApplication().showMessage("修改异常", "修改失败,请重新修改", Alert.AlertType.ERROR, 0);
        }

    }

    /**
     * 检查文本字段是否为空
     * 
     * 此方法用于验证多个文本字段是否为空或仅包含空格。这些字段可能代表不同的输入信息，
     * 如活动的ID、名称、类型等。如果任何一个字段为空或仅包含空格，则认为验证不通过。
     * 这种验证机制有助于确保输入数据的有效性和完整性。
     * 
     * @return boolean 返回true如果任何一个文本字段为空或仅包含空格，否则返回false。
     */
    boolean textFieldIsNull() {
        // 通过调用getText().trim().isEmpty()检查每个文本字段是否为空或仅包含空格
        // 返回true如果任何一个字段满足此条件，表明存在空字段。
        return cid.getText().trim().isEmpty() || cName.getText().trim().isEmpty()
                || cType.getText().trim().isEmpty() || cInfo.getText().trim().isEmpty()
                || cTime.getText().trim().isEmpty()
                || cLocation.getText().trim().isEmpty() || cInit.getText().trim().isEmpty()
                || cEnd.getText().trim().isEmpty() || cSchool.getText().trim().isEmpty()
                || cPeriod.getText().trim().isEmpty() || cCapacity.getText().trim().isEmpty();
    }
}
