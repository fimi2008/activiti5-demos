package com.lionxxw.common.constants;

/**
 * <p>Description: 数据状态常量定义 </p>
 * 1-有效 0-无效
 * @author wangxiang
 * @date 16/5/6 上午9:25
 * @version 1.0
 */
public interface DataStatus {

    final static int ENABLED = 1;   // 数据有效

    final static int DISABLED = 0;  // 数据无效

    final static int SEX_MAN = 0;   // 性别-男

    final static int SEX_WOMAN = 1; // 性别-女

    final static int HTTP_SUCCESS = 200; // http请求成功

    final static int HTTP_FAILE = 500; // http请求失败

    final static String DECODECHARSET = "utf-8";    // 默认字符编码

    static final String SESSION_EMP = "session_emp"; // 后台员工session key
}
