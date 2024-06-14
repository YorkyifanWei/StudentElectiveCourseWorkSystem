package com.secwsystem.ctrl.admin;

import com.secwsystem.app.AdminApplication;
import com.secwsystem.dao.pojo.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * 控制器类，用于管理课程信息的获取。
 * 实现Initializable接口，以便在初始化阶段加载课程数据。
 */
public class GetCourse implements Initializable {

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
    private TextField cSchool; // 课程所属学校输入框

    @FXML
    private TextField cPeriod; // 课程周期输入框

    @FXML
    private TextField cCurrent; // 当前报名人数输入框

    @FXML
    private TextField cInit; // 课程初始容量输入框

    @FXML
    private TextField cEnd; // 课程结束时间输入框

    @FXML
    private TextField cLocation; // 课程地点输入框

    @FXML
    private Button buttonGetTeachers; // 获取教师按钮

    @FXML
    private Button buttonGetStudents; // 获取学生按钮

    /**
     * 初始化方法，用于在界面加载后设置课程的相关信息。
     * @param url URL资源
     * @param resourceBundle 资源包
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 通过AdminContext获取AdminMainController实例
        AdminMainController controller = (AdminMainController) AdminContext.controllers
                .get(AdminMainController.class.getSimpleName());
        // 获取当前课程信息
        Course course = controller.getCourse();
        // 将课程信息填充到相应的输入框中
        cid.setText(course.getCid());
        cName.setText(course.getCName());
        cType.setText(course.getCType());
        cInfo.setText(course.getCInfo());
        cTime.setText(course.getCTime());
        cSchool.setText(course.getCSchool());
        cPeriod.setText(course.getCPeriod());
        cInit.setText(course.getCInti());
        cEnd.setText(course.getCEnd());
        cLocation.setText(course.getCLocation());
        cCurrent.setText(course.getCCurrent() + "/" + course.getCCapacity());
    }

    /**
     * 点击获取学生按钮时触发的事件处理方法。
     * 通过AdminApplication类的方法获取该课程的所有学生信息。
     * @throws IOException 如果发生I/O错误
     */
    @FXML
    void getStudentsEvent() throws IOException {
        new AdminApplication().getStudentsFromCourse();
    }

    /**
     * 点击获取教师按钮时触发的事件处理方法。
     * 通过AdminApplication类的方法获取该课程的所有教师信息。
     * @throws IOException 如果发生I/O错误
     */
    @FXML
    void getTeachersEvent() throws IOException {
        new AdminApplication().getTeachersFromCourse();
    }
}