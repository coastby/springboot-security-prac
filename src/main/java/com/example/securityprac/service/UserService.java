package com.example.securityprac.service;

import com.example.securityprac.domain.User;
import com.example.securityprac.dto.UserDto;
import com.example.securityprac.dto.UserJoinRequest;
import com.example.securityprac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //비지니스 로직 - 회원 가입
    public UserDto join (UserJoinRequest request){
        //userName이 중복되면 예외 발생
        userRepository.findByUserName(request.getUserName())
                .ifPresent(user -> new RuntimeException("이미 존재하는 아이디입니다."));
        User savedUser = userRepository.save(request.toEntity());
        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmail())
                .build();
    }
}
