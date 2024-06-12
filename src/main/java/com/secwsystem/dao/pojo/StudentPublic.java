package com.secwsystem.dao.pojo;

public class StudentPublic {
    private String sid;// 学号
    private String s_name;// 姓名
    private String s_class;// 班级
    private String s_sex;// 性别
    private String s_school;// 学院
    private String s_phone;// 电话
    private String s_email;// 邮箱


    public StudentPublic() {
    }

    public StudentPublic(String sid, String s_name, String s_class, String s_sex, String s_school, String s_phone, String s_email) {
        this.sid = sid;
        this.s_name = s_name;
        this.s_class = s_class;
        this.s_sex = s_sex;
        this.s_school = s_school;
        this.s_phone = s_phone;
        this.s_email = s_email;
    }

    // getter and setter
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

    public String getS_sex() {
        return s_sex;
    }

    public void setS_sex(String s_sex) {
        this.s_sex = s_sex;
    }

    public String getS_school() {
        return s_school;
    }

    public void setS_school(String s_school) {
        this.s_school = s_school;
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
}
