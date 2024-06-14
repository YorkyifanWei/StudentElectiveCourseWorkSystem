package com.secwsystem.ctrl.admin;

import com.secwsystem.app.Admin;
import com.secwsystem.dao.impl.StudentDAO;
import com.secwsystem.dao.pojo.StudentPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 添加学生的控制器类，负责处理添加学生相关的UI事件和业务逻辑。
 */
public class AddStudent {

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
    private TextField sid; // 学生ID输入框

    @FXML
    private TextField sPassword; // 学生密码输入框

    @FXML
    private TextField sName; // 学生姓名输入框

    @FXML
    private TextField sSex; // 学生性别输入框

    @FXML
    private TextField sPhone; // 学生电话输入框

    @FXML
    private TextField sIdcard; // 学生身份证号输入框

    @FXML
    private TextField sSchool; // 学生学校输入框

    @FXML
    private TextField sClass; // 学生班级输入框

    @FXML
    private TextField sEntertime; // 学生入学时间输入框

    @FXML
    private TextField sEmail; // 学生邮箱输入框

    @FXML
    private Button buttonSet; // 确定按钮

    @FXML
    private Button buttonCancel; // 取消按钮

    /**
     * 取消操作事件处理方法，用于关闭当前窗口。
     */
    @FXML
    void cancelEvent() {
        stage.close();
    }

    /**
     * 设置学生信息并尝试添加到数据库。
     * 此方法首先检查文本字段是否为空，然后尝试添加新学生到数据库。
     * 如果学生信息填写不完整或学生已存在，则会抛出相应的异常。
     * 如果成功添加学生，则关闭当前窗口，并在主控制器中更新学生列表。
     * 
     * @throws AdminException 如果文本字段为空、学生已存在或添加学生失败，则抛出相应的AdminException子类异常。
     */
    @FXML
    void setEvent() {
        try{
            // 创建一个新的学生对象
            StudentPrivate student = new StudentPrivate();
            // 创建学生数据访问对象
            StudentDAO studentDAO = new StudentDAO();

            // 检查文本字段是否为空
            if (textFieldIsNull()) {
                throw new AdminException.TextFieldNullException();
            }

            // 检查数据库中是否已存在相同ID的学生
            if (studentDAO.getPrivate(sid.getText().trim()) != null) {
                throw new AdminException.StudentExistException();
            }

            // 设置学生对象的各种属性，从相应的文本字段获取输入信息
            student.setSid(sid.getText().trim());
            student.setSName(sName.getText().trim());
            student.setSSex(sSex.getText().trim());
            student.setSPhoneNumber(sPhone.getText().trim());
            student.setSIdcard(sIdcard.getText().trim());
            student.setSSchool(sSchool.getText().trim());
            student.setSClass(sClass.getText().trim());
            student.setSEmail(sEmail.getText().trim());
            student.setSPassword(sPassword.getText().trim());
            student.setSEntertime(sEntertime.getText().trim());

            // 尝试将学生对象添加到数据库
            if (studentDAO.add(student)) {
                // 如果添加成功，更新主控制器中的学生列表，并显示成功消息
                AdminMainController controller = (AdminMainController) AdminContext.controllers.get(AdminMainController.class.getSimpleName());
                controller.AddStuToTable(student);
                new Admin().showMessage("提示","学生添加成功！", Alert.AlertType.INFORMATION,0);
                stage.close();
            } else {
                throw new AdminException.AddException();
            }
        } catch (AdminException.TextFieldNullException e) {
            // 如果文本字段为空，显示错误消息
            new Admin().showMessage("提示","请填写完整信息！", Alert.AlertType.ERROR,0);
        } catch (AdminException.StudentExistException e) {
            // 如果学生已存在，显示错误消息
            new Admin().showMessage("创建失败","该学生已存在！", Alert.AlertType.ERROR,0);
        } catch (AdminException.AddException e) {
            // 如果添加学生失败，显示错误消息
            new Admin().showMessage("创建异常","添加失败,请重新添加！", Alert.AlertType.ERROR,0);
        }
    }

    /**
     * 检查输入文本框是否为空的方法。
     * 
     * @return 如果任意一个文本框为空，则返回true；否则返回false。
     */
    boolean textFieldIsNull() {
        return sid.getText().trim().isEmpty() || sPassword.getText().trim().isEmpty() ||
                sName.getText().trim().isEmpty() || sSex.getText().trim().isEmpty() ||
                sPhone.getText().trim().isEmpty() || sIdcard.getText().trim().isEmpty() ||
                sSchool.getText().trim().isEmpty() || sClass.getText().trim().isEmpty() ||
                sEmail.getText().trim().isEmpty();
    }

}