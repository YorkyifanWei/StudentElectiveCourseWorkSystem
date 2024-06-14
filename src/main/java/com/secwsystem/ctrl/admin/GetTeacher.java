package com.secwsystem.ctrl.admin;

import com.secwsystem.dao.pojo.TeacherPrivate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 控制器类，用于获取和显示教师信息。
 * 通过初始化方法从后台获取教师数据，并填充到相应的文本字段中。
 */
public class GetTeacher implements Initializable {

    @FXML
    private Button buttonSet; // 设置按钮
    @FXML
    private Button buttonCancel; // 取消按钮
    @FXML
    private TextField tid; // 教师ID文本字段
    @FXML
    private TextField tPassword; // 教师密码文本字段
    @FXML
    private TextField tName; // 教师姓名文本字段
    @FXML
    private TextField tSex; // 教师性别文本字段
    @FXML
    private TextField tIdcard; // 教师身份证号文本字段
    @FXML
    private TextField tPhoneNumber; // 教师电话号码文本字段
    @FXML
    private TextField tSchool; // 教师所在学校文本字段
    @FXML
    private TextField tTitle; // 教师职称文本字段
    @FXML
    private TextField tEmail; // 教师邮箱文本字段
    @FXML
    private TextField tAddress; // 教师地址文本字段

    /**
     * 初始化方法，当FXML控制器加载时调用。
     * 从AdminMainController获取教师信息，并填充到相应的文本字段中。
     *
     * @param url           FXML文件的URL
     * @param resourceBundle FXML文件的资源包
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 通过AdminContext获取AdminMainController实例
        AdminMainController controller = (AdminMainController) AdminContext.controllers
                .get(AdminMainController.class.getSimpleName());
        // 获取教师私有信息
        TeacherPrivate teacher = controller.getTeacher();
        // 将教师信息填充到文本字段中
        tid.setText(teacher.getTid());
        tName.setText(teacher.getTName());
        tSex.setText(teacher.getTSex());
        tIdcard.setText(teacher.getTIdcard());
        tPhoneNumber.setText(teacher.getTPhoneNumber());
        tSchool.setText(teacher.getTSchool());
        tTitle.setText(teacher.getTTitle());
        tEmail.setText(teacher.getTEmail());
        tAddress.setText(teacher.getTAddress());
        tPassword.setText(teacher.getTPassword());
    }
}