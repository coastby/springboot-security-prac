package com.example.securityprac.controller;

import com.example.securityprac.dto.*;
import com.example.securityprac.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @ApiOperation(value = "회원 가입")
    @PostMapping
    public Response<UserJoinResponse> join (@RequestBody UserJoinRequest userJoinRequest){
        UserDto userDto = userService.join(userJoinRequest);
        return Response.success(new UserJoinResponse(userDto.getUserName(), userDto.getEmail()));
    }
    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public Response<UserLoginResponse> login (@RequestBody UserLoginRequest userLoginRequest){
        String token = userService.login(userLoginRequest.getUserName(), userLoginRequest.getPassword());
        return Response.success(new UserLoginResponse(token));
    }
}
