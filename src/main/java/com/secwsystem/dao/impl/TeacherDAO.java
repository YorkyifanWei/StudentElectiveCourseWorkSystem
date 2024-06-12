package com.secwsystem.dao.impl;

import com.secwsystem.dao.DAOForAccount;
import com.secwsystem.dao.pojo.DBConnection;
import com.secwsystem.dao.pojo.TeacherPublic;
import com.secwsystem.dao.pojo.TeacherPrivate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 教师数据访问对象（DAO）的实现类，负责教师公共信息和私有信息的数据库操作。
 * 实现了DAOForAll接口，提供了对教师公共信息和私有信息的增删改查操作。
 */
public class TeacherDAO implements DAOForAccount<TeacherPublic, TeacherPrivate> {

    /**
     * 获取所有教师的私有信息。
     * 该方法通过查询数据库来获取所有教师的详细信息，并将这些信息封装成一个TeacherPrivate对象的列表。
     *
     * @return ArrayList<TeacherPrivate> 包含所有教师私有信息的列表。如果数据库连接失败，返回null。
     */
    public ArrayList<TeacherPrivate> getAll() {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<TeacherPrivate> teachers = new ArrayList<>();
        // 检查数据库连接是否成功
        if (conn == null) {
            return null;
        }
        try {
            // 准备SQL查询语句，查询teacher表中的所有数据
            String sql = "SELECT * FROM " + DBConnection.teacher;
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            // 遍历查询结果，创建并填充TeacherPrivate对象
            while (rs.next()) {
                TeacherPrivate teacher = new TeacherPrivate();
                // 从结果集中提取教师信息，并设置到TeacherPrivate对象中
                teacher.setId(rs.getLong("id"));
                teacher.setTid(rs.getString("tid"));
                teacher.setT_name(rs.getString("t_name"));
                teacher.setT_title(rs.getString("t_title"));
                teacher.setT_school(rs.getString("t_school"));
                teacher.setT_sex(rs.getString("t_sex"));
                teacher.setT_phone(rs.getString("t_phone"));
                teacher.setT_email(rs.getString("t_email"));
                teacher.setT_idcard(rs.getString("t_idcard"));
                teacher.setT_address(rs.getString("t_address"));
                teacher.setT_password(rs.getString("t_password"));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            // 如果出现SQL异常，抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和相关资源
            DBConnection.close(conn, stmt, rs);
        }
        return teachers;
    }

    /**
     * 将教师的私有信息添加到数据库中。
     * 此方法尝试建立与数据库的连接，并执行插入操作。如果连接失败或插入操作遇到异常，
     * 方法将返回false。成功执行插入操作后，方法返回true。
     *
     * @param teacher 要添加到数据库的教师私有信息对象。
     * @return 如果添加成功，返回true；否则返回false。
     */
    @Override
    public boolean add(TeacherPrivate teacher) {
        // 获取与数据库的连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        // 检查连接是否成功获取
        if (conn == null) {
            return false;
        }
        try {
            // 准备插入数据的SQL语句，使用占位符来避免SQL注入
            String sql = "INSERT INTO " + DBConnection.teacher +
                    " (tid, t_name, t_title, t_school, t_sex, t_phone, t_email, t_idcard, t_address, t_password)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            // 设置SQL语句中的参数值，对应教师私有信息的各种属性
            stmt.setString(1, teacher.getTid());
            stmt.setString(2, teacher.getT_name());
            stmt.setString(3, teacher.getT_title());
            stmt.setString(4, teacher.getT_school());
            stmt.setString(5, teacher.getT_sex());
            stmt.setString(6, teacher.getT_phone());
            stmt.setString(7, teacher.getT_email());
            stmt.setString(8, teacher.getT_idcard());
            stmt.setString(9, teacher.getT_address());
            stmt.setString(10, teacher.getT_password());
            // 执行插入操作
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 如果在数据库操作过程中遇到异常，抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement，释放资源
            DBConnection.close(conn, stmt, null);
        }
        // 如果执行插入操作没有遇到异常，返回true表示添加成功
        return true;
    }

    /**
     * 删除指定教师的信息。
     *
     * 此方法首先尝试从数据库中删除与给定教师ID（tid）相关的课程信息，
     * 然后删除教师本身的记录。如果数据库连接失败或删除操作遇到任何问题，
     * 方法将抛出运行时异常。
     *
     * @param tid 教师的唯一标识符。这是要被删除的教师的ID。
     * @return 如果删除成功，则返回true；如果数据库连接失败或其他错误发生，则返回false。
     */
    @Override
    public boolean delete(String tid) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        // 初始化PreparedStatement，用于执行SQL语句
        PreparedStatement stmt = null;
        // 如果数据库连接获取失败，则直接返回false
        if (conn == null) {
            return false;
        }
        try {
            // 构造删除课程信息的SQL语句，其中tid为教师ID
            String sql = "DELETE FROM " + DBConnection.teacher_course + " WHERE tid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tid);
            stmt.executeUpdate();
            // 构造删除教师信息的SQL语句，同样基于tid
            sql = "DELETE FROM " + DBConnection.teacher + " WHERE tid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 如果在执行SQL操作过程中遇到异常，则抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 无论操作成功还是失败，都尝试关闭数据库连接和PreparedStatement
            DBConnection.close(conn, stmt, null);
        }
        // 如果执行到此处，表示删除操作已完成，返回true
        return true;
    }

    /**
     * 更新教师的公共信息。
     * 该方法通过连接数据库，执行SQL语句来更新指定教师的各项信息。
     *
     * @param teacher 教师的公共信息对象，包含需要更新的所有信息。
     * @return 如果更新成功，返回true；如果数据库连接失败或其他错误导致更新失败，返回false。
     */
    @Override
    public boolean updatePublic(TeacherPublic teacher) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        // 如果数据库连接失败，直接返回false
        if (conn == null) {
            return false;
        }
        try {
            // 构造更新SQL语句，包含需要更新的所有字段
            String sql = "UPDATE " + DBConnection.teacher +
                    " SET t_name = ?, t_title = ?, t_school = ?, t_sex = ?, t_phone = ?, t_email = ?, t_address = ? WHERE tid = ?";
            // 准备执行SQL语句，设置参数
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, teacher.getT_name());
            stmt.setString(2, teacher.getT_title());
            stmt.setString(3, teacher.getT_school());
            stmt.setString(4, teacher.getT_sex());
            stmt.setString(5, teacher.getT_phone());
            stmt.setString(6, teacher.getT_email());
            stmt.setString(7, teacher.getT_address());
            stmt.setString(8, teacher.getTid());
            // 执行更新操作
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 如果出现SQL异常，抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement
            DBConnection.close(conn, stmt, null);
        }
        // 如果执行更新没有遇到错误，返回true表示更新成功
        return true;
    }

    /**
     * 更新教师的私有信息。
     * 该方法通过连接数据库，执行SQL语句来更新指定教师的各项私有信息。
     *
     * @param teacher 教师私有信息对象，包含需要更新的所有信息。
     * @return 如果更新成功，返回true；如果数据库连接失败或其他错误导致更新失败，返回false。
     */
    @Override
    public boolean updatePrivate(TeacherPrivate teacher) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        // 如果数据库连接失败，直接返回false
        if (conn == null) {
            return false;
        }
        try {
            // 构造更新SQL语句，包含教师表中所有可更新的字段
            String sql = "UPDATE " + DBConnection.teacher +
                    " SET t_name = ?, t_title = ?, t_school = ?, t_sex = ?, t_phone = ?, t_email = ?, t_idcard = ?, t_address = ?, t_password = ? WHERE tid = ?";
            // 准备执行SQL语句，设置参数
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, teacher.getT_name());
            stmt.setString(2, teacher.getT_title());
            stmt.setString(3, teacher.getT_school());
            stmt.setString(4, teacher.getT_sex());
            stmt.setString(5, teacher.getT_phone());
            stmt.setString(6, teacher.getT_email());
            stmt.setString(7, teacher.getT_idcard());
            stmt.setString(8, teacher.getT_address());
            stmt.setString(9, teacher.getT_password());
            stmt.setString(10, teacher.getTid());
            // 执行更新操作
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 如果出现SQL异常，抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement
            DBConnection.close(conn, stmt, null);
        }
        // 如果更新成功，返回true
        return true;
    }

    /**
     * 根据教师ID从数据库中获取教师的公共信息。
     *
     * @param tid 教师的唯一标识ID。
     * @return 返回教师的公共信息对象，如果找不到则返回null。
     */
    @Override
    public TeacherPublic getPublic(String tid) {
        // 获取与数据库的连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TeacherPublic teacher = null;

        // 检查连接是否成功获取
        if (conn == null) {
            return null;
        }
        try {
            // 准备SQL查询语句，使用预处理语句防止SQL注入
            String sql = "SELECT * FROM " + DBConnection.teacher + " WHERE tid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tid);

            // 执行查询并获取结果集
            rs = stmt.executeQuery();

            // 如果结果集中有数据，则创建并填充教师对象
            if (rs.next()) {
                teacher = new TeacherPublic(rs.getString("tid"), rs.getString("t_name"), rs.getString("t_title"), rs.getString("t_school"), rs.getString("t_sex"), rs.getString("t_phone"), rs.getString("t_email"), rs.getString("t_address"));
            }
        } catch (SQLException e) {
            // 抛出运行时异常，将SQL异常封装
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接及相关资源
            DBConnection.close(conn, stmt, rs);
        }
        return teacher;
    }

    /**
     * 根据教师ID从数据库中获取教师的私有信息。
     * 此方法实现了对教师详细信息的查询，这些信息被视为私有，只能通过此方法访问。
     *
     * @param tid 教师的唯一标识ID，用于在数据库中定位特定教师的信息。
     * @return 返回一个TeacherPrivate对象，包含查询到的教师的私有信息。
     *         如果没有找到对应的教师信息，则返回null。
     */
    @Override
    public TeacherPrivate getPrivate(String tid) {
        // 获取与数据库的连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TeacherPrivate teacher = null;

        // 检查连接是否成功获取，如果失败则直接返回null
        if (conn == null) {
            return null;
        }
        try {
            // 准备SQL查询语句，使用预处理语句来防止SQL注入
            String sql = "SELECT * FROM " + DBConnection.teacher + " WHERE tid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tid);

            // 执行查询操作，并获取结果集
            rs = stmt.executeQuery();

            // 如果结果集中有数据，则创建并填充TeacherPrivate对象
            if (rs.next()) {
                teacher = new TeacherPrivate(rs.getLong("id"),
                        rs.getString("tid"), rs.getString("t_name"),
                        rs.getString("t_title"), rs.getString("t_school"),
                        rs.getString("t_sex"), rs.getString("t_phone"),
                        rs.getString("t_email"), rs.getString("t_idcard"),
                        rs.getString("t_address"), rs.getString("t_password"));
            }
        } catch (SQLException e) {
            // 如果出现SQL异常，抛出运行时异常，并包含原始异常信息
            throw new RuntimeException(e);
        } finally {
            // 无论是否出现异常，都关闭数据库连接和相关资源
            DBConnection.close(conn, stmt, rs);
        }
        // 返回查询结果，可能为null
        return teacher;
    }
}
