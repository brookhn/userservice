package com.pp.userservice.entity;

import java.util.Date;

public class UserInfo {
    private Integer id;

    private String userAccount;

    private String token;

    private String ykToken;

    private String ip;

    private Integer sex;    // '性别 0：男，1：女'

    private String phone;   // '手机号'

    private String mail;    // '邮箱'

    private Date birthday;  // '生日'

    private Date registerTime; // '注册时间'

    private String appPlt;  // '平台参数'

    private String appId;   // '平台id'

    private Integer isSvip; // 'svip  0：不是，1：是'

    private Date svipValidate; // 'svip有效时间'

    private String nick;    // 昵称

    private String facePic; // 头像

    private Date createTime;

    private Date updateTime;

    /** 用户类型：0普通，1测试，2VIP **/
    private Integer userType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getYkToken() {
        return ykToken;
    }

    public void setYkToken(String ykToken) {
        this.ykToken = ykToken;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getAppPlt() {
        return appPlt;
    }

    public void setAppPlt(String appPlt) {
        this.appPlt = appPlt;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getIsSvip() {
        return isSvip;
    }

    public void setIsSvip(Integer isSvip) {
        this.isSvip = isSvip;
    }

    public Date getSvipValidate() {
        return svipValidate;
    }

    public void setSvipValidate(Date svipValidate) {
        this.svipValidate = svipValidate;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getFacePic() {
        return facePic;
    }

    public void setFacePic(String facePic) {
        this.facePic = facePic;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
