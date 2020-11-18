/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hulu.shop.utils;

public class ResponseCode {
    public static Integer OK = 0; //成功
    public static Integer INVALID_PARAMETER = 400; //请求参数错误
    public static Integer UNAUTHORIZED = 401; //未获得授权
    public static Integer FORBIDDEN = 403; //请求拒绝(路径错误等）
    public static Integer NOT_FOUND = 404; //数据不存在

    public static Integer INTERNAL_ERROR = 500; // 内部错误
    public static Integer PARAMS_ERROR = 501; // 参数错误

    public static Integer ERROR = 1000;
}
