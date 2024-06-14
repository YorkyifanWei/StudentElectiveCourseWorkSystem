package com.secwsystem.dao.pojo;

import com.secwsystem.dao.impl.StudentDAO;
import com.secwsystem.dao.impl.TeacherDAO;

import java.util.ArrayList;

public class Course {
    private String id;// 主键
    private String cid;// 课程号
    private String cName;// 课程名
    private String cInfo;// 简介
    private String cTime;// 上课时间
    private String cInit;// 开始时间
    private String cEnd;// 结束时间
    private String cLocation;// 上课教室
    private String cType;// 课程类型
    private String cSchool;// 课程所属学院
    private String cPeriod;// 课程学时
    private String cCapacity;// 课程容量
    private String cCurrent;// 已选人数

    // 课程对应的老师和学生列表
    private ArrayList<IdName> teachers;
    private ArrayList<IdName> students;

    public Course() {
    }

    public Course(String id, String cid, String cName, String cInfo,
                  String cTime, String cInit, String cEnd, String cLocation,
                  String cType, String cSchool, String cPeriod, String cCapacity,
                  String cCurrent, ArrayList<IdName> teachers, ArrayList<IdName> students) {
        this.id = id;
        this.cid = cid;
        this.cName = cName;
        this.cInfo = cInfo;
        this.cTime = cTime;
        this.cInit = cInit;
        this.cEnd = cEnd;
        this.cLocation = cLocation;
        this.cType = cType;
        this.cSchool = cSchool;
        this.cPeriod = cPeriod;
        this.cCapacity = cCapacity;
        this.cCurrent = cCurrent;
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
        teachers.add(new IdName(tid, teacher.getTName()));
        return true;
    }

    public boolean deleteTeacher(String tid) {
        if (teachers != null) {
            TeacherDAO teacherDAO = new TeacherDAO();
            TeacherPublic teacher = teacherDAO.getPublic(tid);
            if (teacher == null) {
                return false;
            }
            teachers.remove(new IdName(tid, teacher.getTName()));
        }
        return true;
    }

    public boolean addStudent(String sid) {
        if (cCurrent.equals(cCapacity)) {
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
        students.add(new IdName(sid, student.getSName()));
        cCurrent = String.valueOf(students.size());
        return true;
    }

    public boolean deleteStudent(String sid) {
        if (students != null) {
            StudentDAO studentDAO = new StudentDAO();
            StudentPublic student = studentDAO.getPublic(sid);
            if (student == null) {
                return false;
            }
            students.remove(new IdName(sid, student.getSName()));
        } else {
            cCurrent = "0";
            return false;
        }
        cCurrent = String.valueOf(students.size());
        return true;
    }

    // getters and setters
    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getCid() { return cid; }

    public void setCid(String cid) { this.cid = cid; }

    public String getCName() { return cName; }

    public void setCName(String cName) { this.cName = cName; }

    public String getCInfo() { return cInfo; }

    public void setCInfo(String cInfo) { this.cInfo = cInfo; }

    public String getCTime() { return cTime; }

    public void setCTime(String cTime) { this.cTime = cTime; }

    public String getCInti() { return cInit; }

    public void setCInit(String cInit) { this.cInit = cInit; }

    public String getCEnd() { return cEnd; }

    public void setCEnd(String cEnd) { this.cEnd = cEnd; }

    public String getCLocation() { return cLocation; }

    public void setCLocation(String cLocation) { this.cLocation = cLocation; }

    public String getCType() { return cType; }

    public void setCType(String cType) { this.cType = cType; }

    public String getCSchool() { return cSchool; }

    public void setCSchool(String cSchool) { this.cSchool = cSchool; }

    public String getCPeriod() { return cPeriod; }

    public void setCPeriod(String cPeriod) { this.cPeriod = cPeriod; }

    public String getCCapacity() { return cCapacity; }

    public void setCCapacity(String cCapacity) { this.cCapacity = cCapacity; }

    public String getCCurrent() { return cCurrent; }

    public void setCCurrent(String cCurrent) { this.cCurrent = cCurrent; }

    public ArrayList<IdName> getTeachers() { return teachers; }

    public void setTeachers(ArrayList<IdName> teachers) { this.teachers = teachers; }

    public ArrayList<IdName> getStudents() { return students; }

    public void setStudents(ArrayList<IdName> students) { this.students = students; }
}
