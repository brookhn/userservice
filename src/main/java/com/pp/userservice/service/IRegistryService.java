package com.pp.userservice.service;

import com.pp.userservice.entity.UserInfo;

import java.util.Map;

public interface IRegistryService {
    UserInfo getUser(String user_account);

    Map<String, Object> upLoadUser(Map<String, Object> params);
}
