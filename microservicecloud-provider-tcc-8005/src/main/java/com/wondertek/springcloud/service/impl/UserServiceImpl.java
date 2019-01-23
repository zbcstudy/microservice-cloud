package com.wondertek.springcloud.service.impl;

import com.wondertek.springcloud.dao.UserRepository;
import com.wondertek.springcloud.entities.User;
import com.wondertek.springcloud.service.UserService;
import org.mengyun.tcctransaction.api.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zbc
 * @Date 21:46-2019/1/23
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    @Compensable(confirmMethod = "confirmSaveUser",cancelMethod = "cancelSaveUser")
    public void saveUser(User user) {
        log.info("开始保存user对象");
        user.setStatus(1);
        userRepository.save(user);
    }

    @Override
    public void confirmSaveUser(User user) {
        log.info("confirm user save");
        user.setStatus(1);
        userRepository.save(user);
    }

    @Override
    public void cancelSaveUser(User user) {
        log.info("cancel user save");
        user.setStatus(0);
        userRepository.save(user);
    }
}
