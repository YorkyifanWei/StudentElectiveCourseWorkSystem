package com.secwsystem.dao.pojo;

public class TeacherPrivate {
    private long id;// 主键
    private String tid;// 职工号
    private String tName;// 姓名
    private String tTile;// 职称
    private String tSchool;// 学院
    private String tSex;// 性别
    private String tPhoneNumber;// 电话
    private String tEmail;// 邮箱
    private String tIdcard;// 身份证
    private String tAddress;// 办公室
    private String tPassword;// 密码

    public TeacherPrivate() {
    }

    public TeacherPrivate(long id, String tid, String tName, String tTitle, String tSchool, String tSex, String tPhoneNumber, String tEmail, String tIdcard, String tAddress, String tPassword) {
        this.id = id;
        this.tid = tid;
        this.tName = tName;
        this.tTile = tTitle;
        this.tSchool = tSchool;
        this.tSex = tSex;
        this.tPhoneNumber = tPhoneNumber;
        this.tEmail = tEmail;
        this.tIdcard = tIdcard;
        this.tAddress = tAddress;
        this.tPassword = tPassword;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getTid() { return tid; }

    public void setTid(String tid) { this.tid = tid; }

    public String getTName() { return tName; }

    public void setTName(String tName) { this.tName = tName; }

    public String getTTitle() { return tTile; }

    public void setTTitle(String tTitle) { this.tTile = tTitle; }

    public String getTSchool() { return tSchool; }

    public void setTSchool(String tSchool) { this.tSchool = tSchool; }

    public String getTSex() { return tSex; }

    public void setTSex(String tSex) { this.tSex = tSex; }

    public String getTPhoneNumber() { return tPhoneNumber; }

    public void setTPhoneNumber(String tPhone) { this.tPhoneNumber = tPhone; }

    public String getTEmail() { return tEmail; }

    public void setTEmail(String tEmail) { this.tEmail = tEmail; }

    public String getTIdcard() { return tIdcard; }

    public void setTIdcard(String tIdcard) { this.tIdcard = tIdcard; }

    public String getTAddress() { return tAddress; }

    public void setTAddress(String tAddress) { this.tAddress = tAddress; }

    public String getTPassword() { return tPassword; }

    public void setTPassword(String tPassword) { this.tPassword = tPassword; }
}
