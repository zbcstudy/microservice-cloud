package com.wondertek.springcloud.service;

import com.wondertek.springcloud.entities.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("microservicecloud-tcc-user")
public interface UserService {

    @RequestMapping("/user/save")
    void saveUser(User user);
}
