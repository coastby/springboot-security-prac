package com.example.securityprac.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

public class JwtTokenUtil {
    /*
    * token 생성하는 함수
    * */
    public static String generateToken(String userName, String key, long expiredTimeMs){
        Claims claims = Jwts.claims();  //토큰의 내용에 값을 넣기 위해 Claims 객체 생성
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
    //token의 Claim을 분리하는 메서드
    public static Claims extractClaims(String token, String key){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
    //token이 만료되었는지 체크
    public static boolean isExpired(String token, String secretKey) {
        Date expiredDate = extractClaims(token, secretKey).getExpiration(); //token의 유효기간 추출
        return expiredDate.before(new Date());      //유효기간이 지금보다 전인지 확인
    }
}
