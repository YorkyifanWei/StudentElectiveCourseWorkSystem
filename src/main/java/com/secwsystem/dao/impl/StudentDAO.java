package com.secwsystem.dao.impl;

import com.secwsystem.dao.DAOForAccount;
import com.secwsystem.dao.pojo.DBConnection;
import com.secwsystem.dao.pojo.StudentPublic;
import com.secwsystem.dao.pojo.StudentPrivate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 学生数据访问对象实现类，实现了DAOForAll接口，针对学生公开信息和私有信息进行数据库操作。
 */
public class StudentDAO implements DAOForAccount<StudentPublic, StudentPrivate> {

    /**
     * 获取所有学生私有信息的列表。
     * 本方法通过查询数据库中的学生ID，进而获取每个学生的私有信息。
     * 如果数据库连接失败或查询出错，将抛出运行时异常。
     *
     * @return ArrayList<StudentPrivate> 所有学生私有信息的列表，如果数据库连接为空，则返回null。
     */
    public ArrayList<StudentPrivate> getAll() {
        // 连接数据库，获取数据库连接对象
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<StudentPrivate> students = new ArrayList<>();
        // 检查数据库连接是否成功，如果失败则直接返回null
        if (conn == null) {
            return null;
        }
        try {
            // 构造SQL语句，查询学生表中的所有学生ID
            String sql = "select * from " + DBConnection.student;
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            // 遍历查询结果，获取每个学生的私有信息
            while (rs.next()) {
                StudentPrivate student = new StudentPrivate();
                student.setId(rs.getLong("id"));
                student.setSid(rs.getString("sid"));
                student.setS_name(rs.getString("s_name"));
                student.setS_class(rs.getString("s_class"));
                student.setS_school(rs.getString("s_school"));
                student.setS_sex(rs.getString("s_sex"));
                student.setS_phone(rs.getString("s_phone"));
                student.setS_email(rs.getString("s_email"));
                student.setS_idcard(rs.getString("s_idcard"));
                student.setS_password(rs.getString("s_password"));
                student.setS_entertime(rs.getString("s_entertime"));
                students.add(student);
            }
        } catch (SQLException e) {
            // 如果出现SQL异常，抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接、PreparedStatement和ResultSet对象
            DBConnection.close(conn, stmt, rs);
        }
        // 返回所有学生私有信息的列表
        return students;
    }


    /**
     * 将学生私有信息添加到数据库中。
     * 此方法通过建立数据库连接并执行SQL语句来实现信息的插入。
     * 使用预处理语句来防止SQL注入攻击，并提高代码的可读性和维护性。
     *
     * @param student 待添加到数据库的学生私有信息对象。
     * @return 如果成功添加学生信息返回true，否则返回false。
     */
    @Override
    public boolean add(StudentPrivate student) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        // 如果连接为空，直接返回false
        if (conn == null) {
            return false;
        }
        try {
            // 组装SQL语句，使用占位符防止SQL注入
            String sql = "insert into " + DBConnection.student +
                    " (sid,s_name,s_class,s_school,s_sex,s_phone,s_email,s_idcard,s_password,s_entertime) values(?,?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            // 设置预处理语句的参数，依次对应学生对象的各个属性
            stmt.setString(1, student.getSid());
            stmt.setString(2, student.getS_name());
            stmt.setString(3, student.getS_class());
            stmt.setString(4, student.getS_school());
            stmt.setString(5, student.getS_sex());
            stmt.setString(6, student.getS_phone());
            stmt.setString(7, student.getS_email());
            stmt.setString(8, student.getS_idcard());
            stmt.setString(9, student.getS_password());
            stmt.setString(10, student.getS_entertime());
            // 执行插入操作
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 抛出运行时异常，便于上层处理
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement
            DBConnection.close(conn, stmt, null);
        }
        // 如果执行到此处，说明插入操作成功，返回true
        return true;
    }

    /**
     * 删除学生的相关信息。
     * 该方法首先尝试从数据库中删除指定学生的课程信息，然后删除该学生的个人信息。
     * 如果数据库连接失败或删除操作遇到任何问题，方法将抛出运行时异常。
     *
     * @param sid 学生的ID，用于唯一标识学生。
     * @return 如果删除成功，则返回true；如果数据库连接失败，则返回false。
     */
    @Override
    public boolean delete(String sid) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        // 初始化PreparedStatement，用于执行SQL语句
        PreparedStatement stmt = null;
        // 检查数据库连接是否成功
        if (conn == null) {
            return false;
        }
        try {
            // 构造删除学生课程信息的SQL语句
            String sql = "delete from " + DBConnection.student_course + " where sid = ?";
            // 准备SQL语句并设置参数
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sid);
            // 执行删除操作
            stmt.executeUpdate();

            // 构造删除学生个人信息的SQL语句
            sql = "delete from " + DBConnection.student + " where sid = ?";
            // 准备SQL语句并设置参数
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sid);
            // 执行删除操作
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 如果遇到SQL异常，抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement
            DBConnection.close(conn, stmt, null);
        }
        // 返回删除操作成功标志
        return true;
    }

    /**
     * 更新学生公开信息在数据库中的记录。
     * 此方法通过连接数据库，执行SQL语句来更新指定学生公开信息的各项数据。
     *
     * @param student 需要更新的学生公开信息对象，包含所有要修改的字段。
     * @return 如果更新成功，返回true；如果数据库连接失败或其他错误导致更新失败，返回false。
     */
    @Override
    public boolean updatePublic(StudentPublic student) {
        // 获取与数据库的连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;

        // 如果数据库连接失败，则直接返回false
        if (conn == null) {
            return false;
        }
        try {
            // 构造更新学生信息的SQL语句，使用预处理语句来防止SQL注入
            String sql = "update " + DBConnection.student +
                    " set s_name = ?, s_class = ?, s_school = ?, s_sex = ?, s_phone = ?, s_email = ? where sid = ?";
            stmt = conn.prepareStatement(sql);

            // 设置预处理语句的参数，对应SQL语句中的问号
            stmt.setString(1, student.getS_name());
            stmt.setString(2, student.getS_class());
            stmt.setString(3, student.getS_school());
            stmt.setString(4, student.getS_sex());
            stmt.setString(5, student.getS_phone());
            stmt.setString(6, student.getS_email());
            stmt.setString(7, student.getSid());

            // 执行更新操作
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 如果在数据库操作过程中发生错误，抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement对象
            DBConnection.close(conn, stmt, null);
        }
        // 如果执行更新操作没有遇到错误，返回true表示更新成功
        return true;
    }

    /**
     * 更新学生私有信息在数据库中的记录。
     * 此方法通过预编译的SQL语句来更新学生的信息，包括姓名、班级、学校、性别、电话、电子邮件、身份证号、密码和入学时间。
     *
     * @param student 学生私有信息对象，包含所有要更新的信息。
     * @return 如果更新成功，返回true；否则返回false。
     */
    @Override
    public boolean updatePrivate(StudentPrivate student) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        // 如果连接为空，直接返回false
        if (conn == null) {
            return false;
        }
        try {
            // 构造更新学生信息的SQL语句
            String sql = "update " + DBConnection.student +
                    " set s_name = ?, s_class = ?, s_school = ?, s_sex = ?, s_phone = ?, s_email = ?, s_idcard = ?, s_password = ?, s_entertime = ? where sid = ?";
            // 创建预编译的SQL语句对象，并设置参数
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getS_name());
            stmt.setString(2, student.getS_class());
            stmt.setString(3, student.getS_school());
            stmt.setString(4, student.getS_sex());
            stmt.setString(5, student.getS_phone());
            stmt.setString(6, student.getS_email());
            stmt.setString(7, student.getS_idcard());
            stmt.setString(8, student.getS_password());
            stmt.setString(9, student.getS_entertime());
            stmt.setString(10, student.getSid());
            // 执行更新操作
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 如果出现SQL异常，抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement对象
            DBConnection.close(conn, stmt, null);
        }
        // 更新成功，返回true
        return true;
    }

    /**
     * 根据学生ID从数据库中获取学生的公开信息。
     * 此方法实现了接口中对应的方法，用于具体查询数据库中的学生公开信息。
     *
     * @param sid 学生的唯一标识符，用于在数据库中定位学生信息。
     * @return 返回一个StudentPublic对象，包含查询到的学生公开信息。
     *         如果没有找到对应的学生信息，则返回null。
     */
    @Override
    public StudentPublic getPublic(String sid) {
        // 连接数据库，获取数据库连接对象
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StudentPublic student = null;
        // 如果连接为空，直接返回null
        if (conn == null) {
            return null;
        }
        try {
            // 准备SQL语句，使用预处理语句来防止SQL注入
            String sql = "select * from " + DBConnection.student + " where sid = ?";
            stmt = conn.prepareStatement(sql);
            // 设置预处理语句的参数，这里的参数是学生ID
            stmt.setString(1, sid);
            // 执行查询操作，获取结果集
            rs = stmt.executeQuery();
            // 如果结果集中有数据，则创建StudentPublic对象并填充数据
            if (rs.next()) {
                // 创建StudentPublic对象，并从rs中设置属性
                student = new StudentPublic(rs.getString("sid"),
                        rs.getString("s_name"), rs.getString("s_class"),
                        rs.getString("s_sex"), rs.getString("s_school"),
                        rs.getString("s_phone"), rs.getString("s_email"));
            }
        } catch (SQLException e) {
            // 如果出现SQL异常，则抛出运行时异常，并包含SQL异常信息
            throw new RuntimeException(e);
        } finally {
            // 无论是否出现异常，都关闭数据库连接、预处理语句和结果集
            DBConnection.close(conn, stmt, rs);
        }
        // 返回查询结果，如果未查询到学生信息，则返回null
        return student;
    }

    /**
     * 根据学生ID从数据库中获取学生的私有信息。
     * 此方法实现了接口中对应的方法，用于具体获取学生私有数据的逻辑。
     *
     * @param sid 学生的唯一标识符，用于在数据库中查询对应的学生信息。
     * @return 返回一个StudentPrivate对象，包含查询到的学生私有信息。
     *         如果没有找到对应的学生信息，则返回null。
     */
    @Override
    public StudentPrivate getPrivate(String sid) {
        // 初始化数据库连接、PreparedStatement和ResultSet对象
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StudentPrivate student = null;
        // 如果连接为空，直接返回null
        if (conn == null){
            return null;
        }
        try {
            // 准备SQL语句，用于查询指定ID的学生信息
            String sql = "select * from " + DBConnection.student + " where sid = ?";
            stmt = conn.prepareStatement(sql);
            // 设置PreparedStatement的参数，绑定查询条件
            stmt.setString(1, sid);
            // 执行查询操作，获取结果集
            rs = stmt.executeQuery();
            // 如果结果集中有数据，则创建并填充StudentPrivate对象
            if (rs.next()) {
                // 创建StudentPrivate对象，并从rs中设置属性
                student = new StudentPrivate(rs.getLong("id"),
                        rs.getString("sid"), rs.getString("s_name"),
                        rs.getString("s_class"), rs.getString("s_school"),
                        rs.getString("s_sex"), rs.getString("s_phone"),
                        rs.getString("s_email"), rs.getString("s_idcard"),
                        rs.getString("s_password"), rs.getString("s_entertime"));
            }
        } catch (SQLException e) {
            // 将SQL异常转换为运行时异常并抛出
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接、PreparedStatement和ResultSet对象
            DBConnection.close(conn, stmt, rs);
        }
        // 返回查询结果，如果未找到则返回null
        return student;
    }
}
