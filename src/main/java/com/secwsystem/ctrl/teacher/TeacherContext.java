package com.secwsystem.ctrl.teacher;

import java.util.HashMap;
import java.util.Map;

/**
 * TeacherContext 类用于存储和管理教师相关控制器的上下文信息。
 * 通过静态 Map 对象 controllers 来持有控制器实例，实现全局访问控制器的功能。
 * 这对于在教师相关的业务逻辑中快速获取和操作控制器实例非常有用。
 */
public class TeacherContext {
    /**
     * 存储教师相关控制器实例的静态 Map。
     * 使用 String 作为键，可以方便地通过控制器的名称来获取对应的控制器实例。
     * Object 作为值，可以存储任何类型的控制器实例，提供了灵活性。
     */
    public static Map<String, Object> controllers = new HashMap<String, Object>();
}