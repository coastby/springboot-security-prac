package com.example.securityprac.service;

import com.example.securityprac.dto.UserDto;
import com.example.securityprac.dto.UserJoinRequest;
import com.example.securityprac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserDto join (UserJoinRequest request){
        return new UserDto();
    }
}
