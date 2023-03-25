package com.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * {@code @Description:}
 */
public class JWTUtils {
    public static final String SECRET = "qwertyuiop";
    
    /**
     * 创建JWT
     *
     * @param claimMap 声明的集合
     * @param plusDays 过期时长（单位：天）
     * @return JWT字符串
     */
    public static String createJWT(Map<String, String> claimMap, long plusDays) {
        JWTCreator.Builder builder = JWT.create();
        claimMap.forEach(builder::withClaim);
        
        String jwt = builder.withExpiresAt(Date.from(LocalDateTime.now().plusDays(plusDays).atZone(ZoneId.systemDefault()).toInstant()))
                            .sign(Algorithm.HMAC256(SECRET));
        return jwt;
    }
    
    /**
     * 验证JWT
     *
     * @param jwt JWT字符串
     */
    public static void verifyJWT(String jwt) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        jwtVerifier.verify(jwt);
    }
    
    /**
     * 获取JWT解码器
     *
     * @param jwt JWT字符串
     * @return DecodedJWT解码器
     */
    public static DecodedJWT getJWTInfo(String jwt) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(jwt);
    }
}