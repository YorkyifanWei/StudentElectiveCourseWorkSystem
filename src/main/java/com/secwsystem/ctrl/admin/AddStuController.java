package com.secwsystem.ctrl.admin;

import com.secwsystem.app.Admin;
import com.secwsystem.dao.impl.StudentDAO;
import com.secwsystem.dao.pojo.StudentPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStuController {

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage;

    @FXML
    private TextField StuId;

    @FXML
    private TextField StuPassword;

    @FXML
    private TextField StuName;

    @FXML
    private TextField StuSex;

    @FXML
    private TextField StuPhone;

    @FXML
    private TextField StuIdcard;

    @FXML
    private TextField StuSchool;

    @FXML
    private TextField StuClass;

    @FXML
    private TextField StuEntertime;

    @FXML
    private TextField StuEmail;

    @FXML
    private Button btn_set;

    @FXML
    private Button btn_cancel;

    @FXML
    void CancelEvent() {
        stage.close();
    }

    @FXML
    void SetEvent() {
        //把所有输入信息上传到数据库
        //我不知道后端数据格式，暂时没做防恶意输入，麻烦后端人员告知
        try{
            StudentPrivate student = new StudentPrivate();
            StudentDAO studentDAO = new StudentDAO();
            if(TextFieldIsNull()){
                throw new Exception.TextFieldNullException();
            }
            if(studentDAO.getPrivate(StuId.getText().trim()) != null){
                throw new Exception.StudentExistException();
            }
            student.setSid(StuId.getText().trim());
            student.setS_name(StuName.getText().trim());
            student.setS_sex(StuSex.getText().trim());
            student.setS_phone(StuPhone.getText().trim());
            student.setS_idcard(StuIdcard.getText().trim());
            student.setS_school(StuSchool.getText().trim());
            student.setS_class(StuClass.getText().trim());
            student.setS_email(StuEmail.getText().trim());
            student.setS_password(StuPassword.getText().trim());
            student.setS_entertime(StuEntertime.getText().trim());
            if(studentDAO.add(student)){
                MainController controller = (MainController) Context.controllers.get(MainController.class.getSimpleName());
                controller.AddStuToTable(student);
                new Admin().showMessage("提示","学生添加成功！", Alert.AlertType.INFORMATION,0);
                stage.close();
            }else{
                throw new Exception.AddException();
            }
        } catch (Exception.TextFieldNullException e) {
            new Admin().showMessage("提示","请填写完整信息！", Alert.AlertType.ERROR,0);
        } catch (Exception.StudentExistException e) {
            new Admin().showMessage("创建失败","该学生已存在！", Alert.AlertType.ERROR,0);
        } catch (Exception.AddException e) {
            new Admin().showMessage("创建异常","添加失败,请重新添加！", Alert.AlertType.ERROR,0);
        }
    }
    boolean TextFieldIsNull() {
        return StuId.getText().trim().isEmpty() || StuPassword.getText().trim().isEmpty() ||
                StuName.getText().trim().isEmpty() || StuSex.getText().trim().isEmpty() ||
                StuPhone.getText().trim().isEmpty() || StuIdcard.getText().trim().isEmpty() ||
                StuSchool.getText().trim().isEmpty() || StuClass.getText().trim().isEmpty() ||
                StuEmail.getText().trim().isEmpty();
    }

}
