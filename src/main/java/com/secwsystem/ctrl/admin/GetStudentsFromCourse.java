package com.secwsystem.ctrl.admin;

import com.secwsystem.app.AdminApplication;
import com.secwsystem.dao.impl.StudentDAO;
import com.secwsystem.dao.pojo.Course;
import com.secwsystem.dao.pojo.StudentPrivate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * 控制器类，用于管理特定课程的学生信息。
 * 实现Initializable接口，以便在初始化阶段加载学生数据。
 */
public class GetStudentsFromCourse implements Initializable {

        @FXML
    private Label labelName; // 用于显示姓名信息

    @FXML
    private TableView<StudentPrivate> tableStudentsFromCourse; // 用于显示课程的学生列表

    @FXML
    private TableColumn<StudentPrivate, String> sid; // 用于显示学生ID

    @FXML
    private TableColumn<StudentPrivate, String> sName; // 用于显示学生姓名

    @FXML
    private TableColumn<StudentPrivate, String> sSex; // 用于显示学生性别

    @FXML
    private TableColumn<StudentPrivate, String> sSchool; // 用于显示学生所在学校

    @FXML
    private TableColumn<StudentPrivate, String> sClass; // 用于显示学生班级

    @FXML
    private TableColumn<StudentPrivate, String> sEmail; // 用于显示学生邮箱

    @FXML
    private Button buttonAdd; // 用于添加学生信息

    @FXML
    private Button buttonDelete; // 用于删除学生信息

    @FXML
    private Button buttonGet; // 用于获取学生信息

    /**
     * 初始化方法，加载当前课程的学生信息到表格。
     *
     * @param url           静态资源的URL
     * @param resourceBundle 静态资源的属性包
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AdminContext.controllers.put(this.getClass().getSimpleName(), this);

        // 获取主控制器，并从中获取当前选中的课程
        AdminMainController controller = (AdminMainController) AdminContext.controllers.get("MainController");
        Course course = controller.getCourse();

        // 设置页面标题为当前课程名
        labelName.setText(course.getCName() + "下面的学生");

        // 通过DAO加载当前课程的学生私密信息，并显示在表格中
        StudentDAO studentDAO = new StudentDAO();
        if (course.getStudents() != null && !course.getStudents().isEmpty()) {
            ArrayList<StudentPrivate> studentPrivates = new ArrayList<>();
            for (int i = 0; i < course.getStudents().size(); i++) {
                studentPrivates.add(studentDAO.getPrivate(course.getStudents().get(i).getId()));
            }
            ObservableList<StudentPrivate> students = FXCollections.observableArrayList(studentPrivates);
            tableStudentsFromCourse.setItems(students);

            // 配置表格列的显示逻辑
            sid.setCellValueFactory(new PropertyValueFactory<>("sid"));
            sName.setCellValueFactory(new PropertyValueFactory<>("s_name"));
            sSex.setCellValueFactory(new PropertyValueFactory<>("s_sex"));
            sSchool.setCellValueFactory(new PropertyValueFactory<>("s_school"));
            sClass.setCellValueFactory(new PropertyValueFactory<>("s_class"));
            sEmail.setCellValueFactory(new PropertyValueFactory<>("s_email"));
        }
    }

    /**
     * 添加学生的事件处理方法。
     * 调用AdminApplication的addStudentIntoCourse方法，导航到添加学生的界面。
     *
     * @throws IOException 如果导航过程中发生IO异常
     */
    @FXML
    void addEvent() throws IOException {
        new AdminApplication().addStudentIntoCourse();
    }

    /**
     * 删除学生的事件处理方法。
     * 从表格中选中的学生将被删除，如果未选中任何学生，则显示警告消息。
     */
    @FXML
    void deleteEvent() {
        try {
            StudentPrivate studentPrivate = tableStudentsFromCourse.getSelectionModel().getSelectedItem();
            StudentDAO studentDAO = new StudentDAO();
            int index = tableStudentsFromCourse.getSelectionModel().getSelectedIndex();
            // 确保有学生被选中
            if (index < 0) {
                throw new AdminException.NoSelectionException();
            }
            // 显示确认对话框，确认删除操作
            if (new AdminApplication().showMessage("提示", "是否删除该学生", Alert.AlertType.CONFIRMATION, 1)) {
                // 如果确认，则尝试删除学生
                if (studentDAO.delete(studentPrivate.getSid())) {
                    tableStudentsFromCourse.getItems().remove(index);
                    new AdminApplication().showMessage("提示", "删除成功", Alert.AlertType.INFORMATION, 0);
                } else {
                    throw new AdminException.DeleteException();
                }
            }
        } catch (AdminException.NoSelectionException e) {
            new AdminApplication().showMessage("提示", "请选择要删除的学生", Alert.AlertType.WARNING, 0);
        } catch (AdminException.DeleteException e) {
            new AdminApplication().showMessage("删除异常", "删除失败,请重新删除", Alert.AlertType.ERROR, 0);
        }
    }

    /**
     * 获取选中课程的学生信息事件处理方法。
     * 调用AdminApplication的getStudentFromCourse方法，用于处理获取当前课程学生信息的逻辑。
     *
     * @throws IOException 如果导航过程中发生IO异常
     */
    @FXML
    void getStudentFromCourseEvent() throws IOException {
        new AdminApplication().getStudentFromCourse();
    }

    /**
     * 获取当前表格中选中的学生信息。
     *
     * @return 表格中选中的学生信息
     */
    StudentPrivate getStudentFromCourse() {
        return tableStudentsFromCourse.getSelectionModel().getSelectedItem();
    }

    /**
     * 将指定的学生信息添加到表格中。
     *
     * @param student 要添加的学生信息
     */
    void addStudentToTable(StudentPrivate student) {
        tableStudentsFromCourse.getItems().add(student);
    }
}