package com.secwsystem.dao;

/**
 * 登录接口，定义了三种角色（学生、教师、管理员）的登录方法。
 * 目前所有登录方法的实现均返回固定值false，这是Java8的特性，当这个返回值不存在就行了
 */
public interface Login {

    /**
     * 学生登录方法。
     *
     * @param user 学生用户名。
     * @param password 学生密码。
     * @return 登录是否成功，目前固定返回false。
     */
    static boolean loginStudent(String user, String password) {
        return false;
    }

    /**
     * 教师登录方法。
     *
     * @param user 教师用户名。
     * @param password 教师密码。
     * @return 登录是否成功，目前固定返回false。
     */
    static boolean loginTeacher(String user, String password) {
        return false;
    }

    /**
     * 管理员登录方法。
     *
     * @param user 管理员用户名。
     * @param password 管理员密码。
     * @return 登录是否成功，目前固定返回false。
     */
    static boolean loginAdmin(String user, String password) {
        return false;
    }
}
