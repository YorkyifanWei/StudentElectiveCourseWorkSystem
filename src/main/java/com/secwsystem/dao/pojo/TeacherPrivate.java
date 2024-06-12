package com.secwsystem.dao.pojo;

public class TeacherPrivate {
    private long id;// 主键
    private String tid;// 职工号
    private String t_name;// 姓名
    private String t_title;// 职称
    private String t_school;// 学院
    private String t_sex;// 性别
    private String t_phone;// 电话
    private String t_email;// 邮箱
    private String t_idcard;// 身份证
    private String t_address;// 办公室
    private String t_password;// 密码

    public TeacherPrivate() {
    }

    public TeacherPrivate(long id, String tid, String t_name, String t_title, String t_school, String t_sex, String t_phone, String t_email, String t_idcard, String t_address, String t_password) {
        this.id = id;
        this.tid = tid;
        this.t_name = t_name;
        this.t_title = t_title;
        this.t_school = t_school;
        this.t_sex = t_sex;
        this.t_phone = t_phone;
        this.t_email = t_email;
        this.t_idcard = t_idcard;
        this.t_address = t_address;
        this.t_password = t_password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_title() {
        return t_title;
    }

    public void setT_title(String t_title) {
        this.t_title = t_title;
    }

    public String getT_school() {
        return t_school;
    }

    public void setT_school(String t_school) {
        this.t_school = t_school;
    }

    public String getT_sex() {
        return t_sex;
    }

    public void setT_sex(String t_sex) {
        this.t_sex = t_sex;
    }

    public String getT_phone() {
        return t_phone;
    }

    public void setT_phone(String t_phone) {
        this.t_phone = t_phone;
    }

    public String getT_email() {
        return t_email;
    }

    public void setT_email(String t_email) {
        this.t_email = t_email;
    }

    public String getT_idcard() {
        return t_idcard;
    }

    public void setT_idcard(String t_idcard) {
        this.t_idcard = t_idcard;
    }

    public String getT_address() {
        return t_address;
    }

    public void setT_address(String t_address) {
        this.t_address = t_address;
    }

    public String getT_password() {
        return t_password;
    }

    public void setT_password(String t_password) {
        this.t_password = t_password;
    }
}
