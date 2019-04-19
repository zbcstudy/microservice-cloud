package com.wondertek.springcloud.security;

import java.io.Serializable;

/**
 * Created by win on 2019/4/19.
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 4890723035135600894L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
