package com.secwsystem.dao.pojo;

public class AdminPublic {
    private String aid;// 管理员id
    private String aName;// 管理员姓名

    public AdminPublic() {
    }

    public AdminPublic(String aid, String aName) {
        this.aid = aid;
        this.aName = aName;
    }

    // getters and setters
    public String getAid() { return aid; }

    public void setAid(String aid) { this.aid = aid; }

    public String getAName() { return aName; }

    public void setAName(String aName) { this.aName = aName; }
}
