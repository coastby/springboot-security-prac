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
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
class UserRestControllerTest {
    @MockBean
    UserService userService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("회원가입")
    void join() throws Exception {
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("hoon")
                .password("password")
                .email("jo@email.com")
                .build();

        given(userService.join(any())).willReturn(UserDto.builder().userName("hoon").email("jo@email.com").build());
//        when(userService.join(any())).thenReturn(mock(UserDto.class));

        mockMvc.perform(
                post("/api/v1/users")
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
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("hoon")
                .password("password")
                .email("jo@email.com")
                .build();

        given(userService.join(any())).willThrow(new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, ""));

        mockMvc.perform(
                post("/api/v1/users")
                        .content(objectMapper.writeValueAsBytes(userJoinRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andDo(print());
    }
}