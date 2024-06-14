package com.secwsystem.ctrl.admin;

import com.secwsystem.dao.pojo.StudentPrivate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 控制器类，用于初始化学生信息页面，从课程中获取特定学生的信息并展示在文本字段中。
 * 该类实现了Initializable接口，以便在FXML加载后进行初始化操作。
 */
public class GetStudentFromCourse implements Initializable {

    @FXML
    private TextField sid; // 学生ID文本字段

    @FXML
    private TextField sPassword; // 学生密码文本字段

    @FXML
    private TextField sName; // 学生姓名文本字段

    @FXML
    private TextField sSex; // 学生性别文本字段

    @FXML
    private TextField sPhoneNumber; // 学生电话号码文本字段

    @FXML
    private TextField sIdcard; // 学生身份证号码文本字段

    @FXML
    private TextField sSchool; // 学生学校文本字段

    @FXML
    private TextField sClass; // 学生班级文本字段

    @FXML
    private TextField sEmail; // 学生电子邮件文本字段

    @FXML
    private TextField sEntertime; // 学生入学时间文本字段

    /**
     * 初始化函数，当FXML控制器加载时调用。
     * @param url FXML资源的URL，此处未使用。
     * @param resourceBundle FXML资源包，此处未使用。
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 通过AdminContext获取GetStudentsFromCourse控制器实例
        GetStudentsFromCourse controller = (GetStudentsFromCourse) AdminContext.controllers
                .get(GetStudentsFromCourse.class.getSimpleName());
        // 从课程中获取学生私有信息
        StudentPrivate student = controller.getStudentFromCourse();
        // 将学生信息填充到相应的文本字段中
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
}