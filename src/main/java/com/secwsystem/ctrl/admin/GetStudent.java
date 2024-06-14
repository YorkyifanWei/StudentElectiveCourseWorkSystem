package com.secwsystem.ctrl.admin;

import com.secwsystem.dao.pojo.StudentPrivate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 控制器类，用于初始化和展示学生详细信息界面。
 * 通过FXMl中的@FXMlController注解与界面元素绑定，实现数据的展示。
 */
public class GetStudent implements Initializable {

    @FXML
    private TextField sid; // 学生ID文本框

    @FXML
    private TextField sPassword; // 学生密码文本框

    @FXML
    private TextField sName; // 学生姓名文本框

    @FXML
    private TextField sSex; // 学生性别文本框

    @FXML
    private TextField sPhoneNumber; // 学生电话号码文本框

    @FXML
    private TextField sIdcard; // 学生身份证号码文本框

    @FXML
    private TextField sSchool; // 学生学校文本框

    @FXML
    private TextField sClass; // 学生班级文本框

    @FXML
    private TextField sEmail; // 学生邮箱文本框

    @FXML
    private TextField sEntertime; // 学生入学时间文本框

    /**
     * 初始化界面控件并加载学生信息。
     * 此方法由JavaFX的Initializable接口定义，用于在界面加载完成后执行初始化逻辑。
     *
     * @param url           界面资源的URL，此处未使用
     * @param resourceBundle 界面资源包，此处未使用
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 通过AdminContext获取AdminMainController实例
        AdminMainController controller = (AdminMainController) AdminContext.controllers
                .get(AdminMainController.class.getSimpleName());
        // 从控制器中获取学生私有信息对象
        StudentPrivate student = controller.getStudent();
        // 将学生信息填充到界面文本框中
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