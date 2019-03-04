package com.wondertek.springcloud.service;

import com.wondertek.springcloud.domain.User;

import java.util.List;

/**
 * @Author zbc
 * @Date 16:39-2019/3/3
 */
public interface UserService {

    Long addUser(User user);

    List<User> getAll();
}
