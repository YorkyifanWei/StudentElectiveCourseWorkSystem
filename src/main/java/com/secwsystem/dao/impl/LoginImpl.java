package com.secwsystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.secwsystem.dao.Login;
import com.secwsystem.dao.pojo.DBConnection;

/**
 * LoginImpl类实现了Login接口，提供了针对学生、教师和管理员的登录验证功能。
 * 通过调用不同的静态方法，可以对相应的用户类型进行身份验证。
 */
public class LoginImpl implements Login {

    /**
     * 验证用户登录。
     * 根据用户类型（学生、教师、管理员）查询相应的数据库表，验证用户身份。
     *
     * @param target 用户类型标识，"s"代表学生，"t"代表教师，"a"代表管理员。
     * @param userid 用户的ID。
     * @param password 用户的密码。
     * @return 登录验证结果，成功返回true，失败返回false。
     */
    private static boolean login(String target, String userid, String password) {
        boolean result;
        // 根据用户类型选择对应的数据库表
        String dbName = switch (target) {
            case "s" -> DBConnection.student;
            case "t" -> DBConnection.teacher;
            case "a" -> DBConnection.admin;
            default -> null;
        };
        Connection conn = DBConnection.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 如果连接为空，直接返回false
        if (conn == null || dbName == null) {
            return false;
        }

        String sql = "SELECT * FROM " + dbName + " WHERE " +
                target + "id = ? AND " + target + "_password = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            result = rs.next();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        finally {
            DBConnection.closeConn(conn,pstmt,rs);
        }
        return result;
    }

    /**
     * 学生登录验证方法。
     * 调用login方法，以学生用户类型进行登录验证。
     *
     * @param user 学生用户名。
     * @param password 学生用户密码。
     * @return 登录验证结果，成功返回true，失败返回false。
     */
    public static boolean loginStudent(String user, String password){
        return login("s", user, password);
    }

    /**
     * 教师登录验证方法。
     * 调用login方法，以教师用户类型进行登录验证。
     *
     * @param user 教师用户名。
     * @param password 教师用户密码。
     * @return 登录验证结果，成功返回true，失败返回false。
     */
    public static boolean loginTeacher(String user, String password){
        return login("t", user, password);
    }

    /**
     * 管理员登录验证方法。
     * 调用login方法，以管理员用户类型进行登录验证。
     *
     * @param user 管理员用户名。
     * @param password 管理员用户密码。
     * @return 登录验证结果，成功返回true，失败返回false。
     */
    public static boolean loginAdmin(String user, String password){
        return login("a", user, password);
    }
}
