package com.secwsystem.dao.pojo;

import com.secwsystem.dao.impl.StudentDAO;
import com.secwsystem.dao.impl.TeacherDAO;

import java.util.ArrayList;

public class Course {
    private String id;// 主键
    private String cid;// 课程号
    private String c_name;// 课程名
    private String c_info;// 简介
    private String c_time;// 上课时间
    private String c_init;// 开始时间
    private String c_end;// 结束时间
    private String c_location;// 上课教室
    private String c_type;// 课程类型
    private String c_school;// 课程所属学院
    private String c_period;// 课程学时
    private String c_capacity;// 课程容量
    private String c_current;// 已选人数

    // 课程对应的老师和学生列表
    private ArrayList<IdName> teachers;
    private ArrayList<IdName> students;

    public Course() {
    }

    public Course(String id, String cid, String c_name, String c_info,
                  String c_time, String c_init, String c_end, String c_location,
                  String c_type, String c_school, String c_period, String c_capacity,
                  String c_current, ArrayList<IdName> teachers, ArrayList<IdName> students) {
        this.id = id;
        this.cid = cid;
        this.c_name = c_name;
        this.c_info = c_info;
        this.c_time = c_time;
        this.c_init = c_init;
        this.c_end = c_end;
        this.c_location = c_location;
        this.c_type = c_type;
        this.c_school = c_school;
        this.c_period = c_period;
        this.c_capacity = c_capacity;
        this.c_current = c_current;
        this.teachers = teachers;
        this.students = students;
    }

    // 修改teachers和students
    public boolean addTeacher(String tid) {
        if (teachers == null) {
            teachers = new ArrayList<>();
        }
        TeacherDAO teacherDAO = new TeacherDAO();
        TeacherPublic teacher = teacherDAO.getPublic(tid);
        if (teacher == null) {
            return false;
        }
        teachers.add(new IdName(tid, teacher.getT_name()));
        return true;
    }

    public boolean deleteTeacher(String tid) {
        if (teachers != null) {
            TeacherDAO teacherDAO = new TeacherDAO();
            TeacherPublic teacher = teacherDAO.getPublic(tid);
            if (teacher == null) {
                return false;
            }
            teachers.remove(new IdName(tid, teacher.getT_name()));
        }
        return true;
    }

    public boolean addStudent(String sid) {
        if (c_current.equals(c_capacity)) {
            return false;
        }
        if (students == null) {
            students = new ArrayList<>();
        }
        StudentDAO studentDAO = new StudentDAO();
        StudentPublic student = studentDAO.getPublic(sid);
        if (student == null) {
            return false;
        }
        students.add(new IdName(sid, student.getS_name()));
        c_current = String.valueOf(students.size());
        return true;
    }

    public boolean deleteStudent(String sid) {
        if (students != null) {
            StudentDAO studentDAO = new StudentDAO();
            StudentPublic student = studentDAO.getPublic(sid);
            if (student == null) {
                return false;
            }
            students.remove(new IdName(sid, student.getS_name()));
        } else {
            c_current = "0";
            return false;
        }
        c_current = String.valueOf(students.size());
        return true;
    }

    // getters and setters
    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getCid() { return cid; }

    public void setCid(String cid) { this.cid = cid; }

    public String getC_name() { return c_name; }

    public void setC_name(String c_name) { this.c_name = c_name; }

    public String getC_info() { return c_info; }

    public void setC_info(String c_info) { this.c_info = c_info; }

    public String getC_time() { return c_time; }

    public void setC_time(String c_time) { this.c_time = c_time; }

    public String getC_init() { return c_init; }

    public void setC_init(String c_init) { this.c_init = c_init; }

    public String getC_end() { return c_end; }

    public void setC_end(String c_end) { this.c_end = c_end; }

    public String getC_location() { return c_location; }

    public void setC_location(String c_location) { this.c_location = c_location; }

    public String getC_type() { return c_type; }

    public void setC_type(String c_type) { this.c_type = c_type; }

    public String getC_school() { return c_school; }

    public void setC_school(String c_school) { this.c_school = c_school; }

    public String getC_period() { return c_period; }

    public void setC_period(String c_period) { this.c_period = c_period; }

    public String getC_capacity() { return c_capacity; }

    public void setC_capacity(String c_capacity) { this.c_capacity = c_capacity; }

    public String getC_current() { return c_current; }

    public void setC_current(String c_current) { this.c_current = c_current; }

    public ArrayList<IdName> getTeachers() { return teachers; }

    public void setTeachers(ArrayList<IdName> teachers) { this.teachers = teachers; }

    public ArrayList<IdName> getStudents() { return students; }

    public void setStudents(ArrayList<IdName> students) { this.students = students; }
}
