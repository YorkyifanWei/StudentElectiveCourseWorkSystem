package com.secwsystem.dao.pojo;

public class TeacherPublic {
    private String tid;// 职工号
    private String tName;// 姓名
    private String tTitle;// 职称
    private String tSchool;// 学院
    private String tSex;// 性别
    private String tPhoneNumber;// 电话
    private String tEmail;// 邮箱
    private String tAddress;// 办公室

    public TeacherPublic() {
    }

    public TeacherPublic(String tid, String tName, String tTitle, String tSchool, String tSex, String tPhoneNumber, String tEmail, String tAddress) {
        this.tid = tid;
        this.tName = tName;
        this.tTitle = tTitle;
        this.tSchool = tSchool;
        this.tSex = tSex;
        this.tPhoneNumber = tPhoneNumber;
        this.tEmail = tEmail;
        this.tAddress = tAddress;
    }

    public String getTid() { return tid; }

    public void setTid(String tid) { this.tid = tid; }

    public String getTName() { return tName; }

    public void setTName(String tName) { this.tName = tName; }

    public String getTTitle() { return tTitle; }

    public void setTTitle(String tTitle) { this.tTitle = tTitle; }

    public String getTSchool() { return tSchool; }

    public void setTSchool(String tSchool) { this.tSchool = tSchool; }

    public String getTSex() { return tSex; }

    public void setTSex(String tSex) { this.tSex = tSex; }

    public String getTPhoneNumber() { return tPhoneNumber; }

    public void setTPhoneNumber(String tPhoneNumber) { this.tPhoneNumber = tPhoneNumber; }

    public String getTEmail() { return tEmail; }

    public void setTEmail(String tEmail) { this.tEmail = tEmail; }


    public String getTAddress() { return tAddress; }

    public void setTAddress(String tAddress) { this.tAddress = tAddress; }

}
