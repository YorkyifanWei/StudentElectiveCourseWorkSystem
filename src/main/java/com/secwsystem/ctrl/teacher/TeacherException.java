package com.secwsystem.ctrl.teacher;

/**
 * 教师模块异常类包，用于定义教师相关操作中可能出现的异常。
 * 这些异常类细化了通用异常，提供了更具体的错误信息，便于错误处理和问题定位。
 */
public class TeacherException {
    /**
     * 身份证号为空异常类。
     * 当教师的身份证号为空时，抛出此异常。
     */
    public static class IdNullException extends Throwable {
    }

    /**
     * 密码为空异常类。
     * 当教师的密码为空时，抛出此异常。
     */
    public static class PasswordNullException extends Throwable {
    }

    /**
     * 身份证号不存在异常类。
     * 当根据身份证号查询教师信息时，如果数据库中不存在该身份证号，抛出此异常。
     */
    public static class IdNotExistException extends Throwable {
    }

    /**
     * 密码错误异常类。
     * 当教师输入的密码与数据库中存储的密码不匹配时，抛出此异常。
     */
    public static class PasswordErrorException extends Throwable {
    }

    /**
     * 文本字段为空异常类。
     * 当教师相关的文本字段为空时，抛出此异常。
     * 这可以是教师的姓名、联系方式等必填字段。
     */
    public static class TextFieldIsNullException extends Throwable {
    }

    /**
     * 课程已存在异常类。
     * 当尝试添加一个已存在的课程时，抛出此异常。
     */
    public static class CourseExistException extends Throwable {
    }

    /**
     * 修改密码异常类。
     * 当教师修改密码过程中出现错误时，抛出此异常。
     * 可能的错误包括新密码不符合要求、旧密码验证失败等。
     */
    public static class ChangePasswordException extends Throwable {
    }
}