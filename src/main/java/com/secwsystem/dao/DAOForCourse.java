package com.secwsystem.dao;

import com.secwsystem.dao.pojo.Course;

import java.util.ArrayList;

/**
 * 课程数据访问接口。
 * 提供对课程及相关操作的抽象，包括添加、删除、更新课程，以及添加、删除教师和学生。
 * 同时，提供根据教师或学生查询其所教授或选修的课程列表的功能。
 */
public interface DAOForCourse {

    // 获取所有课程
    ArrayList<Course> getAllCourses();

    /**
     * 添加课程。
     *
     * @param course 要添加的课程对象。
     * @return 添加操作的成功与否。
     */
    boolean addCourse(Course course);

    /**
     * 删除课程。
     *
     * @param course 要删除的课程对象。
     * @return 删除操作的成功与否。
     */
    boolean deleteCourse(Course course);

    /**
     * 更新课程信息。
     *
     * @param course 要更新的课程对象。
     * @return 更新操作的成功与否。
     */
    boolean updateCourse(Course course);

    /**
     * 根据课程ID获取课程信息。
     *
     * @param cid 课程ID。
     * @return 对应课程ID的课程对象。
     */
    Course getCourse(String cid);

    /**
     * 为课程添加教师。
     *
     * @param cid 课程ID。
     * @param tid 教师ID。
     * @return 添加操作的成功与否。
     */
    boolean addTeacher(String cid,String tid);

    /**
     * 从课程中删除教师。
     *
     * @param cid 课程ID。
     * @param tid 教师ID。
     * @return 删除操作的成功与否。
     */
    boolean deleteTeacher(String cid,String tid);

    /**
     * 为课程添加学生。
     *
     * @param cid 课程ID。
     * @param sid 学生ID。
     * @return 添加操作的成功与否。
     */
    boolean addStudent(String cid,String sid);

    /**
     * 从课程中删除学生。
     *
     * @param cid 课程ID。
     * @param sid 学生ID。
     * @return 删除操作的成功与否。
     */
    boolean deleteStudent(String cid,String sid);

    /**
     * 根据教师ID获取其教授的所有课程ID列表。
     *
     * @param tid 教师ID。
     * @return 教师所教授的课程ID列表。
     */
    ArrayList<Course> getCoursesFromTeacher(String tid);

    /**
     * 根据学生ID获取其选修的所有课程ID列表。
     *
     * @param sid 学生ID。
     * @return 学生所选修的课程ID列表。
     */
    ArrayList<Course> getCoursesFromStudent(String sid);
}
