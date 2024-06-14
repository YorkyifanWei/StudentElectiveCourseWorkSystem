package com.secwsystem.dao.pojo;

public class AdminPrivate {
    private long id;// 主键
    private String aid;// 管理员id
    private String aName;// 管理员姓名
    private String aPassword;// 管理员密码

    public AdminPrivate() {
    }

    public AdminPrivate(long id, String aid, String aName, String aPassword) {
        this.id = id;
        this.aid = aid;
        this.aName = aName;
        this.aPassword = aPassword;
    }

    // getters and setters
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getAid() { return aid; }

    public void setAid(String aid) { this.aid = aid; }

    public String getAName() { return aName; }

    public void setAName(String aName) { this.aName = aName; }

    public String getAPassword() { return aPassword; }

    public void setAPassword(String aPassword) { this.aPassword = aPassword; }
}
