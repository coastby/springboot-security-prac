package com.example.securityprac.controller;

import com.example.securityprac.domain.User;
import com.example.securityprac.dto.UserDto;
import com.example.securityprac.dto.UserJoinRequest;
import com.example.securityprac.exception.ErrorCode;
import com.example.securityprac.exception.HospitalReviewAppException;
import com.example.securityprac.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
@WithMockUser
class UserRestControllerTest {
    @MockBean
    UserService userService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    UserJoinRequest userJoinRequest = UserJoinRequest.builder()
            .userName("hoon")
            .password("password")
            .email("jo@email.com")
            .build();

    @Test
    @DisplayName("회원가입")
    void join() throws Exception {
        given(userService.join(any())).willReturn(UserDto.builder().userName("hoon").email("jo@email.com").build());
//        when(userService.join(any())).thenReturn(mock(UserDto.class));

        mockMvc.perform(
                post("/api/v1/users")
                        .with(csrf())
                        .content(objectMapper.writeValueAsBytes(userJoinRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.userName").exists())
                .andExpect(jsonPath("$.result.email").exists())
                .andDo(print());
    }
    @Test
    @DisplayName("중복 아이디 예외 발생")
    void joinFail() throws Exception {
        given(userService.join(any())).willThrow(new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, ""));

        mockMvc.perform(
                post("/api/v1/users")
                        .with(csrf())
                        .content(objectMapper.writeValueAsBytes(userJoinRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andDo(print());
    }
    @Test
    @DisplayName("로그인 실패: id 없음")
    void login_fail_idx() throws Exception {
        given(userService.login(any(), any())).willThrow(new HospitalReviewAppException(ErrorCode.USER_NOT_FOUND, ""));

        mockMvc.perform(
                        post("/api/v1/users/login")
                                .with(csrf())
                                .content(objectMapper.writeValueAsBytes(userJoinRequest))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    @DisplayName("로그인 실패: 잘못된 비밀번호")
    void login_fail_password() throws Exception {
        given(userService.login(any(), any())).willThrow(new HospitalReviewAppException(ErrorCode.INVALID_PASSWORD, ""));

        mockMvc.perform(
                        post("/api/v1/users/login")
                                .with(csrf())
                                .content(objectMapper.writeValueAsBytes(userJoinRequest))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    @DisplayName("로그인 성공")
    void login(){

    }
}