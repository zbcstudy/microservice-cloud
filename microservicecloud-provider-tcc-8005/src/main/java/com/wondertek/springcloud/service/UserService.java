package com.wondertek.springcloud.service;

import com.wondertek.springcloud.entities.User;
import org.mengyun.tcctransaction.api.Compensable;

public interface UserService {

    @Compensable
    void saveUser(User user);

    void confirmSaveUser(User user);

    void cancelSaveUser(User user);
}
