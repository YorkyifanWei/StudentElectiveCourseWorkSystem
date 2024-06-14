package com.secwsystem.dao.pojo;

public class StudentPublic {
    private String sid;// 学号
    private String sName;// 姓名
    private String sClass;// 班级
    private String sSex;// 性别
    private String sSchool;// 学院
    private String sPhoneNumber;// 电话
    private String sEmail;// 邮箱


    public StudentPublic() {
    }

    public StudentPublic(String sid, String sName, String sClass, String sSex, String sSchool, String sPhoneNumber, String sEmail) {
        this.sid = sid;
        this.sName = sName;
        this.sClass = sClass;
        this.sSex = sSex;
        this.sSchool = sSchool;
        this.sPhoneNumber = sPhoneNumber;
        this.sEmail = sEmail;
    }

    // getter and setter
    public String getSid() { return sid; }

    public void setSid(String sid) { this.sid = sid; }

    public String getSName() { return sName; }

    public void setSName(String sName) { this.sName = sName; }

    public String getSClass() { return sClass; }

    public void setSClass(String sClass) { this.sClass = sClass; }

    public String getSSex() { return sSex; }

    public void setSSex(String sSex) { this.sSex = sSex; }

    public String getSSchool() { return sSchool; }

    public void setSSchool(String sSchool) { this.sSchool = sSchool; }

    public String getSPhoneNumber() { return sPhoneNumber; }

    public void setSPhoneNumber(String sPhoneNumber) { this.sPhoneNumber = sPhoneNumber; }

    public String getSEmail() { return sEmail; }

    public void setSEmail(String sEmail) { this.sEmail = sEmail; }
}
