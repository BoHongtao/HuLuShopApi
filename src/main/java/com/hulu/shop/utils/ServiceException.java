/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hulu.shop.utils;

import com.hulu.shop.api.controller.user.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceException extends Throwable {
    public Integer code = 0;
    public String msg = "";
    public Object data = null;
    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);


    public ServiceException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        logger.info("?????????");
    }

    public ServiceException(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
