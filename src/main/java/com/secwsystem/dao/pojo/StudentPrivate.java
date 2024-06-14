package com.secwsystem.dao.pojo;

public class StudentPrivate {
    private long id;// 主键
    private String sid;// 学号
    private String sName;// 姓名
    private String sClass;// 班级
    private String sSchool;// 学院
    private String sSex;// 性别
    private String sPhoneNumber;// 电话
    private String sEmail;// 邮箱
    private String sIdcard;// 身份证
    private String sPassword;// 密码
    private String sEntertime;// 入学时间

    public StudentPrivate() {
    }

    public StudentPrivate(Long id, String sid, String sName, String sClass, String sSchool, String sSex, String sPhoneNumber, String sEmail, String sIdcard, String sPassword, String sEntertime) {
        this.id = id;
        this.sid = sid;
        this.sName = sName;
        this.sClass = sClass;
        this.sSchool = sSchool;
        this.sSex = sSex;
        this.sPhoneNumber = sPhoneNumber;
        this.sEmail = sEmail;
        this.sIdcard = sIdcard;
        this.sPassword = sPassword;
        this.sEntertime = sEntertime;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getSid() { return sid; }

    public void setSid(String sid) { this.sid = sid; }

    public String getSName() { return sName; }

    public void setSName(String sName) { this.sName = sName; }

    public String getSClass() { return sClass; }

    public void setSClass(String sClass) { this.sClass = sClass; }

    public String getSSchool() { return sSchool; }

    public void setSSchool(String sSchool) { this.sSchool = sSchool; }

    public String getSSex() { return sSex; }

    public void setSSex(String sSex) { this.sSex = sSex; }

    public String getSPhoneNumber() { return sPhoneNumber; }

    public void setSPhoneNumber(String sPhoneNumber) { this.sPhoneNumber = sPhoneNumber; }

    public String getSEmail() { return sEmail; }

    public void setSEmail(String sEmail) { this.sEmail = sEmail; }

    public String getSIdcard() { return sIdcard; }

    public void setSIdcard(String sIdcard) { this.sIdcard = sIdcard; }

    public String getSPassword() { return sPassword; }

    public void setSPassword(String sPassword) { this.sPassword = sPassword; }

    public String getSEntertime() { return sEntertime; }

    public void setSEntertime(String sEntertime) { this.sEntertime = sEntertime; }
}
