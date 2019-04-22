package com.wondertek.springcloud.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Created by win on 2019/4/19.
 */
@Component
public class JwtTokenUtil implements Serializable {

    public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";

    static final String CLAIM_KEY_USER_ID = "user_id";
    static final String CLAIM_KEY_AUTHORITIES = "scope";

    //save token
    private Map<String, String> tokenMap = new ConcurrentHashMap<>();

    private Clock clock = DefaultClock.INSTANCE;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long access_token_expiration;

    @Value("${jwt.expiration}")
    private Long refresh_token_expiration;

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date create, Date lastPasswordReset) {
        return (lastPasswordReset != null && create.before(clock.now()));
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        final Date createDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createDate);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setId(UUID.randomUUID().toString().replace("_",""))
                .setIssuedAt(createDate)
                .setExpiration(expirationDate)
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SignatureAlgorithm.ES512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date create = getIssuedAtDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(create, lastPasswordReset)
                && (isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.ES512, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser jwtUser = (JwtUser) userDetails;
        final String userName = getUserNameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);
//        final Date expiration = getExpirationDateFromToken(token);
        return (userName.equals(jwtUser.getUsername())
                && !isTokenExpired(token)
                && !isCreatedBeforeLastPasswordReset(created, jwtUser.getLastPasswordResetDate()));
    }

    private Date calculateExpirationDate(Date createDate) {
        return new Date(createDate.getTime() + access_token_expiration * 1000);
    }

    public void putToken(String userName, String token) {
        tokenMap.put(userName, token);
    }

    public void deleteToken(String userName) {
        tokenMap.remove(userName);
    }

    public boolean containToken(String userName,String token) {
        if (userName != null && tokenMap.containsKey(userName) && tokenMap.get(userName).equals(token)) {
            return true;
        }
        return false;
    }

}
