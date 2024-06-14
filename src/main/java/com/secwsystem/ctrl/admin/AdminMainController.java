package com.secwsystem.ctrl.admin;

import com.secwsystem.app.AdminApplication;
import com.secwsystem.dao.impl.*;
import com.secwsystem.dao.pojo.Course;
import com.secwsystem.dao.pojo.StudentPrivate;
import com.secwsystem.dao.pojo.TeacherPrivate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminMainController implements Initializable {

    // 获取当前窗口的Stage对象
    public Stage getStage() {
        return stage;
    }

    // 设置当前窗口的Stage对象
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage; // 管理员窗口的Stage对象

    @FXML
    private TextField aid; // 输入教工ID的文本框

    @FXML
    private TextField aName; // 输入教工姓名的文本框

    @FXML
    private Button buttonAlter; // 修改信息按钮

    @FXML
    private Button buttonCourse; // 课程管理按钮

    @FXML
    private Button buttonStudent; // 学生管理按钮

    @FXML
    private Button buttonTeacher; // 教师管理按钮

    @FXML
    private Button buttonAdd; // 添加信息按钮

    @FXML
    private Button buttonDelete; // 删除信息按钮

    @FXML
    private Button buttonModify; // 修改信息按钮

    @FXML
    private Button buttonGet; // 获取信息按钮

    @FXML
    private Label labelCourse; // 显示课程信息标签

    @FXML
    private Label labelStudent; // 显示学生信息标签

    @FXML
    private Label labelTeacher; // 显示教师信息标签

    @FXML
    private TableView<Course> tableCourse; // 课程信息表格

    @FXML
    private TableColumn<Course, String> cid; // 课程ID列

    @FXML
    private TableColumn<Course, String> cName; // 课程名称列

    @FXML
    private TableColumn<Course, String> cType; // 课程类型列

    @FXML
    private TableColumn<Course, Integer> cPeriod; // 课程学时列

    @FXML
    private TableColumn<Course, String> cSchool; // 课程所属学院列

    @FXML
    private TableColumn<Course, String> cTime; // 课程时间列

    @FXML
    private TableColumn<Course, String> cLocation; // 课程地点列

    @FXML
    private TableColumn<Course, Integer> cCapacity; // 课程容量列

    @FXML
    private TableView<TeacherPrivate> tableTeacher; // 教师私密信息表格

    @FXML
    private TableColumn<TeacherPrivate, String> tid; // 教师ID列

    @FXML
    private TableColumn<TeacherPrivate, String> tName; // 教师姓名列

    @FXML
    private TableColumn<TeacherPrivate, String> tSex; // 教师性别列

    @FXML
    private TableColumn<TeacherPrivate, String> tSchool; // 教师所属学院列

    @FXML
    private TableColumn<TeacherPrivate, String> tTitle; // 教师职称列

    @FXML
    private TableColumn<TeacherPrivate, String> tAddress; // 教师地址列

    @FXML
    private TableColumn<TeacherPrivate, String> tEmail; // 教师邮箱列

    @FXML
    private TableView<StudentPrivate> tableStudent; // 学生私密信息表格

    @FXML
    private TableColumn<StudentPrivate, String> sid; // 学生ID列

    @FXML
    private TableColumn<StudentPrivate, String> sName; // 学生姓名列

    @FXML
    private TableColumn<StudentPrivate, String> sSex; // 学生性别列

    @FXML
    private TableColumn<StudentPrivate, String> sSchool; // 学生所属学院列

    @FXML
    private TableColumn<StudentPrivate, String> sClass; // 学生班级列

    @FXML
    private TableColumn<StudentPrivate, String> sEmail; // 学生邮箱列

    /**
     * 控制器初始化方法，用于在界面加载时初始化各种数据。
     * @param url 页面的URL信息，用于加载资源。
     * @param resourceBundle 页面的资源包，用于国际化等。
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 注册当前控制器到AdminContext，以便于其他地方获取和使用
        AdminContext.controllers.put(this.getClass().getSimpleName(), this);
        
        // 初始化管理员DAO，用于后续获取管理员信息
        AdminDAO adminDAO = new AdminDAO();
        // 通过AdminContext获取AdminLogin控制器实例，用于获取管理员ID
        AdminLogin controller = (AdminLogin) AdminContext.controllers.get(AdminLogin.class.getSimpleName());
        // 获取并显示管理员ID
        String aid = controller.getAid();
        this.aid.setText(aid);
        // 根据管理员ID获取管理员名称，并显示
        this.aName.setText(adminDAO.getPublic(aid).getAName());

        // 初始化课程DAO，用于后续获取所有课程信息
        CourseDAO courseDAO = new CourseDAO();
        // 获取所有课程列表
        List<Course> courseList = courseDAO.getAll();
        // 如果课程列表不为空，则将其加载到表格中
        if (courseList != null && !courseList.isEmpty()) {
            ObservableList<Course> courses = FXCollections.observableArrayList(courseList);
            tableCourse.setItems(courses);
            // 配置表格各列的数据绑定
            cid.setCellValueFactory(new PropertyValueFactory<Course, String>("cid"));
            cName.setCellValueFactory(new PropertyValueFactory<Course, String>("c_name"));
            cType.setCellValueFactory(new PropertyValueFactory<Course, String>("c_type"));
            cPeriod.setCellValueFactory(new PropertyValueFactory<Course, Integer>("c_period"));
            cSchool.setCellValueFactory(new PropertyValueFactory<Course, String>("c_school"));
            cTime.setCellValueFactory(new PropertyValueFactory<Course, String>("c_time"));
            cLocation.setCellValueFactory(new PropertyValueFactory<Course, String>("c_location"));
            cCapacity.setCellValueFactory(new PropertyValueFactory<Course, Integer>("c_capacity"));
        } else {
            // 如果课程列表为空，则清空表格
            tableCourse.setItems(FXCollections.emptyObservableList());
        }

        // 初始化教师DAO，用于后续获取所有教师信息
        TeacherDAO teacherDAO = new TeacherDAO();
        // 获取所有教师列表
        List<TeacherPrivate> teachersList = teacherDAO.getAll();
        // 如果教师列表不为空，则将其加载到表格中
        if (teachersList != null && !teachersList.isEmpty()) {
            ObservableList<TeacherPrivate> teachers = FXCollections.observableArrayList(teachersList);
            tableTeacher.setItems(teachers);
            // 配置表格各列的数据绑定
            tid.setCellValueFactory(new PropertyValueFactory<>("tid"));
            tName.setCellValueFactory(new PropertyValueFactory<>("t_name"));
            tSex.setCellValueFactory(new PropertyValueFactory<>("t_sex"));
            tSchool.setCellValueFactory(new PropertyValueFactory<>("t_school"));
            tTitle.setCellValueFactory(new PropertyValueFactory<>("t_title"));
            tAddress.setCellValueFactory(new PropertyValueFactory<>("t_address"));
            tEmail.setCellValueFactory(new PropertyValueFactory<>("t_email"));
        } else {
            // 如果教师列表为空，则清空表格
            tableTeacher.setItems(FXCollections.emptyObservableList());
        }

        // 初始化学生DAO，用于后续获取所有学生信息
        StudentDAO studentDAO = new StudentDAO();
        // 获取所有学生列表
        List<StudentPrivate> studentList = studentDAO.getAll();
        // 如果学生列表不为空，则将其加载到表格中
        if (studentList != null && !studentList.isEmpty()) {
            ObservableList<StudentPrivate> students = FXCollections.observableArrayList(studentList);
            tableStudent.setItems(students);
            // 配置表格各列的数据绑定
            sid.setCellValueFactory(new PropertyValueFactory<>("sid"));
            sName.setCellValueFactory(new PropertyValueFactory<>("s_name"));
            sSex.setCellValueFactory(new PropertyValueFactory<>("s_sex"));
            sSchool.setCellValueFactory(new PropertyValueFactory<>("s_school"));
            sClass.setCellValueFactory(new PropertyValueFactory<>("s_class"));
            sEmail.setCellValueFactory(new PropertyValueFactory<>("s_email"));
        } else {
            // 如果学生列表为空，则清空表格
            tableStudent.setItems(FXCollections.emptyObservableList());
        }
    }

    /**
     * 触发修改密码的操作。
     * 此方法通过调用AdminApplication类中的changePassword方法来实现密码更改的功能。
     * 该方法使用了@FXML注解，表明它是一个由FXML控制器定义的方法，可能与UI元素的事件处理相关。
     * 
     * @throws IOException 如果在改变密码的过程中发生I/O错误。
     */
    @FXML
    void alterEvent() throws IOException {
        new AdminApplication().changePassword();
    }

    /**
     * 设置界面元素的可见性状态。
     * 此方法用于根据业务逻辑需要，批量调整界面中各组件的可见性。
     * 具体来说，它隐藏了与课程、学生、教师相关的标签和表格，
     * 同时使添加、删除、修改和获取操作的按钮可见。
     * 这样做的目的是为了在特定的使用场景下，优化用户界面，
     * 仅显示与当前操作相关的控制元素，提高用户的操作便捷性和界面的清晰度。
     */
    void setVisible() {
        labelCourse.setVisible(false); // 隐藏课程标签
        tableCourse.setVisible(false); // 隐藏课程表格
        labelStudent.setVisible(false); // 隐藏学生标签
        tableStudent.setVisible(false); // 隐藏学生表格
        labelTeacher.setVisible(false); // 隐藏教师标签
        tableTeacher.setVisible(false); // 隐藏教师表格
        buttonAdd.setVisible(true); // 显示添加按钮
        buttonDelete.setVisible(true); // 显示删除按钮
        buttonModify.setVisible(true); // 显示修改按钮
        buttonGet.setVisible(true); // 显示获取按钮
    }

    /**
     * 处理课程事件的方法。
     * 此方法的目的是在用户界面中显示课程相关的元素。
     * 它通过调用setVisible()方法来设置整体可见性，然后分别将课程标签和课程表格的可见性设置为true。
     * 这样做使得用户能够在界面中看到和操作课程相关的部分。
     */
    @FXML
    void courseEvent() {
        setVisible();
        labelCourse.setVisible(true);
        tableCourse.setVisible(true);
    }

    /**
     * 处理学生相关事件的函数。
     * 此函数的目的是在用户界面中显示学生相关的元素，如标签和表格。
     * 通过调用setVisible()方法来设置整体可见性，然后分别设置labelStudent和tableStudent的可见性为true。
     * @FXML 标记表示此方法是由FXML控制器调用的，用于与UI元素交互。
     */
    @FXML
    void studentEvent() {
        setVisible();
        labelStudent.setVisible(true);
        tableStudent.setVisible(true);
    }

    /**
     * 处理与教师相关的事件。
     * 此方法被设计为响应特定的教师相关事件，例如在用户界面中点击教师相关的按钮或链接。
     * 当这个事件发生时，方法的目的是使教师相关信息在用户界面中变得可见。
     * 
     * @FXML 标记表示这个方法是由FXML控制器使用的，用于处理UI元素的事件。
     */
    @FXML
    void teacherEvent() {
        // 设置教师相关信息可见
        setVisible();
        // 显示教师表格
        tableTeacher.setVisible(true);
        // 显示教师标签
        labelTeacher.setVisible(true);
    }

    /**
     * 根据当前视图的可见性，添加相应的事件。
     * 此方法检查表格视图的可见性，以确定应添加课程、教师还是学生。
     * 如果多个表格视图可见，则将针对每个可见的表格视图调用相应的添加方法。
     * 
     * @throws IOException 如果发生I/O错误，例如读取FXML文件失败。
     */
    @FXML
    void addEvent() throws IOException {
        // 检查课程表格是否可见，如果可见，则添加课程
        if (tableCourse.isVisible()) {
            new AdminApplication().addCourse();
        }
        // 检查教师表格是否可见，如果可见，则添加教师
        if (tableTeacher.isVisible()) {
            new AdminApplication().addTeacher();
        }
        // 检查学生表格是否可见，如果可见，则添加学生
        if (tableStudent.isVisible()) {
            new AdminApplication().addStudent();
        }
    }

    /**
     * 将课程添加到课程表中。
     * 
     * 此方法用于将一个新的课程对象添加到课程表的列表中。通过调用课程表的getItems方法获取列表对象，
     * 然后使用add方法将新的课程对象添加到列表中，从而实现课程的添加操作。
     * 
     * @param course 要添加到课程表中的课程对象。该对象应包含课程的相关信息，如课程名称、教师等。
     */
    void addCourseToTable(Course course) {
        tableCourse.getItems().add(course);
    }

    /**
     * 将教师信息添加到教师列表中。
     * 
     * 此方法用于将一个新的教师对象添加到教师数据表中。通过调用教师数据表的getItems方法获取当前所有教师列表，
     * 然后使用add方法将新的教师对象添加到列表末尾。这样，新教师信息就会显示在教师列表中。
     * 
     * @param teacher 要添加到教师列表的教师对象。该对象应包含教师的详细信息。
     */
    void addTeacherToTable(TeacherPrivate teacher) {
        tableTeacher.getItems().add(teacher);
    }

    /**
     * 将学生信息添加到表格视图中。
     * 
     * 此方法用于将一个新的学生对象添加到表格视图的项目列表中。表格视图是一种用户界面元素，
     * 用于显示和管理数据集合。通过调用表格视图的getItems方法获取项目列表，然后使用add方法将新学生对象添加到列表中。
     * 
     * @param student 要添加到表格的学生对象。此对象应包含学生的所有相关属性和方法。
     */
    void addStudentToTable(StudentPrivate student) {
        tableStudent.getItems().add(student);
    }

    /**
     * 删除事件处理方法。
     * 根据当前显示的表格（课程、教师、学生），弹出确认对话框后删除选中的项。
     * 如果没有选中项或删除操作失败，则会显示相应的提示信息。
     * 
     * @throws AdminException 删除操作相关的异常。
     */
    @FXML
    void deleteEvent() {
        try {
            // 如果课程表格可见，处理课程的删除逻辑
            if (tableCourse.isVisible()) {
                Course course = tableCourse.getSelectionModel().getSelectedItem();
                int index = tableCourse.getSelectionModel().getSelectedIndex();
                // 如果没有选中项，抛出无选择异常
                if (index < 0) {
                    throw new AdminException.NoSelectionException();
                }
                // 显示删除确认对话框，如果确认，则尝试删除课程
                if (new AdminApplication().showMessage("删除确认", "你确定删除" + course.getCName() + "吗？",
                        Alert.AlertType.CONFIRMATION, 1)) {
                    if (new CourseDAO().deleteCourse(course)) {
                        tableCourse.getItems().remove(index);
                        tableCourse.refresh();
                        new AdminApplication().showMessage("删除成功", "删除成功", Alert.AlertType.INFORMATION, 0);
                    } else {
                        throw new AdminException.DeleteException();
                    }
                }
            }
            // 如果教师表格可见，处理教师的删除逻辑
            if (tableTeacher.isVisible()) {
                TeacherPrivate teacher = tableTeacher.getSelectionModel().getSelectedItem();
                int index = tableTeacher.getSelectionModel().getSelectedIndex();
                // 如果没有选中项，抛出无选择异常
                if (index < 0) {
                    throw new AdminException.NoSelectionException();
                }
                // 显示删除确认对话框，如果确认，则尝试删除教师
                if (new AdminApplication().showMessage("删除确认", "你确定删除" + teacher.getTName() + "吗？",
                        Alert.AlertType.CONFIRMATION, 1)) {
                    if (new TeacherDAO().delete(teacher.getTid())) {
                        tableTeacher.getItems().remove(index);
                        tableTeacher.refresh();
                        new AdminApplication().showMessage("删除成功", "删除成功", Alert.AlertType.INFORMATION, 0);
                    } else {
                        throw new AdminException.DeleteException();
                    }
                }
            }
            // 如果学生表格可见，处理学生的删除逻辑
            if (tableStudent.isVisible()) {
                StudentPrivate student = tableStudent.getSelectionModel().getSelectedItem();
                int index = tableStudent.getSelectionModel().getSelectedIndex();
                // 如果没有选中项，抛出无选择异常
                if (index < 0) {
                    throw new AdminException.NoSelectionException();
                }
                // 显示删除确认对话框，如果确认，则尝试删除学生
                if (new AdminApplication().showMessage("删除确认", "你确定删除" + student.getSName() + "吗？",
                        Alert.AlertType.CONFIRMATION, 1)) {
                    if (new StudentDAO().delete(student.getSid())) {
                        tableStudent.getItems().remove(index);
                        tableStudent.refresh();
                        new AdminApplication().showMessage("删除成功", "删除成功", Alert.AlertType.INFORMATION, 0);
                    } else {
                        throw new AdminException.DeleteException();
                    }
                }
            }
        } catch (AdminException.NoSelectionException e) {
            // 捕获无选择异常，显示警告信息
            new AdminApplication().showMessage("提示", "请选择要删除的对象", Alert.AlertType.WARNING, 0);
        } catch (AdminException.DeleteException e) {
            // 捕获删除异常，显示错误信息
            new AdminApplication().showMessage("删除失败", "删除失败，请重试", Alert.AlertType.ERROR, 0);
        }
    }

    /**
     * 根据当前显示的表格类型，调用相应的新建操作。
     * 此方法检查当前是否显示了课程、教师或学生表格，并根据显示的表格类型发起相应的修改操作。
     * 该方法利用了JavaFX的场景图形API来检查表格的可见性，并通过调用AdminApplication类的相关方法来执行修改操作。
     * 
     * @throws IOException 如果在执行修改操作过程中发生了I/O错误。
     */
    @FXML
    void modifyEvent() throws IOException {
        // 检查课程表格是否可见，如果可见，则调用修改课程的方法
        if (tableCourse.isVisible()) {
            new AdminApplication().modifyCourse();
        }
        // 检查教师表格是否可见，如果可见，则调用修改教师的方法
        if (tableTeacher.isVisible()) {
            new AdminApplication().modifyTeacher();
        }
        // 检查学生表格是否可见，如果可见，则调用修改学生的方法
        if (tableStudent.isVisible()) {
            new AdminApplication().modifyStudent();
        }
    }

    /**
     * 获取选中的课程
     * 
     * 该方法用于从课程选择表中获取当前选中的课程项。如果用户已选择课程，
     * 则此方法将返回选中课程的对象；如果没有课程被选中，则返回null。
     * 
     * @return 返回选中的课程对象，如果没有选中任何课程，则返回null。
     */
    Course getCourse() {
        // 从课程选择表中获取当前选中的课程
        return tableCourse.getSelectionModel().getSelectedItem();
    }

    /**
     * 获取当前选中的教师信息。
     * 
     * 该方法用于从教师列表中获取当前被选中的教师对象。如果列表中没有选中任何教师，
     * 则返回null。使用此方法前，需要确保教师列表（tableTeacher）已经被正确填充并显示了教师数据。
     * 
     * @return 当前选中的教师对象，如果没有任何教师被选中，则返回null。
     */
    TeacherPrivate getTeacher() {
        // 从教师列表的选择模型中获取当前选中的教师项
        return tableTeacher.getSelectionModel().getSelectedItem();
    }

    /**
     * 获取当前选中的学生对象。
     * 
     * 本方法用于从学生列表中获取当前被选中的学生对象。通过调用tableStudent的选择模型，
     * 获取选中项并返回。如果没有学生被选中，或者列表为空，则返回null。
     * 
     * @return 当前选中的学生对象，如果不存在则返回null。
     */
    StudentPrivate getStudent() {
        return tableStudent.getSelectionModel().getSelectedItem();
    }

    /**
     * 更新表格中选中课程的信息。
     * 此方法用于替换表格中已选课程的详细信息，以确保表格数据与实际课程信息保持同步。
     * 
     * @param course 要更新的新课程信息。此参数包含了即将替换现有课程数据的全部信息。
     */
    void modifyCourseToTable(Course course) {
        // 通过获取选中课程的索引，然后使用set方法更新表格中该位置的课程信息
        tableCourse.getItems().set(tableCourse.getSelectionModel().getSelectedIndex(), course);
    }

    /**
     * 修改表格中选中教师的信息。
     * 
     * 此方法用于更新教师信息表格中已选中教师的详细信息。它通过获取选中教师的索引，
     * 然后使用新教师对象替换该索引处的教师对象，从而实现更新教师信息的目的。
     * 这种设计允许用户在界面上直接看到信息的更改，提高了交互的直观性。
     * 
     * @param teacher 要更新的教师对象，包含新的教师信息。
     */
    void modifyTeacherToTable(TeacherPrivate teacher) {
        // 获取当前选中的教师索引
        int selectedIndex = tableTeacher.getSelectionModel().getSelectedIndex();
        // 如果有教师被选中，则更新该教师的信息
        if (selectedIndex != -1) {
            tableTeacher.getItems().set(selectedIndex, teacher);
        }
    }

    /**
     * 更新表格中选中学生的信息。
     * 此方法用于修改表格视图中已选中学生的数据，通过获取选中学生的索引，
     * 然后使用新学生对象替换掉对应索引处的学生对象，从而实现表格数据的更新。
     * 
     * @param student 要更新进表格的学生对象，包含新的学生信息。
     */
    void modifyStudentToTable(StudentPrivate student) {
        // 获取当前选中的学生索引
        int selectedIndex = tableStudent.getSelectionModel().getSelectedIndex();
        // 如果有学生被选中，则更新选中学生的数据
        if (selectedIndex != -1) {
            tableStudent.getItems().set(selectedIndex, student);
        }
    }

    /**
     * 根据不同表格的可见性，获取对应的数据。
     * 此方法用于处理获取课程、教师、学生数据的事件。
     * 如果特定的表格在用户界面中可见，则尝试获取对应类型的数据。
     * 
     * @throws IOException 如果在获取数据过程中发生I/O错误。
     */
    @FXML
    void getEvent() throws IOException {
        // 检查课程表格是否可见，如果可见，则获取课程数据
        if (tableCourse.isVisible()) {
            new AdminApplication().getCourse();
        }
        // 检查教师表格是否可见，如果可见，则获取教师数据
        if (tableTeacher.isVisible()) {
            new AdminApplication().getTeacher();
        }
        // 检查学生表格是否可见，如果可见，则获取学生数据
        if (tableStudent.isVisible()) {
            new AdminApplication().getStudent();
        }
    }

}
