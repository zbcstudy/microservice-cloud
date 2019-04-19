package com.wondertek.springcloud.security.controller;

import com.wondertek.springcloud.security.JwtTokenUtil;
import com.wondertek.springcloud.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by win on 2019/4/19.
 */
@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public JwtUser getAuthenticationUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String userName = jwtTokenUtil.getUserNameFromToken(token);
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(userName);
        return jwtUser;
    }
}
