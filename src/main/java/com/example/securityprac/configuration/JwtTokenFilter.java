package com.example.securityprac.configuration;

import com.example.securityprac.service.UserService;
import com.example.securityprac.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {      //api 요청을 할 때 한 번만 인증을 거친다??
    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Token에서 Claim 꺼내기
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){      //header에 AUTHORIZATION이 없거나, Bearer로 시작하지 않으면 filter
            log.error("header가 없거나, 형식이 틀립니다. - {}", authorizationHeader);
            filterChain.doFilter(request, response);
            return;
        }

        String token;
        try {
            token = authorizationHeader.split(" ")[1].trim();
        } catch (Exception e) {
            log.error("토큰을 분리하는데 실패했습니다. - {}", authorizationHeader);
            filterChain.doFilter((request, response));
            return;
        }
        log.info("token : {}", token);

        //토큰이 Valid한지 확인하기


        //userName 넣기, 문 열어주기
        //token 만들기
        UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken("", null, List.of(new SimpleGrantedAuthority("U ser")));
        //디테일 설정하기
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);

    }
}
