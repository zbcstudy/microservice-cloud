package com.wondertek.springcloud.security;

import java.io.Serializable;

/**
 * Created by win on 2019/4/19.
 */
public class JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = 8657891003775340284L;

    private String userName;

    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
