/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hulu.shop.api.service;

import com.google.gson.Gson;
import com.hulu.shop.api.controller.user.UserController;
import com.hulu.shop.api.controller.user.resp.LoginResponseInfo;
import com.hulu.shop.api.utils.ResponseCode;
import com.hulu.shop.common.domain.User;
import com.hulu.shop.common.domain.query.QUser;
import com.hulu.shop.common.utils.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public LoginResponseInfo passwordLogin(String phone, String passWord) throws ServiceException {
        logger.info("passwordLogin phone:" + phone + " passWord:" + passWord);
        User user = new QUser().phone.eq(phone).findOne();
        if (user == null) {
            throw new ServiceException(ResponseCode.ERROR, "user not find");
        }
        logger.info("---search user data:"+new Gson().toJsonTree(user));
        if (!passWord.equals(user.password)) {
            throw new ServiceException(ResponseCode.ERROR, "username or password wrong");
        }
        LoginResponseInfo retObj = new LoginResponseInfo();
        retObj.phone = phone;
        retObj.token = "abc";
        return retObj;
    }
}
