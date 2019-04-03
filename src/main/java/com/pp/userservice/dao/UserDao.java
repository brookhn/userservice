package com.pp.userservice.dao;

import com.pp.userservice.entity.UserInfo;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface UserDao {
    UserInfo queryUser(Map<String, Object> param);

    UserInfo selectById(Integer id);

    int uploadUser(UserInfo record);
}
