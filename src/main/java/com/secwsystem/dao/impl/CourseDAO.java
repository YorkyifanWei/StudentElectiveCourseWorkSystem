package com.secwsystem.dao.impl;

import com.secwsystem.dao.DAOForCourse;
import com.secwsystem.dao.pojo.DBConnection;
import com.secwsystem.dao.pojo.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 课程数据访问对象，用于对课程信息进行数据库操作。
 */
public class CourseDAO implements DAOForCourse {
    /**
     * 获取所有课程的信息。
     * 通过查询数据库中的课程ID，进而获取完整的课程对象列表。
     * 如果数据库连接失败，将返回null。
     *
     * @return ArrayList<Course> 所有课程的列表，如果无法建立数据库连接，则返回null。
     */
    public ArrayList<Course> getAllCourses() {
        // 连接到数据库
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Course> courses = new ArrayList<>();
        // 检查数据库连接是否成功
        if (conn == null) {
            return null;
        }
        try {
            // 准备SQL查询语句，查询课程表中的所有课程ID
            String sql = "select cid from " + DBConnection.course;
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            // 遍历查询结果，为每个课程ID调用getCourse方法，获取完整的课程信息
            while (rs.next()) {
                Course course = getCourse(rs.getString("cid"));
                if (course != null) {
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            // 如果出现SQL异常，抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和资源
            DBConnection.close(conn, stmt, rs);
        }
        return courses;
    }


    /**
     * 添加课程信息及其教师和学生关联到数据库。
     *
     * @param course 要添加的课程对象。
     * @return 如果添加成功返回true，否则返回false。
     */
    @Override
    public boolean addCourse(Course course) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        // 如果连接为空，表示获取数据库连接失败，直接返回false
        if (conn == null) {
            return false;
        }
        try {
            // 构造添加课程基本信息的SQL语句
            String sql = "insert into " + DBConnection.course + " (cid,c_name,c_info,c_time,c_init,c_end,c_location,c_type,c_school,c_period,c_capacity,c_current) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            // 设置SQL语句中的参数值，对应课程的各种属性
            stmt.setString(1, course.getCid());
            stmt.setString(2, course.getC_name());
            stmt.setString(3, course.getC_info());
            stmt.setString(4, course.getC_time());
            stmt.setString(5, course.getC_init());
            stmt.setString(6, course.getC_end());
            stmt.setString(7, course.getC_location());
            stmt.setString(8, course.getC_type());
            stmt.setString(9, course.getC_school());
            stmt.setString(10, course.getC_period());
            stmt.setString(11, course.getC_capacity());
            stmt.setString(12, course.getC_current());
            // 执行更新操作，将课程信息插入数据库
            stmt.executeUpdate();

            // 遍历课程的教师列表，为每个教师和课程建立关联关系
            for (int i = 0; i < course.getTeachers().size(); i++) {
                sql = "insert into " + DBConnection.teacher_course + " (cid,tid) values(?,?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, course.getCid());
                stmt.setString(2, course.getTeachers().get(i).getId());
                stmt.executeUpdate();
            }

            // 遍历课程的学生列表，为每个学生和课程建立关联关系
            for (int i = 0; i < course.getStudents().size(); i++) {
                sql = "insert into " + DBConnection.student_course + " (cid,sid) values(?,?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, course.getCid());
                stmt.setString(2, course.getStudents().get(i).getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            // 如果出现SQL异常，抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement
            DBConnection.close(conn, stmt, null);
        }
        // 添加成功，返回true
        return true;
    }



    /**
     * 删除课程及其相关联的教师和学生信息。
     * <p>
     * 此方法首先尝试从数据库中删除指定课程的信息。然后，它遍历课程中的每个教师，
     * 并从教师与课程关联的表中删除这些关联。类似地，它也遍历课程中的每个学生，
     * 并删除这些学生与课程的关联。这个方法是课程管理中的一个重要操作，
     * 需要谨慎处理以确保数据库的一致性。
     *
     * @param course 要删除的课程对象。这个对象包含了课程的ID以及与该课程相关的教师和学生列表。
     * @return 如果删除成功，则返回true；如果删除过程中遇到任何问题，则返回false。
     */
    @Override
    public boolean deleteCourse(Course course) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        // 如果无法获取到数据库连接，则直接返回false
        if (conn == null) {
            return false;
        }
        try {
            // 删除课程信息
            String sql = "delete from " + DBConnection.course + " where cid=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, course.getCid());
            stmt.executeUpdate();

            // 遍历课程中的每个教师，删除他们与课程的关联
            for (int i = 0; i < course.getTeachers().size(); i++) {
                sql = "delete from " + DBConnection.teacher_course + " where cid=? and tid=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, course.getCid());
                stmt.setString(2, course.getTeachers().get(i).getId());
                stmt.executeUpdate();
            }

            // 遍历课程中的每个学生，删除他们与课程的关联
            for (int i = 0; i < course.getStudents().size(); i++) {
                sql = "delete from " + DBConnection.student_course + " where cid=? and sid=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, course.getCid());
                stmt.setString(2, course.getStudents().get(i).getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            // 如果在删除过程中发生SQL异常，则抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement
            DBConnection.close(conn, stmt, null);
        }
        // 如果删除成功，返回true
        return true;
    }

    /**
     * 更新课程信息及其相关的教师和学生信息。
     *
     * @param course 包含需要更新的课程信息的对象。
     * @return 如果更新成功，则返回true；否则返回false。
     */
    @Override
    public boolean updateCourse(Course course) {
        // 获取原有课程信息
        Course oldCourse = getCourse(course.getCid());
        // 如果课程不存在，则直接返回false
        if (oldCourse == null) {
            return false;
        }
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        // 如果连接为空，则直接返回false
        if (conn == null) {
            return false;
        }
        try {
            // 构造更新课程信息的SQL语句
            String sql = "update " + DBConnection.course + " set c_name=?,c_info=?,c_time=?,c_init=?,c_end=?,c_location=?,c_type=?,c_school=?,c_period=?,c_capacity=?,c_current=? where cid=?";
            stmt = conn.prepareStatement(sql);
            // 设置SQL语句中的参数值，更新课程基本信息
            stmt.setString(1, course.getC_name());
            stmt.setString(2, course.getC_info());
            stmt.setString(3, course.getC_time());
            stmt.setString(4, course.getC_init());
            stmt.setString(5, course.getC_end());
            stmt.setString(6, course.getC_location());
            stmt.setString(7, course.getC_type());
            stmt.setString(8, course.getC_school());
            stmt.setString(9, course.getC_period());
            stmt.setString(10, course.getC_capacity());
            stmt.setString(11, course.getC_current());
            stmt.setString(12, course.getCid());
            stmt.executeUpdate();

            // 删除原有的教师和学生关联信息
            for (int i = 0; i < oldCourse.getTeachers().size(); i++) {
                sql = "delete from " + DBConnection.teacher_course + " where cid=? and tid=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, oldCourse.getCid());
                stmt.setString(2, oldCourse.getTeachers().get(i).getId());
                stmt.executeUpdate();
            }
            for (int i = 0; i < oldCourse.getStudents().size(); i++) {
                sql = "delete from " + DBConnection.student_course + " where cid=? and sid=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, oldCourse.getCid());
                stmt.setString(2, oldCourse.getStudents().get(i).getId());
                stmt.executeUpdate();
            }

            // 添加新的教师和学生关联信息
            for (int i = 0; i < course.getTeachers().size(); i++) {
                sql = "insert into " + DBConnection.teacher_course + " (cid,tid) values(?,?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, course.getCid());
                stmt.setString(2, course.getTeachers().get(i).getId());
                stmt.executeUpdate();
            }
            for (int i = 0; i < course.getStudents().size(); i++) {
                sql = "insert into " + DBConnection.student_course + " (cid,sid) values(?,?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, course.getCid());
                stmt.setString(2, course.getStudents().get(i).getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.close(conn, stmt, null);
        }
        // 更新操作暂时总是返回false，可能需要修改为返回更新结果的正确处理
        return true;
    }

    /**
     * 根据课程ID获取课程信息。
     * 该方法通过查询数据库来获取指定ID的课程详情，包括课程的基本信息和相关的教师与学生列表。
     *
     * @param cid 课程ID，用于查询特定课程的信息。
     * @return 返回查询到的课程对象，如果未找到对应课程或数据库连接失败，则返回null。
     */
    @Override
    public Course getCourse(String cid) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Course course = null;
        // 检查数据库连接是否成功
        if (conn == null) {
            return null;
        }
        try {
            // 构造查询课程基本信息的SQL语句
            String sql = "select * from " + DBConnection.course + " where cid=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cid);
            rs = stmt.executeQuery();
            // 如果查询结果存在，则创建新的课程对象
            if (rs.next()) {
                course = new Course(rs.getString("id"), rs.getString("cid"),
                        rs.getString("c_name"), rs.getString("c_info"),
                        rs.getString("c_time"), rs.getString("c_init"),
                        rs.getString("c_end"), rs.getString("c_location"),
                        rs.getString("c_type"), rs.getString("c_school"),
                        rs.getString("c_period"), rs.getString("c_capacity"),
                        rs.getString("c_current"), new ArrayList<>(), new ArrayList<>());
                // 遍历课程教师列表，查询并添加每个教师的ID
                sql = "select tid from " + DBConnection.teacher_course + " where cid=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, course.getCid());
                rs = stmt.executeQuery();
                while (rs.next()) {
                    course.addTeacher(rs.getString("tid"));
                }

                // 遍历课程学生列表，查询并添加每个学生的ID
                sql = "select sid from " + DBConnection.student_course + " where cid=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, course.getCid());
                rs = stmt.executeQuery();
                while (rs.next()) {
                    course.addStudent(rs.getString("sid"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和资源
            DBConnection.close(conn, stmt, rs);
        }
        return course;
    }

    /**
     * 添加教师到指定课程。此方法尝试将一个教师关联到一个课程，通过在teacher_course表中插入一条新的记录。
     *
     * @param cid 课程ID，表示要添加教师的课程。
     * @param tid 教师ID，表示要添加到课程的教师。
     * @return 如果成功添加教师，则返回true；如果连接数据库失败或其他错误发生，则返回false。
     */
    @Override
    public boolean addTeacher(String cid, String tid) {
        // 获取与数据库的连接。
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;

        // 检查数据库连接是否成功获取。
        if (conn == null) {
            return false;
        }
        try {
            // 构造SQL语句，用于在teacher_course表中插入新的记录。
            String sql = "insert into " + DBConnection.teacher_course + "(cid, tid) values(?, ?)";
            stmt = conn.prepareStatement(sql);

            // 设置SQL语句中的参数值，分别为课程ID和教师ID。
            stmt.setString(1, cid);
            stmt.setString(2, tid);

            // 执行更新操作，将教师添加到课程。
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 如果在数据库操作过程中发生错误，抛出运行时异常。
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement，以释放资源。
            DBConnection.close(conn, stmt, null);
        }
        return true;
    }

    /**
     * 删除指定课程ID和教师ID的教师信息。
     *
     * @param cid 课程ID
     * @param tid 教师ID
     * @return 如果删除成功返回true，否则返回false。
     */
    @Override
    public boolean deleteTeacher(String cid, String tid) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;

        // 如果连接为空，则直接返回false
        if (conn == null) {
            return false;
        }
        try {
            // 构造删除教师信息的SQL语句，参数化查询以避免SQL注入
            String sql = "delete from " + DBConnection.teacher_course + " where cid=? and tid=?";
            stmt = conn.prepareStatement(sql);
            // 设置SQL语句的参数值
            stmt.setString(1, cid);
            stmt.setString(2, tid);
            // 执行更新操作，删除符合条件的教师信息
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 如果出现SQLException，抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement对象
            DBConnection.close(conn, stmt, null);
        }
        // 删除操作成功，返回true
        return true;
    }

    /**
     * 添加学生到指定课程的方法。
     *
     * @param cid 课程ID，表示学生要加入的课程。
     * @param sid 学生ID，表示要加入课程的学生。
     * @return 如果学生成功加入课程，则返回true；如果连接数据库失败或其他错误发生，则返回false。
     */
    @Override
    public boolean addStudent(String cid, String sid) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        // 检查数据库连接是否成功，如果失败则直接返回false
        if (conn == null) {
            return false;
        }
        try {
            // 组织SQL语句，用于插入学生和课程的关系
            String sql = "insert into " + DBConnection.student_course + "(cid, sid) values(?, ?)";
            stmt = conn.prepareStatement(sql);
            // 设置SQL语句中的参数值
            stmt.setString(1, cid);
            stmt.setString(2, sid);
            // 执行更新操作，将学生添加到课程中
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 如果出现SQL异常，则抛出运行时异常，并包含SQL异常信息
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement对象
            DBConnection.close(conn, stmt, null);
        }
        // 如果执行到此处，说明学生添加操作成功，返回true
        return true;
    }

    /**
     * 根据课程ID和学生ID删除学生选课信息。
     *
     * @param cid 课程ID，用于定位特定课程的选课信息。
     * @param sid 学生ID，用于定位特定学生在特定课程中的选课信息。
     * @return 如果删除成功返回true，如果连接数据库失败或其他异常返回false。
     */
    @Override
    public boolean deleteStudent(String cid, String sid) {
        // 获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;

        // 检查数据库连接是否成功
        if (conn == null) {
            return false;
        }
        try {
            // 构造删除选课信息的SQL语句，使用预编译方式防止SQL注入
            String sql = "delete from " + DBConnection.student_course + " where cid=? and sid=?";
            stmt = conn.prepareStatement(sql);
            // 设置SQL语句中的参数值
            stmt.setString(1, cid);
            stmt.setString(2, sid);
            // 执行更新操作，删除符合条件的选课信息
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 如果出现SQL异常，抛出运行时异常，并包含SQL异常信息
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接和PreparedStatement，释放资源
            DBConnection.close(conn, stmt, null);
        }
        // 删除操作成功，返回true
        return true;
    }
    /**
     * 根据教师ID获取该教师教授的所有课程。
     * 本方法首先尝试从数据库中获取指定教师ID对应的课程ID列表，
     * 然后根据这些课程ID获取详细的课程信息。
     *
     * @param tid 教师的ID。
     * @return 包含该教师所有课程的ArrayList，如果未找到任何课程或发生错误，则返回null。
     */
    @Override
    public ArrayList<Course> getCoursesFromTeacher(String tid) {
        // 建立与数据库的连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<String> cids = new ArrayList<>();
        // 检查连接是否成功，如果失败则直接返回null
        if (conn == null) {
            return null;
        }
        try {
            // 准备查询语句，查询指定教师ID对应的课程ID
            String sql = "select cid from " + DBConnection.teacher_course + " where tid=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tid);
            rs = stmt.executeQuery();
            // 遍历查询结果，将课程ID添加到列表中
            while (rs.next()) {
                cids.add(rs.getString("cid"));
            }
        } catch (SQLException e) {
            // 如果出现SQL异常，抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接及相关资源
            DBConnection.close(conn, stmt, rs);
        }
        // 如果课程ID列表为空，说明未找到该教师的任何课程，返回null
        if (cids.isEmpty()) {
            return null;
        }
        // 创建一个列表用于存储获取到的课程信息
        ArrayList<Course> courses = new ArrayList<>();
        // 遍历课程ID列表，获取每门课程的详细信息，并添加到课程列表中
        for (String cid : cids) {
            Course course = getCourse(cid);
            if (course != null) {
                courses.add(course);
            }
        }
        // 返回包含该教师所有课程的列表
        return courses;
    }
    /**
     * 根据学生ID获取该学生所选的课程列表。
     * 本方法首先尝试从数据库中查询学生选课信息，获取所有选课的课程ID，
     * 然后根据这些课程ID查询课程的详细信息，最后返回一个包含所有课程的列表。
     * 如果学生没有选任何课程或者查询过程中出现错误，本方法将返回null。
     *
     * @param sid 学生ID，用于查询学生选课信息。
     * @return 返回学生选修的课程列表，如果学生没有选课则返回null。
     */
    @Override
    public ArrayList<Course> getCoursesFromStudent(String sid) {
        // 连接数据库，获取数据库连接
        Connection conn = DBConnection.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<String> cids = new ArrayList<>();
        // 检查数据库连接是否成功，如果失败则直接返回null
        if (conn == null) {
            return null;
        }
        try {
            // 准备SQL语句，查询指定学生ID的选课信息
            String sql = "select cid from " + DBConnection.student_course + " where sid=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sid);
            rs = stmt.executeQuery();

            // 遍历查询结果，将课程ID添加到列表中
            while (rs.next()) {
                cids.add(rs.getString("cid"));
            }
        } catch (SQLException e) {
            // 如果出现SQL异常，抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            // 关闭数据库连接及相关资源
            DBConnection.close(conn, stmt, rs);
        }
        // 如果查询结果为空，说明学生没有选课，返回null
        if (cids.isEmpty()) {
            return null;
        }
        ArrayList<Course> courses = new ArrayList<>();
        // 根据课程ID查询课程详细信息，并将课程添加到列表中
        for (String cid : cids) {
            Course course = getCourse(cid);
            if (course != null) {
                courses.add(course);
            }
        }
        // 返回包含所有课程的列表
        return courses;
    }


}
