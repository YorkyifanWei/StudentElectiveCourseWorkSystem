package com.secwsystem.dao.pojo;

public class AdminPrivate {
    private long id;// 主键
    private String aid;// 管理员id
    private String a_name;// 管理员姓名
    private String a_password;// 管理员密码

    public AdminPrivate() {
    }

    public AdminPrivate(long id, String aid, String a_name, String a_password) {
        this.id = id;
        this.aid = aid;
        this.a_name = a_name;
        this.a_password = a_password;
    }

    // getters and setters
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getAid() { return aid; }

    public void setAid(String aid) { this.aid = aid; }

    public String getA_name() { return a_name; }

    public void setA_name(String a_name) { this.a_name = a_name; }

    public String getA_password() { return a_password; }

    public void setA_password(String a_password) { this.a_password = a_password; }
}
