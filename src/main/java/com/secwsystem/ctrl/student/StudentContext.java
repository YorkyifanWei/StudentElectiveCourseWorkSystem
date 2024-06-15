package com.secwsystem.ctrl.student;

import java.util.HashMap;
import java.util.Map;

/**
 * 学生模块的上下文类，用于管理学生相关控制器的实例。
 * 该类提供了一个静态映射，用于存储和访问不同学生控制器的实例。
 * 这样做的目的是为了方便在整个学生模块中共享和访问这些控制器，而不需要每次都实例化新的对象。
 */
public class StudentContext {
    // 静态映射，用于存储学生控制器的实例
    public static Map<String, Object> controllers = new HashMap<String, Object>();
}