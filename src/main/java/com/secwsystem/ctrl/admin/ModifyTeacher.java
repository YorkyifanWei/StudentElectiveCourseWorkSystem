package com.secwsystem.ctrl.admin;

import com.secwsystem.app.AdminApplication;
import com.secwsystem.dao.impl.TeacherDAO;
import com.secwsystem.dao.pojo.TeacherPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * ModifyTeacher 类用于实现教师信息的修改功能。
 * 它实现了 Initializable 接口，以便在初始化阶段加载界面元素。
 */
public class ModifyTeacher implements Initializable {

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
    private Stage stage; // 当前窗口的 Stage 对象，用于控制舞台

    @FXML
    private Button buttonSet; // 用于设置教师信息的按钮

    @FXML
    private Button buttonCancel; // 用于取消教师信息修改的按钮

    @FXML
    private TextField tid; // 输入教师ID的文本框

    @FXML
    private TextField tPassword; // 输入教师密码的文本框

    @FXML
    private TextField tName; // 输入教师姓名的文本框

    @FXML
    private TextField tSex; // 输入教师性别的文本框

    @FXML
    private TextField tIdcard; // 输入教师身份证号的文本框

    @FXML
    private TextField tPhoneNumber; // 输入教师电话号码的文本框

    @FXML
    private TextField tSchool; // 输入教师所在学校的文本框

    @FXML
    private TextField tTitle; // 输入教师职称的文本框

    @FXML
    private TextField tEmail; // 输入教师电子邮件的文本框

    @FXML
    private TextField tAddress; // 输入教师地址的文本框

    /**
     * 初始化界面时调用的方法，用于展示教师的详细信息。
     * 
     * @param url            页面的URL地址，此处未使用。
     * @param resourceBundle 页面的资源包，此处未使用。
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 通过AdminContext获取AdminMainController实例，用于后续获取教师信息
        AdminMainController controller = (AdminMainController) AdminContext.controllers
                .get(AdminMainController.class.getSimpleName());

        // 从控制器中获取当前教师对象
        TeacherPrivate teacher = controller.getTeacher();

        // 将教师的信息展示在界面上
        tid.setText(teacher.getTid());
        tName.setText(teacher.getTName());
        tSex.setText(teacher.getTSex());
        tIdcard.setText(teacher.getTIdcard());
        tPhoneNumber.setText(teacher.getTPhoneNumber());
        tSchool.setText(teacher.getTSchool());
        tTitle.setText(teacher.getTTitle());
        tEmail.setText(teacher.getTEmail());
        tAddress.setText(teacher.getTAddress());
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
     * 设置事件处理方法，用于更新教师的个人信息。
     * 通过获取AdminMainController实例，从界面获取教师信息，并通过TeacherDAO更新数据库中的教师信息。
     * 如果文本字段为空，则抛出TextFieldNullException。如果更新失败，则抛出ModifyException。
     * 成功更新后，关闭当前窗口，并显示修改成功的消息。
     * 
     * @throws AdminException.TextFieldNullException 如果文本字段为空，则抛出此异常。
     * @throws AdminException.ModifyException 如果更新教师信息失败，则抛出此异常。
     */
    @FXML
    void setEvent() {
        try {
            // 从AdminContext中获取AdminMainController实例
            AdminMainController controller = (AdminMainController) AdminContext.controllers
                    .get(AdminMainController.class.getSimpleName());
            // 获取当前登录的教师信息
            TeacherPrivate teacher = controller.getTeacher();
            // 创建TeacherDAO实例，用于数据库操作
            TeacherDAO teacherDAO = new TeacherDAO();

            // 检查文本字段是否为空，如果为空则抛出异常
            if (textFieldIsNull()) {
                throw new AdminException.TextFieldNullException();
            }

            // 从界面文本字段中获取教师信息，并设置到teacher对象中
            teacher.setTid(tid.getText().trim());
            teacher.setTName(tName.getText().trim());
            teacher.setTSex(tSex.getText().trim());
            teacher.setTIdcard(tIdcard.getText().trim());
            teacher.setTPhoneNumber(tPhoneNumber.getText().trim());
            teacher.setTSchool(tSchool.getText().trim());
            teacher.setTTitle(tTitle.getText().trim());
            teacher.setTEmail(tEmail.getText().trim());
            teacher.setTAddress(tAddress.getText().trim());

            // 使用TeacherDAO更新数据库中的教师信息
            if (teacherDAO.updatePrivate(teacher)) {
                // 如果更新成功，则在界面中更新教师信息，并显示修改成功的消息
                controller.modifyTeacherToTable(teacher);
                new AdminApplication().showMessage("修改成功", "修改成功", Alert.AlertType.INFORMATION, 0);
                stage.close();
            } else {
                // 如果更新失败，则抛出ModifyException异常
                throw new AdminException.ModifyException();
            }
        } catch (AdminException.TextFieldNullException e) {
            // 处理文本字段为空的异常，显示错误消息
            new AdminApplication().showMessage("修改失败", "请将信息填写完整", Alert.AlertType.ERROR, 0);
        } catch (AdminException.ModifyException e) {
            // 处理修改异常，显示错误消息
            new AdminApplication().showMessage("修改异常", "修改失败,请重新修改", Alert.AlertType.ERROR, 0);
        }
    }

    /**
     * 检查所有文本字段是否为空
     * 该方法用于验证多个文本字段是否为空或仅包含空格。这在需要确保所有必要信息都已输入的情况下非常有用，
     * 比如在用户注册或信息录入的场景中。
     * 
     * @return boolean 返回true如果任何字段为空或仅包含空格，否则返回false。
     */
    boolean textFieldIsNull() {
        // 通过trim()方法去除文本字段两端的空格，然后检查是否为空，如果任何字段满足条件，则返回true。
        return tid.getText().trim().isEmpty() || tName.getText().trim().isEmpty()
                || tSex.getText().trim().isEmpty() || tIdcard.getText().trim().isEmpty()
                || tPhoneNumber.getText().trim().isEmpty() || tSchool.getText().trim().isEmpty()
                || tTitle.getText().trim().isEmpty() || tEmail.getText().trim().isEmpty()
                || tAddress.getText().trim().isEmpty();
    }

}
