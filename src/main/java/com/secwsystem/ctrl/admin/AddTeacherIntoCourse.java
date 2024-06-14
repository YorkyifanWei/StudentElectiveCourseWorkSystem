package com.secwsystem.ctrl.admin;

import com.secwsystem.app.AdminApplication;
import com.secwsystem.dao.impl.CourseDAO;
import com.secwsystem.dao.impl.TeacherDAO;
import com.secwsystem.dao.pojo.Course;
import com.secwsystem.dao.pojo.TeacherPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 控制器类，用于添加教师到课程中。
 */
public class AddTeacherIntoCourse {

    @FXML
    private Stage stage; // 主舞台

    @FXML
    private TextField teacherInCourse; // 输入教师账号的文本框

    @FXML
    private Button buttonSet; // 确定按钮

    @FXML
    private Button buttonCancel; // 取消按钮

    /**
     * 获取当前舞台。
     * 
     * @return 当前的Stage对象
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * 设置当前舞台。
     * 
     * @param stage 要设置的Stage对象
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * 取消操作事件处理方法，关闭当前舞台。
     */
    @FXML
    void cancelEvent() {
        stage.close();
    }

    /**
     * 确定操作事件处理方法，尝试将教师添加到课程中。
     */
    @FXML
    void setEvent() {
        try {
            // 从AdminContext中获取主控制器
            AdminMainController maincontroller = (AdminMainController) AdminContext.controllers.get(AdminMainController.class.getSimpleName());
            // 获取选中的课程
            Course course = maincontroller.getCourse();
            // 实例化课程DAO和教师DAO
            CourseDAO courseDAO = new CourseDAO();
            TeacherDAO teacherDAO = new TeacherDAO();
            // 检查教师账号名是否为空
            if (teacherInCourse.getText().trim().isEmpty()) {
                throw new AdminException.TextFieldNullException();
            }
            // 检查教师是否存在
            if (teacherDAO.getPrivate(teacherInCourse.getText().trim()) == null) {
                throw new AdminException.TeacherNotExistException();
            }
            // 尝试将教师添加到课程中
            if (courseDAO.addTeacher(course.getCid(), teacherInCourse.getText().trim())) {
                // 从AdminContext中获取显示教师列表的控制器
                GetTeachersFromCourse getTeasFromCouController = (GetTeachersFromCourse) AdminContext.controllers.get("GetTeasFromCouController");
                // 获取添加的教师信息
                TeacherPrivate teacher = teacherDAO.getPrivate(teacherInCourse.getText().trim());
                // 将教师信息添加到教师列表中
                getTeasFromCouController.addTeacherToTable(teacher);
                // 显示添加成功的提示信息
                new AdminApplication().showMessage("添加成功", "添加成功", Alert.AlertType.INFORMATION, 0);
                stage.close();
            } else {
                throw new AdminException.AddException();
            }
        } catch (AdminException.TextFieldNullException e) {
            // 显示教师账号名不能为空的错误提示
            new AdminApplication().showMessage("添加失败", "教师账号名不可为空", Alert.AlertType.ERROR, 0);
        } catch (AdminException.TeacherNotExistException e) {
            // 显示教师不存在的错误提示
            new AdminApplication().showMessage("添加失败", "教师不存在", Alert.AlertType.ERROR, 0);
        } catch (AdminException.AddException e) {
            // 显示添加失败的错误提示
            new AdminApplication().showMessage("添加失败", "添加失败,请重新添加", Alert.AlertType.ERROR, 0);
        }
    }
}