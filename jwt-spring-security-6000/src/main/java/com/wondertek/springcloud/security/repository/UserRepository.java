package com.wondertek.springcloud.security.repository;

import com.wondertek.springcloud.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by win on 2019/4/19.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
