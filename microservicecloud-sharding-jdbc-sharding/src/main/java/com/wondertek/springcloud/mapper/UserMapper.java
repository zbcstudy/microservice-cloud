package com.wondertek.springcloud.mapper;

import com.wondertek.springcloud.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    Long addUser(User user);

    List<User> getAll();
}
