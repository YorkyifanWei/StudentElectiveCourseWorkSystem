package com.secwsystem.ctrl.admin;

import com.secwsystem.app.Admin;
import com.secwsystem.dao.impl.TeacherDAO;
import com.secwsystem.dao.pojo.TeacherPrivate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTeacher {

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Stage stage;

    @FXML
    private Button btn_set;

    @FXML
    private Button btn_cancel;

    @FXML
    private TextField TeaId;

    @FXML
    private TextField TeaPassword;

    @FXML
    private TextField TeaName;

    @FXML
    private TextField TeaSex;

    @FXML
    private TextField TeaIdcard;

    @FXML
    private TextField TeaPhone;

    @FXML
    private TextField TeaSchool;

    @FXML
    private TextField TeaTitle;

    @FXML
    private TextField TeaEmail;

    @FXML
    private TextField TeaAddress;

    @FXML
    void CancelEvent() {
        stage.close();
    }

    @FXML
    void SetEvent() {
        //把所有输入信息上传到数据库
        //我不知道后端数据格式，暂时没做防恶意输入，麻烦后端人员告知

        try {
            TeacherPrivate teacher = new TeacherPrivate();
            TeacherDAO teacherDAO = new TeacherDAO();
            if (TextFieldIsNull()) {
                throw new AdminException.TextFieldNullException();
            }
            if(teacherDAO.getPrivate(TeaId.getText().trim()) != null){
                throw new AdminException.TeacherExistException();
            }
            teacher.setTid(TeaId.getText().trim());
            teacher.setTName(TeaName.getText().trim());
            teacher.setTSex(TeaSex.getText().trim());
            teacher.setTSchool(TeaSchool.getText().trim());
            teacher.setTIdcard(TeaIdcard.getText().trim());
            teacher.setTPhoneNumber(TeaPhone.getText().trim());
            teacher.setTEmail(TeaEmail.getText().trim());
            teacher.setTAddress(TeaAddress.getText().trim());
            teacher.setTPassword(TeaPassword.getText().trim());
            teacher.setTTitle(TeaTitle.getText().trim());
            if(teacherDAO.add(teacher)){
                AdminMainController controller = (AdminMainController) AdminContext.controllers.get(AdminMainController.class.getSimpleName());
                controller.AddTeaToTable(teacher);
                new Admin().showMessage("提示", "教师添加成功！", Alert.AlertType.INFORMATION, 0);
                stage.close();
            }else{
                throw new AdminException.AddException();
            }

        }catch(AdminException.TextFieldNullException e){
            new Admin().showMessage("提示", "请填写完整信息！", Alert.AlertType.ERROR, 0);
        } catch (AdminException.TeacherExistException e) {
            new Admin().showMessage("创建失败", "教师已存在！", Alert.AlertType.ERROR, 0);
        } catch (AdminException.AddException e) {
            new Admin().showMessage("创建异常", "添加失败,请重新添加！", Alert.AlertType.ERROR, 0);
        }
    }

    boolean TextFieldIsNull() {
        return TeaId.getText().trim().isEmpty() || TeaPassword.getText().trim().isEmpty()
                || TeaName.getText().trim().isEmpty() || TeaSex.getText().trim().isEmpty()
                || TeaIdcard.getText().trim().isEmpty() || TeaPhone.getText().trim().isEmpty()
                || TeaSchool.getText().trim().isEmpty() || TeaTitle.getText().trim().isEmpty()
                || TeaEmail.getText().trim().isEmpty() || TeaAddress.getText().trim().isEmpty();
    }
}
