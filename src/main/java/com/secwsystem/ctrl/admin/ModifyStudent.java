package com.secwsystem.ctrl.admin;

import com.secwsystem.app.AdminApplication;
import com.secwsystem.dao.impl.StudentDAO;
import com.secwsystem.dao.pojo.StudentPrivate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * ModifyStudent 类用于实现学生信息的修改功能。
 * 它实现了 Initializable 接口，以便在初始化界面时进行相应的设置。
 */
public class ModifyStudent implements Initializable {

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
    public Stage stage; // 当前窗口的 Stage 对象

    @FXML
    private TextField sid; // 学生ID

    @FXML
    private TextField sPassword; // 学生密码

    @FXML
    private TextField sName; // 学生姓名

    @FXML
    private TextField sSex; // 学生性别

    @FXML
    private TextField sPhoneNumber; // 学生电话号码

    @FXML
    private TextField sIdcard; // 学生身份证号码

    @FXML
    private TextField sSchool; // 学生所在学校

    @FXML
    private TextField sClass; // 学生班级

    @FXML
    private TextField sEmail; // 学生电子邮件

    @FXML
    private TextField sEntertime; // 学生入学时间

    @FXML
    private Button buttonSet; // 确认修改学生信息按钮

    @FXML
    private Button buttonCancel; // 取消修改学生信息按钮

    /**
     * 初始化视图控件的值，用于展示学生信息。
     * 此方法在用户界面加载时调用，用于填充学生详情页面的各种字段。
     * 
     * @param url 页面加载时的URL，此处未使用。
     * @param resourceBundle 页面的资源包，用于国际化等，此处未使用。
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 从AdminContext中获取AdminMainController实例
        AdminMainController controller = (AdminMainController) AdminContext.controllers
                .get(AdminMainController.class.getSimpleName());

        // 从控制器中获取当前操作的学生对象
        StudentPrivate student = controller.getStudent();

        // 将学生对象的属性值赋给对应的UI控件
        sid.setText(student.getSid());
        sName.setText(student.getSName());
        sSex.setText(student.getSSex());
        sPhoneNumber.setText(student.getSPhoneNumber());
        sIdcard.setText(student.getSIdcard());
        sSchool.setText(student.getSSchool());
        sClass.setText(student.getSClass());
        sEmail.setText(student.getSEmail());
        sEntertime.setText(student.getSEntertime());
        sPassword.setText(student.getSPassword());
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
     * 设置学生个人信息的事件处理方法。
     * 该方法用于更新学生的信息。首先从控制器中获取当前选中的学生对象，
     * 然后从文本字段中获取输入的学生信息，并更新学生对象的属性。
     * 最后，通过学生数据访问对象（DAO）来尝试更新数据库中的学生信息。
     * 如果更新成功，则关闭当前窗口并显示成功消息；如果更新失败，则抛出异常并显示相应的错误消息。
     *
     * @throws AdminException.TextFieldNullException 如果文本字段为空，则抛出此异常。
     * @throws AdminException.ModifyException 如果更新学生信息失败，则抛出此异常。
     */
    @FXML
    void setEvent() {
        try {
            // 从管理员主控制器上下文中获取管理员主控制器实例
            AdminMainController controller = (AdminMainController) AdminContext.controllers
                    .get(AdminMainController.class.getSimpleName());
            // 从控制器中获取当前选中的学生对象
            StudentPrivate student = controller.getStudent();
            // 创建学生数据访问对象
            StudentDAO studentDAO = new StudentDAO();

            // 检查文本字段是否为空，如果为空则抛出异常
            if (textFieldIsNull()) {
                throw new AdminException.TextFieldNullException();
            }

            // 从文本字段中获取输入的学生信息，并更新学生对象的属性
            student.setSid(sid.getText().trim());
            student.setSName(sName.getText().trim());
            student.setSSex(sSex.getText().trim());
            student.setSPhoneNumber(sPhoneNumber.getText().trim());
            student.setSIdcard(sIdcard.getText().trim());
            student.setSSchool(sSchool.getText().trim());
            student.setSClass(sClass.getText().trim());
            student.setSEmail(sEmail.getText().trim());
            student.setSEntertime(sEntertime.getText().trim());

            // 尝试更新数据库中的学生信息
            if (studentDAO.updatePrivate(student)) {
                // 如果更新成功，则在控制器中更新学生表格，并显示成功消息
                controller.modifyStudentToTable(student);
                new AdminApplication().showMessage("修改成功", "修改成功", Alert.AlertType.INFORMATION, 0);
                stage.close();
            } else {
                // 如果更新失败，则抛出异常
                throw new AdminException.ModifyException();
            }
        } catch (AdminException.TextFieldNullException e) {
            // 捕获文本字段为空异常，并显示错误消息
            new AdminApplication().showMessage("修改失败", "修改不能为空", Alert.AlertType.ERROR, 0);
        } catch (AdminException.ModifyException e) {
            // 捕获更新异常，并显示错误消息
            new AdminApplication().showMessage("修改异常", "修改异常,请重新修改", Alert.AlertType.ERROR, 0);
        }
    }

    /**
     * 检查所有文本字段是否为空或只包含空格。
     * 此方法用于验证用户输入是否已填写，确保每个必要的字段都包含有效信息。
     * 它检查了sid、sPassword、sName、sSex、sPhoneNumber、sIdcard、sSchool、sClass和sEmail文本字段。
     * 如果任何字段为空或只包含空格，则方法返回true，表示输入不完整。
     * 如果所有字段都至少包含一个非空格字符，则方法返回false，表示输入完整。
     *
     * @return boolean - 如果存在空文本字段则返回true，否则返回false。
     */
    boolean textFieldIsNull() {
        // 通过trim方法去除文本字段两端的空格，然后检查是否为空，如果为空或只包含空格，则条件为真。
        return sid.getText().trim().isEmpty() || sPassword.getText().trim().isEmpty() ||
                sName.getText().trim().isEmpty() || sSex.getText().trim().isEmpty() ||
                sPhoneNumber.getText().trim().isEmpty() || sIdcard.getText().trim().isEmpty() ||
                sSchool.getText().trim().isEmpty() || sClass.getText().trim().isEmpty() ||
                sEmail.getText().trim().isEmpty();
    }
}
