package com.ping.system.service;

import com.ping.system.entity.User;
import com.ping.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ping
 * @date 2023/10/22
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    public int insertUserDetail(User user) {
        return userMapper.insertUserDetail(user);
    }
}
