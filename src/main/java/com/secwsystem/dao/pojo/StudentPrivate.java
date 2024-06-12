package com.secwsystem.dao.pojo;

public class StudentPrivate {
    private long id;// 主键
    private String sid;// 学号
    private String s_name;// 姓名
    private String s_class;// 班级
    private String s_school;// 学院
    private String s_sex;// 性别
    private String s_phone;// 电话
    private String s_email;// 邮箱
    private String s_idcard;// 身份证
    private String s_password;// 密码
    private String s_entertime;// 入学时间

    public StudentPrivate() {
    }

    public StudentPrivate(Long id, String sid, String s_name, String s_class, String s_school, String s_sex, String s_phone, String s_email, String s_idcard, String s_password, String s_entertime) {
        this.id = id;
        this.sid = sid;
        this.s_name = s_name;
        this.s_class = s_class;
        this.s_school = s_school;
        this.s_sex = s_sex;
        this.s_phone = s_phone;
        this.s_email = s_email;
        this.s_idcard = s_idcard;
        this.s_password = s_password;
        this.s_entertime = s_entertime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_class() {
        return s_class;
    }

    public void setS_class(String s_class) {
        this.s_class = s_class;
    }

    public String getS_school() {
        return s_school;
    }

    public void setS_school(String s_school) {
        this.s_school = s_school;
    }

    public String getS_sex() {
        return s_sex;
    }

    public void setS_sex(String s_sex) {
        this.s_sex = s_sex;
    }

    public String getS_phone() {
        return s_phone;
    }

    public void setS_phone(String s_phone) {
        this.s_phone = s_phone;
    }

    public String getS_email() {
        return s_email;
    }

    public void setS_email(String s_email) {
        this.s_email = s_email;
    }

    public String getS_idcard() {
        return s_idcard;
    }

    public void setS_idcard(String s_idcard) {
        this.s_idcard = s_idcard;
    }

    public String getS_password() {
        return s_password;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }

    public String getS_entertime() {
        return s_entertime;
    }

    public void setS_entertime(String s_entertime) {
        this.s_entertime = s_entertime;
    }
}
