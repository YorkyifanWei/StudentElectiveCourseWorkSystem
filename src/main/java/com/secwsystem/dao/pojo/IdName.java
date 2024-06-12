package com.secwsystem.dao.pojo;

/**
 * IdName类用于封装ID和名称的组合。
 * 这是一个简单的数据持有类，提供了对ID和名称的封装。
 */
public class IdName {
    private String id;// 学生或老师的id
    private String name;// 学生或老师的名字

    /**
     * 默认构造函数。
     * 提供了一个无参的构造方法，用于创建一个空的IdName对象。
     */
    public IdName() {
    }

    /**
     * 构造函数用于初始化ID和名称。
     *
     * @param id 身份证或其他唯一标识符。
     * @param name 物品、人员或资源的名称。
     */
    public IdName(String id, String name) {
        this.id = id;
        this.name = name;
    }


    // getters and setters
    public String getId() { return id; }

    public String getName() { return name; }

    public void setId(String id) { this.id = id; }

    public void setName(String name) { this.name = name; }
}
