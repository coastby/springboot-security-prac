## SpringBoot Security 
교재 : 스프링부트 핵심가이드

Gradle Project
Spring Boot 2.7.5
Java 11

<details>
<summary>Dependency</summary>
<div markdown="1">

- spring-boot-starter-web
- spring-boot-starter-test
- spring-boot-starter-data-jpa
- lombok
- mysql
- spring-boot-configuration-proccesor
- springfox-swagger-ui
- jjwt
- spring-boot-starter-security
- spring-security-test

</div>
</details>




## 최종 목표
> 회원 가입 :
- 아이디가 중복되면 안 된다.
- 비밀번호를 DB에 저장 시 암호화되어서 저장된다.

> 로그인 :
- 해당되는 아이디가 없으면 예외가 발생한다.
- 비밀번호가 일치하지 않으면 예외가 발생한다.
- 로그인이 되면 토큰이 발급된다.
 
> 리뷰쓰기 :
- 로그인을 한 사람만 리뷰를 쓸 수 있다.


## Nov 28, 2022
### 📎 아이디가 중복되면 예외를 발생시킨다.
- `POST /api/v1/users` 회원 가입 시
- **custom exception**
- @RestControllerAdvise, @ExceptionHandler
- Enum을 이용한 ErrorCode 관리



## Nov 29, 2022
### 📎 회원가입 기능
- `POST /api/v1/users` 로 아이디와 패스워드를 받으면 패스워드는 암호화되어서 저장된다.
- Spring Security 적용, SecurityFilterChain 설정
- 비밀번호 인코딩해서 DB에 저장하기
- 테스트 코드 security에 맞게 수정하기
### 📎 로그인하면 토큰 발급해주기
- 로그인 시 아이디가 없거나 비밀번호가 맞지 않으면 예외 발생
- 로그인 성공 시 JWT를 반환

## Dec 5, 2022
### 📎 로그인해야 리뷰 쓰기 가능
- 헤더 형식이 맞는 지 검사
- 헤더에서 토큰 분리하여 유효한지 검사
- 토큰에서 정보 분리하여 Authorization에 정보 넣어주기
- 유효한 토큰이 헤더에 있어야 댓글쓰기 API 통과 가능