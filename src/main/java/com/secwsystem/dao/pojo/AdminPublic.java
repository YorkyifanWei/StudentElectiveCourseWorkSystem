package com.secwsystem.dao.pojo;

public class AdminPublic {
    private String aid;// 管理员id
    private String a_name;// 管理员姓名

    public AdminPublic() {
    }

    public AdminPublic(String aid, String a_name) {
        this.aid = aid;
        this.a_name = a_name;
    }

    // getters and setters
    public String getAid() { return aid; }

    public void setAid(String aid) { this.aid = aid; }

    public String getA_name() { return a_name; }

    public void setA_name(String a_name) { this.a_name = a_name; }
}
