package com.hulu.shop.api.controller.user;

import com.hulu.shop.api.controller.BaseController;
import com.hulu.shop.api.controller.user.req.PasswordLoginParams;
import com.hulu.shop.api.controller.user.req.SignupParams;
import com.hulu.shop.api.controller.user.resp.LoginResponseInfo;
import com.hulu.shop.api.service.UserService;
import com.hulu.shop.api.utils.ResponseCode;
import com.hulu.shop.common.utils.ServiceException;
import org.slf4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import com.hulu.shop.api.utils.ResponseObject;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
public class UserController extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final UserService userService = new UserService();

    @RequestMapping(value = "/user/signup")
    @ResponseBody
    public ResponseObject signup(@Valid SignupParams params, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseObject(ResponseCode.OK).error(errors);
        }
        String phone = params.phone;
        String passWord = params.passWord;
        String channel = params.channel;
        try {
            HashMap<String,String> responseInfo = userService.signUp(phone, passWord,channel);
            if (responseInfo == null) {
                return new ResponseObject(ResponseCode.OK, "no data").setArrayData(null);
            } else {
                ResponseObject ret = new ResponseObject(ResponseCode.OK, "ok");
                ret.setObjectData(responseInfo);
                return ret;
            }
        } catch (ServiceException exception) {
            logger.info("register ServiceException" + exception.code + "-" + exception.msg);
            return new ResponseObject(exception.code, exception.msg);
        } catch (Exception exception) {
            return new ResponseObject(ResponseCode.ERROR, exception.getMessage());
        }
    }


    @RequestMapping(value = "/user/passlogin")
    @ResponseBody
    public ResponseObject passwordLogin(HttpServletRequest request, @Valid PasswordLoginParams params, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseObject(ResponseCode.OK).error(errors);
        }
        String phone = params.phone;
        String passWord = params.passWord;
        String clientIp = request.getRemoteHost();
        HashMap<String, String> info = new HashMap<>();
        info.put("channel", "wx");
        info.put("deviceOs", "android");
        info.put("clientIp", clientIp);
        try {
            LoginResponseInfo responseInfo = userService.passwordLogin(phone, passWord, info);
            if (responseInfo == null) {
                return new ResponseObject(ResponseCode.OK, "no data").setArrayData(null);
            } else {
                ResponseObject ret = new ResponseObject(ResponseCode.OK, "ok");
                ret.setObjectData(responseInfo);
                return ret;
            }
        } catch (ServiceException exception) {
            logger.info("passwordLogin ServiceException" + exception.code + "-" + exception.msg);
            return new ResponseObject(exception.code, exception.msg);
        } catch (Exception exception) {
            return new ResponseObject(ResponseCode.ERROR, exception.getMessage());
        }
    }
}
