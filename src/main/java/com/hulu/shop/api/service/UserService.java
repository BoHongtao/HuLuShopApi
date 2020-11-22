/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hulu.shop.api.service;

import com.hulu.shop.api.controller.user.UserController;
import com.hulu.shop.api.controller.user.resp.LoginResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserService {
    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public LoginResponseInfo passwordLogin(String userName, String passWord) {
        logger.info("passwordLogin userName:" + userName + " passWord:" + passWord);
        LoginResponseInfo retObj = new LoginResponseInfo();
        retObj.userName = userName;
        retObj.token = "abc";
        return retObj;
    }
}
