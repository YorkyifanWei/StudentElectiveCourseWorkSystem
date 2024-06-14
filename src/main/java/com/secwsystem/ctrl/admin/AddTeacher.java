package com.secwsystem.ctrl.admin;

import com.secwsystem.app.AdminApplication;
import com.secwsystem.dao.impl.TeacherDAO;
import com.secwsystem.dao.pojo.TeacherPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 添加教师信息的控制器类。
 * 通过FXML注解与UI元素绑定，负责处理教师信息的添加操作。
 */
public class AddTeacher {

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage; // 主舞台

    @FXML
    private Button buttonSet; // 确定按钮

    @FXML
    private Button buttonCancel; // 取消按钮

    @FXML
    private TextField tid; // 教师ID输入框

    @FXML
    private TextField tPassword; // 教师密码输入框

    @FXML
    private TextField tName; // 教师姓名输入框

    @FXML
    private TextField tSex; // 教师性别输入框

    @FXML
    private TextField tIdcard; // 教师身份证号输入框

    @FXML
    private TextField tPhoneNumber; // 教师电话号码输入框

    @FXML
    private TextField tSchool; // 教师所在学校输入框

    @FXML
    private TextField tTitle; // 教师职称输入框

    @FXML
    private TextField tEmail; // 教师邮箱输入框

    @FXML
    private TextField tAddress; // 教师地址输入框

    /**
     * 取消操作事件处理方法。
     * 关闭当前舞台。
     */
    @FXML
    void cancelEvent() {
        stage.close();
    }

    /**
     * 确定操作事件处理方法。
     * 校验输入信息，若无误则添加教师信息到数据库。
     */
    @FXML
    void setEvent() {
        try {
            TeacherPrivate teacher = new TeacherPrivate();
            TeacherDAO teacherDAO = new TeacherDAO();
            // 检查输入字段是否为空
            if (textFieldIsNull()) {
                throw new AdminException.TextFieldNullException();
            }
            // 检查教师ID是否已存在
            if (teacherDAO.getPrivate(tid.getText().trim()) != null) {
                throw new AdminException.TeacherExistException();
            }
            // 设置教师信息
            teacher.setTid(tid.getText().trim());
            teacher.setTName(tName.getText().trim());
            teacher.setTSex(tSex.getText().trim());
            teacher.setTSchool(tSchool.getText().trim());
            teacher.setTIdcard(tIdcard.getText().trim());
            teacher.setTPhoneNumber(tPhoneNumber.getText().trim());
            teacher.setTEmail(tEmail.getText().trim());
            teacher.setTAddress(tAddress.getText().trim());
            teacher.setTPassword(tPassword.getText().trim());
            teacher.setTTitle(tTitle.getText().trim());
            // 添加教师信息到数据库
            if (teacherDAO.add(teacher)) {
                AdminMainController controller = (AdminMainController) AdminContext.controllers.get(AdminMainController.class.getSimpleName());
                controller.AddTeaToTable(teacher);
                new AdminApplication().showMessage("提示", "教师添加成功！", Alert.AlertType.INFORMATION, 0);
                stage.close();
            } else {
                throw new AdminException.AddException();
            }
        } catch (AdminException.TextFieldNullException e) {
            new AdminApplication().showMessage("提示", "请填写完整信息！", Alert.AlertType.ERROR, 0);
        } catch (AdminException.TeacherExistException e) {
            new AdminApplication().showMessage("创建失败", "教师已存在！", Alert.AlertType.ERROR, 0);
        } catch (AdminException.AddException e) {
            new AdminApplication().showMessage("创建异常", "添加失败,请重新添加！", Alert.AlertType.ERROR, 0);
        }
    }

    /**
     * 检查输入文本字段是否为空。
     * 
     * @return 若所有字段都不为空，则返回false；否则返回true。
     */
    boolean textFieldIsNull() {
        return tid.getText().trim().isEmpty() || tPassword.getText().trim().isEmpty()
                || tName.getText().trim().isEmpty() || tSex.getText().trim().isEmpty()
                || tIdcard.getText().trim().isEmpty() || tPhoneNumber.getText().trim().isEmpty()
                || tSchool.getText().trim().isEmpty() || tTitle.getText().trim().isEmpty()
                || tEmail.getText().trim().isEmpty() || tAddress.getText().trim().isEmpty();
    }
}