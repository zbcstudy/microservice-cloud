package com.wondertek.springcloud.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by win on 2019/4/19.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint,Serializable {

    private static final long serialVersionUID = 6590026716050175462L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "unauthorized");
    }
}
