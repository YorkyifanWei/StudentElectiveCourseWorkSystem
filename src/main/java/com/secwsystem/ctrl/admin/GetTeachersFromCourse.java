package com.secwsystem.ctrl.admin;

import com.secwsystem.app.AdminApplication;
import com.secwsystem.dao.impl.TeacherDAO;
import com.secwsystem.dao.pojo.Course;
import com.secwsystem.dao.pojo.TeacherPrivate;
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
 * 控制器类，用于管理特定课程的教师列表。
 * 实现Initializable接口，以便在初始化阶段加载教师数据。
 */
public class GetTeachersFromCourse implements Initializable {

    @FXML
    private TableView<TeacherPrivate> tableTeachersFromCourse; // 教师信息表格视图

    @FXML
    private TableColumn<TeacherPrivate, String> tid; // 教师ID列

    @FXML
    private TableColumn<TeacherPrivate, String> tName; // 教师姓名列

    @FXML
    private TableColumn<TeacherPrivate, String> tSex; // 教师性别列

    @FXML
    private TableColumn<TeacherPrivate, String> tSchool; // 教师所属学校列

    @FXML
    private TableColumn<TeacherPrivate, String> tTitle; // 教师职称列

    @FXML
    private TableColumn<TeacherPrivate, String> tAddress; // 教师地址列

    @FXML
    private TableColumn<TeacherPrivate, String> tEmail; // 教师邮箱列

    @FXML
    private Label labelName; // 显示课程名称的标签

    @FXML
    private Button buttonAdd; // 添加教师按钮

    @FXML
    private Button buttonDelete; // 删除教师按钮

    @FXML
    private Button buttonGet; // 获取教师信息按钮

    /**
     * 初始化方法，加载指定课程的教师信息到表格。
     *
     * @param url           页面URL
     * @param resourceBundle 资源包
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AdminContext.controllers.put(this.getClass().getSimpleName(), this);

        // 获取主控制器和当前课程信息
        AdminMainController controller = (AdminMainController) AdminContext.controllers.get("MainController");
        Course course = controller.getCourse();

        // 设置页面标题
        labelName.setText(course.getCName() + "下面的讲师");

        // 加载教师数据
        TeacherDAO teacherDAO = new TeacherDAO();
        if (course.getTeachers() != null && !course.getTeachers().isEmpty()) {
            ArrayList<TeacherPrivate> teacherPrivates = new ArrayList<>();
            for (int i = 0; i < course.getTeachers().size(); i++) {
                teacherPrivates.add(teacherDAO.getPrivate(course.getTeachers().get(i).getId()));
            }
            ObservableList<TeacherPrivate> teachers = FXCollections.observableArrayList(teacherPrivates);
            tableTeachersFromCourse.setItems(teachers);

            // 配置表格列数据绑定
            configureTableColumns();
        } else {
            tableTeachersFromCourse.setItems(FXCollections.emptyObservableList());
        }
    }

    /**
     * 配置表格列的数据绑定。
     */
    private void configureTableColumns() {
        tid.setCellValueFactory(new PropertyValueFactory<>("tid"));
        tName.setCellValueFactory(new PropertyValueFactory<>("t_name"));
        tSex.setCellValueFactory(new PropertyValueFactory<>("t_sex"));
        tSchool.setCellValueFactory(new PropertyValueFactory<>("t_school"));
        tTitle.setCellValueFactory(new PropertyValueFactory<>("t_title"));
        tAddress.setCellValueFactory(new PropertyValueFactory<>("t_address"));
        tEmail.setCellValueFactory(new PropertyValueFactory<>("t_email"));
    }

    /**
     * 增加教师到课程事件处理方法。
     *
     * @throws IOException 如果发生IO异常
     */
    @FXML
    void addEvent() throws IOException {
        new AdminApplication().addTeacherIntoCourse();
    }

    /**
     * 从课程中删除教师事件处理方法。
     */
    @FXML
    void deleteEvent() {
        try {
            TeacherPrivate teacher = tableTeachersFromCourse.getSelectionModel().getSelectedItem();
            TeacherDAO teacherDAO = new TeacherDAO();
            int index = tableTeachersFromCourse.getSelectionModel().getSelectedIndex();
            
            // 确保有教师被选中
            if (index < 0) {
                throw new AdminException.NoSelectionException();
            }

            // 确认删除操作
            if (new AdminApplication().showMessage("提示", "是否删除该教师", Alert.AlertType.CONFIRMATION, 1)) {
                if (teacherDAO.delete(teacher.getTid())) {
                    tableTeachersFromCourse.getItems().remove(index);
                    new AdminApplication().showMessage("提示", "删除成功", Alert.AlertType.INFORMATION, 0);
                } else {
                    throw new AdminException.DeleteException();
                }
            }
        } catch (AdminException.NoSelectionException e) {
            new AdminApplication().showMessage("提示", "请选择要删除的教师", Alert.AlertType.WARNING, 0);
        } catch (AdminException.DeleteException e) {
            new AdminApplication().showMessage("删除异常", "删除失败,请重新删除", Alert.AlertType.ERROR, 0);
        }
    }

    /**
     * 获取课程中的教师事件处理方法。
     *
     * @throws IOException 如果发生IO异常
     */
    @FXML
    void getTeacherFromCourseEvent() throws IOException {
        new AdminApplication().getTeacherFromCourse();
    }

    /**
     * 获取选中的教师。
     *
     * @return 选中的教师对象
     */
    TeacherPrivate getTeacherFromCourse() {
        return tableTeachersFromCourse.getSelectionModel().getSelectedItem();
    }

    /**
     * 向表格中添加教师。
     *
     * @param teacher 要添加的教师对象
     */
    void addTeacherToTable(TeacherPrivate teacher) {
        tableTeachersFromCourse.getItems().add(teacher);
    }
}