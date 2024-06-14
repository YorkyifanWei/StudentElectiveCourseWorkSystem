package com.secwsystem.dao.pojo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接类，负责建立与数据库的连接，以及连接资源的释放。
 */
public class DBConnection {
    // 数据库连接URL
    private static String databaseURL = null;
    // 数据库用户名
    private static String userName = null;
    // 数据库密码
    private static String password = null;
    /*
      静态代码块用于初始化数据库连接配置。
      本块在类加载时执行，从资源文件"application.properties"中加载数据库连接的URL、用户名和密码。
      如果配置文件不存在或无法读取，将抛出异常。
     */
    static {
        Properties props = new Properties();
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("application.properties")) {
            // 检查配置文件是否存在，如果不存在，则抛出异常
            if (input == null) {
                throw new IllegalStateException("配置文件不存在");
            }
            // 加载配置文件内容
            props.load(input);
            // 从属性文件中获取数据库连接URL、用户名和密码
            databaseURL = props.getProperty("db.url");
            userName = props.getProperty("db.name");
            password = props.getProperty("db.password");
            if (password.isEmpty()) {
                password = null;
            }
        } catch (IOException e) {
            // 如果在读取配置文件时发生IO异常，则抛出运行时异常
            throw new RuntimeException(e);
        }
    }

    // 表名常量：管理员信息表
    public static final String admin = "admin_info";
    // 表名常量：课程信息表
    public static final String course = "course_info";
    // 表名常量：学生选课信息表
    public static final String studentCourse = "student_course";
    // 表名常量：学生信息表
    public static final String student = "student_info";
    // 表名常量：教师授课信息表
    public static final String teacherCourse = "teacher_course";
    // 表名常量：教师信息表
    public static final String teacher = "teacher_info";

    /**
     * 获取数据库连接。
     *
     * @return 返回数据库连接对象。
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(databaseURL, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
    /**
     * 关闭数据库连接资源。
     *
     * @param conn 数据库连接对象
     * @param ps 预编译的SQL语句对象
     * @param rs 结果集对象
     */
    public static void closeConn(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
