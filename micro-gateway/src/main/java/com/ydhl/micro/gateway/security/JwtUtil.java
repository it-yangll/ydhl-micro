package com.ydhl.micro.gateway.security;

import cn.hutool.crypto.SecureUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ydhl.micro.gateway.consts.LoginType;
import com.ydhl.micro.gateway.consts.UserType;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @ClassName JwtUtil
 * @Description TODO
 * @Author yangll
 * @Date 2019/3/27 13:21
 * @Version 1.0
 **/
public class JwtUtil {

    public static final String JWT_NAME = "Authorization";
    private static final String JWT_PAYLOAD_AES_KEY = "hFjjn2f6TyiCymLn";

    public static String buildJWT(JwtBody jwtBody) {
        //生成jwt
        Date now = new Date();
        JWTCreator.Builder builder = JWT.create()
                .withJWTId(jwtBody.getJwtId())
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + jwtBody.getExpiresAt() * 1000));

        //私有负荷AES加密
        builder.withClaim("identity", SecureUtil.aes(JWT_PAYLOAD_AES_KEY.getBytes()).encryptHex(jwtBody.getIdentity()));
        builder.withClaim("loginType", SecureUtil.aes(JWT_PAYLOAD_AES_KEY.getBytes()).encryptHex(String.valueOf(jwtBody.getLoginType())));
        builder.withClaim("userType", SecureUtil.aes(JWT_PAYLOAD_AES_KEY.getBytes()).encryptHex(String.valueOf(jwtBody.getUserType())));
        builder.withClaim("source", SecureUtil.aes(JWT_PAYLOAD_AES_KEY.getBytes()).encryptHex(jwtBody.getIp()));
        builder.withClaim("pk", SecureUtil.aes(JWT_PAYLOAD_AES_KEY.getBytes()).encryptHex(String.valueOf(jwtBody.getPk())));
        builder.withClaim("name", SecureUtil.aes(JWT_PAYLOAD_AES_KEY.getBytes()).encryptHex(String.valueOf(jwtBody.getName())));

        return builder.sign(Algorithm.HMAC256(JWT_PAYLOAD_AES_KEY));
    }

    public static void verifyJWT(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_PAYLOAD_AES_KEY)).build();
        DecodedJWT jwt = verifier.verify(token);
    }

    public static JwtBody parseToJwtBody(String token) {
        JwtBody body = new JwtBody();
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_PAYLOAD_AES_KEY)).build();
        DecodedJWT jwt = verifier.verify(token);

        body.setJwtId(jwt.getId());
        body.setIssuedAt(jwt.getIssuedAt());
        body.setExpiresAt(jwt.getExpiresAt().getTime() - jwt.getIssuedAt().getTime());

        jwt.getClaims().forEach((K, V) -> {
            if (StringUtils.equalsIgnoreCase(K, "identity")) {
                body.setIdentity(SecureUtil.aes(JWT_PAYLOAD_AES_KEY.getBytes()).decryptStr(V.asString()));
            } else if (StringUtils.equalsIgnoreCase(K, "loginType")) {
                body.setLoginType(LoginType.valueOf(SecureUtil.aes(JWT_PAYLOAD_AES_KEY.getBytes()).decryptStr(V.asString())));
            } else if (StringUtils.equalsIgnoreCase(K, "userType")) {
                body.setUserType(UserType.valueOf(SecureUtil.aes(JWT_PAYLOAD_AES_KEY.getBytes()).decryptStr(V.asString())));
            } else if (StringUtils.equalsIgnoreCase(K, "source")) {
                body.setIp(SecureUtil.aes(JWT_PAYLOAD_AES_KEY.getBytes()).decryptStr(V.asString()));
            } else if (StringUtils.equalsIgnoreCase(K, "pk")) {
                body.setPk(Long.valueOf(SecureUtil.aes(JWT_PAYLOAD_AES_KEY.getBytes()).decryptStr(V.asString())));
            } else if (StringUtils.equalsIgnoreCase(K, "name")) {
                body.setName(SecureUtil.aes(JWT_PAYLOAD_AES_KEY.getBytes()).decryptStr(V.asString()));
            }
        });
        return body;
    }


    public static void main(String[] args) {

        JwtBody body = JwtBody.createJwtBody(UUID.randomUUID().toString(), 20 * 1000, "127.0.0.1", LoginType.ADMIN, UserType.EMPLOYEE, "袁霞", 1L, "姓名");

        String token = JwtUtil.buildJWT(body);
        System.out.println(token);
        JwtUtil.verifyJWT(token);
        System.out.println(JwtUtil.parseToJwtBody(token));
    }


}
