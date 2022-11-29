package com.example.securityprac.service;

import com.example.securityprac.domain.User;
import com.example.securityprac.dto.UserDto;
import com.example.securityprac.dto.UserJoinRequest;
import com.example.securityprac.exception.ErrorCode;
import com.example.securityprac.exception.HospitalReviewAppException;
import com.example.securityprac.repository.UserRepository;
import com.example.securityprac.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;    //비밀번호 인코더
    @Value("${jwt.token.secret}")
    private String secretKey;
    private long expiredTimeMs = 1000*60*60;    //1시간

    //비지니스 로직 - 회원 가입
    public UserDto join (UserJoinRequest request){
        //userName이 중복되면 예외 발생
        userRepository.findByUserName(request.getUserName())
                .ifPresent(user -> {
                    throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s는 이미 존재하는 아이디입니다.", request.getUserName()));
                });
        User savedUser = userRepository.save(request.toEntity(encoder.encode(request.getPassword())));  //request의 비밀번호를 인코딩하여 매개변수로 넘겨준다
        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmail())
                .build();
    }

    public String login(String userName, String password) {
        //id가 존재하는지 확인
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new HospitalReviewAppException(ErrorCode.USER_NOT_FOUND, String.format("아이디가 없습니다.")));
        //비밀번호가 일치하는지 확인
        if (!encoder.matches(password, user.getPassword())) {
            throw new HospitalReviewAppException(ErrorCode.INVALID_PASSWORD, String.format(("비밀번호가 틀렸습니다.")));
        }
        //토큰 생성
        String token = JwtTokenUtil.generateToken(user.getUserName(), secretKey, expiredTimeMs);
        return token;
    }
}
