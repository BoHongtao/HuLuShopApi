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
import com.hulu.shop.common.utils.Crypt;
import com.hulu.shop.common.utils.Db;
import com.hulu.shop.common.utils.ServiceException;
import com.hulu.shop.api.constant.userModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;

public class UserService {
    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public LoginResponseInfo passwordLogin(String phone, String passWord, HashMap<String, String> info) throws ServiceException {
        logger.info("passwordLogin phone:" + phone + " passWord:" + passWord);
        User user = new QUser().phone.eq(phone).findOne();
        if (user == null) {
            throw new ServiceException(ResponseCode.ERROR, "user not find");
        }
        if (user.status == userModel.STATUS_LOCKED) {
            throw new ServiceException(ResponseCode.ERROR, "user account was loacked");
        }
        String cryptPassword = Crypt.xxteaEncode(passWord);
        if (!user.password.equals(cryptPassword)) {
            throw new ServiceException(ResponseCode.ERROR, "phone or password wrong");
        }
        LoginResponseInfo retObj = new LoginResponseInfo();
        retObj.phone = phone;
        retObj.token = "abc";
        return retObj;
    }

    public HashMap<String, String> signUp(String phone, String passWord, String channel) throws ServiceException {
        logger.info("signUp phone:" + phone + " channel" + channel);
        User existUser = new QUser().loginid.eq(phone).findOne();
        if (existUser != null) {
            throw new ServiceException(ResponseCode.ERROR,"phone already sign up");
        }
        User user = new User();
        user.userid = Db.getUuid();
        user.loginid = user.phone = user.name = phone;
        user.regtime = Timestamp.from(Instant.now());
        user.status = userModel.STATUS_ACTIVE;
        user.password = Crypt.xxteaEncode(passWord);
        user.regchannel = channel;
        user.save();
        HashMap<String, String> signInfo = new HashMap<>();
        signInfo.put("loginid",phone);
        signInfo.put("regchannel",channel);
        return signInfo;
    }

}
