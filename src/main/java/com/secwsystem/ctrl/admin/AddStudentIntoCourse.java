package com.secwsystem.ctrl.admin;

import com.secwsystem.app.AdminApplication;
import com.secwsystem.dao.impl.CourseDAO;
import com.secwsystem.dao.impl.StudentDAO;
import com.secwsystem.dao.pojo.Course;
import com.secwsystem.dao.pojo.StudentPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 控制器类，用于处理添加学生到课程的操作。
 */
public class AddStudentIntoCourse {

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage; // 主舞台

    @FXML
    private TextField studentInCourse; // 输入学生账号的文本字段

    @FXML
    private Button buttonSet; // 确定按钮

    @FXML
    private Button buttonCancel; // 取消按钮

    /**
     * 取消操作事件处理方法，用于关闭当前舞台。
     */
    @FXML
    void cancelEvent() {
        stage.close();
    }

    /**
     * 确定操作事件处理方法，用于将学生添加到课程。
     */
    @FXML
    void setEvent() {
        try{
            // 获取主控制器实例
            AdminMainController maincontroller = (AdminMainController) AdminContext.controllers.get(AdminMainController.class.getSimpleName());
            // 获取选中的课程
            Course course = maincontroller.getCourse();
            // 实例化课程DAO和学生DAO
            CourseDAO courseDAO = new CourseDAO();
            StudentDAO studentDAO = new StudentDAO();

            // 检查输入文本是否为空
            if (studentInCourse.getText().trim().isEmpty()) {
                throw new AdminException.TextFieldNullException();
            }
            // 检查学生是否存在
            if (studentDAO.getPrivate(studentInCourse.getText().trim()) == null) {
                throw new AdminException.TeacherNotExistException();
            }
            // 尝试将学生添加到课程
            if (courseDAO.addStudent(course.getCid(), studentInCourse.getText().trim())) {
                // 获取显示学生列表的控制器实例
                GetStudentsFromCourse getStusFromCouController = (GetStudentsFromCourse) AdminContext.controllers.get("GetStusFromCouController");
                // 获取添加的学生信息
                StudentPrivate student = studentDAO.getPrivate(studentInCourse.getText().trim());
                // 将学生添加到学生列表
                getStusFromCouController.AddStuToTable(student);
                // 显示添加成功的提示
                new AdminApplication().showMessage("添加成功", "添加成功", Alert.AlertType.INFORMATION, 0);
                stage.close();
            } else {
                throw new AdminException.AddException();
            }
        } catch (AdminException.TextFieldNullException e) {
            // 显示输入为空的错误提示
            new AdminApplication().showMessage("添加失败", "学生账号名不可为空", Alert.AlertType.ERROR, 0);
        } catch (AdminException.TeacherNotExistException e) {
            // 显示学生不存在的错误提示
            new AdminApplication().showMessage("添加失败", "学生不存在", Alert.AlertType.ERROR, 0);
        } catch (AdminException.AddException e) {
            // 显示添加失败的错误提示
            new AdminApplication().showMessage("添加失败", "添加失败,请查看课程是否已满", Alert.AlertType.ERROR, 0);
        }
    }

}