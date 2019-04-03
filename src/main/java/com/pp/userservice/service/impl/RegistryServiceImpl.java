package com.pp.userservice.service.impl;

import com.pp.userservice.Error.GeneralException;
import com.pp.userservice.Error.ParamErrorCode;
import com.pp.userservice.Error.ParamField;
import com.pp.userservice.controller.Registery;
import com.pp.userservice.dao.UserDao;
import com.pp.userservice.entity.UserInfo;
import com.pp.userservice.service.IRegistryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("registryService")
public class RegistryServiceImpl implements IRegistryService {

    private Logger logger = LoggerFactory.getLogger(RegistryServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public UserInfo getUser(String user_account) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userAccount", user_account);
        return userDao.queryUser(paramMap);
    }

    @Override
    public Map<String, Object> upLoadUser(Map<String, Object> params) {
        // 获取用户的IP
//        String ip = HttpObjUtil.getIpAddr();
//        if(StringUtils.isBlank(ip)) {
//            logger.info("get ip failed");
//            throw new GeneralException(ErrorCode.USER_IP_ERROR);
//        }

        String userAccount = params.get("user_account").toString();
        // 获取优酷令牌yk_token
//        String ykToken = YouKuApi.getAuthorization(userAccount, ip);
//        if(StringUtils.isBlank(ykToken)) {
//            logger.info("get yk_token failed");
//            throw new GeneralException(ErrorCode.USER_YK_TOKEN_ERROR);
//        }

        UserInfo user = new UserInfo();
        // 必填字段
        user.setUserAccount(userAccount);
        user.setToken(params.get("token").toString());
        user.setYkToken("");
        user.setIp("");
        user.setAppPlt(params.get("appplt").toString());
        user.setAppId(params.get("appid").toString());
        user.setIsSvip(Integer.valueOf(params.get("is_svip").toString()));
        user.setUserType(0);
        // 选填字段
        if(null != params.get("sex")) {
            user.setSex(Integer.valueOf(params.get("sex").toString()));
        }
        if(null != params.get("phone")) {
            user.setPhone(params.get("phone").toString());
        }
        if(null != params.get("mail")) {
            user.setMail(params.get("mail").toString());
        }
        if(null != params.get("birthday")) {
            user.setBirthday(formatDate(params.get("birthday").toString(), ParamField.BIRTHDAY));
        }
        if(null != params.get("register_time")) {
            user.setRegisterTime(formatDate(params.get("register_time").toString(), ParamField.REGISTER_TIME));
        }
        if(null != params.get("svip_validate")) {
            try {
                user.setSvipValidate(new Date(Long.valueOf(params.get("svip_validate").toString())));
            } catch (Exception ex) {
                throw new GeneralException(ParamErrorCode.COMMON_PARAMETER_ILLEGAL.setParam(ParamField.SVIP_VALIDATE));
            }
        }
        if(null != params.get("nick")) {
            user.setNick(params.get("nick").toString());
        }
        if(null != params.get("facepic")) {
            user.setFacePic(params.get("facepic").toString());
        }
        userDao.uploadUser(user);

        Map<String, Object> model = new HashMap<>();
        model.put("user_account", userAccount);
        model.put("yk_token", "");
        model.put("superTV", 0);    // 默认设置为非superTV会员
//        try {
//            String token = TokenUtil.encrpy(userAccount);
//            model.put("supertv_token", URLEncoder.encode(token, "UTF-8"));
//        }
//        catch (UnsupportedEncodingException e) {
//            throw new RuntimeException();
//        }
        // 查询该用户下是否存在会员包(superTV)权益
        Map<String, Object> rightsParams = new HashMap<>();
        rightsParams.put("userAccount", userAccount);
        rightsParams.put("type", 1);
//        UserRights userRights = rightsDao.getUserRightsByParams(rightsParams);
//        if(null != userRights) {
//            model.put("superTV", 1);
//            model.put("validdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(userRights.getEndTime()));
//        }
        return model;
    }

    private Date formatDate(String origin, String paramName) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(origin);    // 1990-5-12
        } catch (Exception ex1) {
            logger.info(paramName + " format is invalid");
            try {
                date = new SimpleDateFormat("yyyy年MM月dd日").parse(origin);    // 1990年5月12日
            } catch (Exception ex2) {
                logger.info(paramName + " format is invalid");
                try {
                    date = new Date(Long.valueOf(origin));    // 1545753599000
                } catch (Exception ex3) {
                    throw new GeneralException(ParamErrorCode.COMMON_PARAMETER_ILLEGAL.setParam(paramName));
                }
            }
        }
        return date;
    }
}
