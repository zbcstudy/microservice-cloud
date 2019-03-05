package com.wondertek.springcloud.service;

import com.wondertek.springcloud.domain.User;
import com.wondertek.springcloud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zbc
 * @Date 16:40-2019/3/3
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Long addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }
}
