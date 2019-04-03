package com.pp.userservice.controller;

import com.alibaba.fastjson.JSON;
import com.pp.userservice.Error.ErrorCode;
import com.pp.userservice.Error.GeneralException;
import com.pp.userservice.Error.ParamErrorCode;
import com.pp.userservice.Error.ParamField;
import com.pp.userservice.common.JsonPrinter;
import com.pp.userservice.entity.UserInfo;
import com.pp.userservice.service.IRegistryService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/registry")
public class Registery {

    private Logger logger = LoggerFactory.getLogger(Registery.class);

    @Autowired
    private IRegistryService registryService;

    @RequestMapping(value = "/{user_account}", method = RequestMethod.GET )
    public void queryUserInfo(@PathVariable(value = "user_account", required = true) String user_account,
                                HttpServletResponse response){
        if (StringUtils.isEmpty(user_account))
        {
            throw new GeneralException(ParamErrorCode.COMMON_PARAMETER_EMPTY.setParam(ParamField.USER_ACCOUNT));
        }
        UserInfo userInfo = registryService.getUser(user_account);
        JsonPrinter.printJson(response, JSON.toJSONString(userInfo));
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public void registerUsePhone(@RequestParam(value = "user_account", required = true) String user_account,
                                 @RequestParam(value = "token", required = true) String token,
                                 @RequestParam(value = "appplt", required = false) String appplt,
                                 @RequestParam(value = "appid", required = false) String appid,
                                 @RequestParam(value = "is_svip", required = false) String is_svip,
                                 @RequestParam(value = "svip_validate", required = false) String svip_validate,
                                 @RequestParam(value = "nick", required = false) String nick,
                                 @RequestParam(value = "facepic", required = false) String facepic,
                                 @RequestParam(value = "sex", required = false) String sex,
                                 @RequestParam(value = "phone", required = false) String phone,
                                 @RequestParam(value = "mail", required = false) String mail,
                                 @RequestParam(value = "birthday", required = false) String birthday,
                                 @RequestParam(value = "register_time", required = false) String register_time,
                                 @RequestParam(value = "format", required = false) String format,
                                 @RequestParam(value = "cb", required = false, defaultValue = "") String cb,
                                 HttpServletResponse response)
    {
        logger.info("UserController upLoadUser start, user_account = " + user_account + ", token = " + token +
                ", appplt = " + appplt + ", appid = " + appid + ", is_svip = " + is_svip +  ", svip_validate = " +
                svip_validate + ", nick = " + nick + ", facepic = " + facepic + ", sex = " + sex + ", phone = " +
                phone + ", mail = " + mail + ", birthday = " + birthday + ", register_time = " + register_time);
        // 检查非空参数
        checkParam(user_account, token, appplt, appid, is_svip, svip_validate);
        Map<String, Object> params = new HashMap<>();
        params.put("user_account", user_account);
        params.put("token", token);
        params.put("appplt", appplt);
        params.put("appid", appid);
        params.put("is_svip", is_svip);
        if(StringUtils.isNotBlank(svip_validate)) {
            params.put("svip_validate", svip_validate);
        }
        if(StringUtils.isNotBlank(sex)) {
            params.put("sex", sex);
        }
        if(StringUtils.isNotBlank(phone)) {
            params.put("phone", phone);
        }
        if(StringUtils.isNotBlank(mail)) {
            params.put("mail", mail);
        }
        if(StringUtils.isNotBlank(birthday)) {
            params.put("birthday", birthday);
        }
        if(StringUtils.isNotBlank(register_time)) {
            params.put("register_time", register_time);
        }
        if(StringUtils.isNotBlank(nick)) {
            params.put("nick", nick);
        }
        if(StringUtils.isNotBlank(facepic)) {
            params.put("facepic", facepic);
        }
//        Mapper<String, Object> result = userService.upLoadUser(params);
        JsonPrinter.printObjectAccessSuccess(response, ErrorCode.SUCCESS, null, format, cb);
    }


    // 验证参数
    private void checkParam(String user_account, String token, String appplt, String appid, String is_svip, String svip_validate) {
        if (StringUtils.isBlank(user_account)) {
            logger.info("user_account is empty");
            throw new GeneralException(ParamErrorCode.COMMON_PARAMETER_EMPTY.setParam(ParamField.USER_ACCOUNT));
        }

        if (StringUtils.isBlank(token)) {
            logger.info("token is empty");
            throw new GeneralException(ParamErrorCode.COMMON_PARAMETER_EMPTY.setParam(ParamField.TOKEN));
        }

        if (StringUtils.isBlank(appplt)) {
            logger.info("appplt is empty");
            throw new GeneralException(ParamErrorCode.COMMON_PARAMETER_EMPTY.setParam(ParamField.APPPLT));
        }

        if (StringUtils.isBlank(appid)) {
            logger.info("appid is empty");
            throw new GeneralException(ParamErrorCode.COMMON_PARAMETER_EMPTY.setParam(ParamField.APPID));
        }

        if (StringUtils.isBlank(is_svip)) {
            logger.info("is_svip is empty");
            throw new GeneralException(ParamErrorCode.COMMON_PARAMETER_EMPTY.setParam(ParamField.IS_SVIP));
        } else {
            if(Integer.valueOf(is_svip).equals(1)) {	// is_svip为1时，svip_validate必填
                if (StringUtils.isBlank(svip_validate)) {
                    logger.info("svip_validate is empty");
                    throw new GeneralException(ParamErrorCode.COMMON_PARAMETER_EMPTY.setParam(ParamField.SVIP_VALIDATE));
                }
            }
        }
    }
}
