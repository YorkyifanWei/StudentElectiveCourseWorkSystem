package com.secwsystem.ctrl.student;

/**
 * 学生模块异常类包，用于定义学生操作过程中可能抛出的特定异常。
 * 这些异常细化了系统中错误的类型，使得错误处理更加精确。
 */
public class StudentException {
    
    /**
     * 学生ID为空的异常类。
     * 当在进行学生操作时，如果ID为空，则抛出此异常。
     */
    public static class IdNullException extends Throwable {
    }
    
    /**
     * 学生密码为空的异常类。
     * 当在进行学生操作时，如果密码为空，则抛出此异常。
     */
    public static class PasswordNullException extends Throwable {
    }
    
    /**
     * 学生ID不存在的异常类。
     * 当查询学生信息时，如果提供的ID在系统中不存在，则抛出此异常。
     */
    public static class IdNotExistException extends Throwable {
    }
    
    /**
     * 学生密码错误的异常类。
     * 当验证学生密码时，如果提供的密码与系统记录的不匹配，则抛出此异常。
     */
    public static class PasswordErrorException extends Throwable {
    }
    
    /**
     * 修改密码失败的异常类。
     * 当学生尝试修改密码时，如果由于某种原因失败（如新密码不符合要求），则抛出此异常。
     */
    public static class ChangePasswordException extends Throwable {
    }

}