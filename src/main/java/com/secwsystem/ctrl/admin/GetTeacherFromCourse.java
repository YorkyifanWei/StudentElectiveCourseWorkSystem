package com.secwsystem.ctrl.admin;

import com.secwsystem.dao.pojo.TeacherPrivate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 此类初始化从课程中获取的教师信息页面。
 * 它通过绑定FXML组件来展示教师的详细信息。
 */
public class GetTeacherFromCourse implements Initializable {

    @FXML
    private TextField tid; // 教师ID文本框

    @FXML
    private TextField tPassword; // 教师密码文本框

    @FXML
    private TextField tName; // 教师姓名文本框

    @FXML
    private TextField tSex; // 教师性别文本框

    @FXML
    private TextField tIdcard; // 教师身份证号码文本框

    @FXML
    private TextField tPhoneNumber; // 教师电话号码文本框

    @FXML
    private TextField tSchool; // 教师所在学校文本框

    @FXML
    private TextField tTitle; // 教师职称文本框

    @FXML
    private TextField tEmail; // 教师电子邮件文本框

    @FXML
    private TextField tAddress; // 教师地址文本框

    /**
     * 初始化教师信息页面。
     * 从GetTeachersFromCourse控制器中获取教师信息，并填充到相应的文本框中。
     *
     * @param url             页面URL
     * @param resourceBundle  页面资源包
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 通过AdminContext获取GetTeachersFromCourse控制器实例
        GetTeachersFromCourse controller = (GetTeachersFromCourse) AdminContext.controllers
                .get(GetTeachersFromCourse.class.getSimpleName());
        // 从控制器中获取教师信息
        TeacherPrivate teacher = controller.getTeacherFromCourse();
        // 将教师信息填充到文本框中
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