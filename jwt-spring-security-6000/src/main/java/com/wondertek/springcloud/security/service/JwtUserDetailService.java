package com.wondertek.springcloud.security.service;

import com.wondertek.springcloud.model.security.User;
import com.wondertek.springcloud.security.JwtUserFactory;
import com.wondertek.springcloud.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by win on 2019/4/19.
 */
@Service("jwtUserDetailService")
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }else {
            return JwtUserFactory.create(user);
        }
    }
}
