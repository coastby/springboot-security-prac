## SpringBoot Security 
교재 : 스프링부트 핵심가이드

Gradle Project
Spring Boot 2.7.5
Java 11

<details>
<summary>Dependency</summary>
<div markdown="1">


</div>
</details>

Dependency :
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


## 최종 목표
> 회원 가입 :
> - 아이디가 중복되면 안 된다.
> - 비밀번호를 DB에 저장 시 암호화되어서 저장된다.
> 
> 로그인 :
> - 해당되는 아이디가 없으면 예외가 발생한다.
> - 비밀번호가 일치하지 않으면 예외가 발생한다.
> - 로그인이 되면 토큰이 발급된다.
> 
> 리뷰쓰기 :
> - 로그인을 한 사람만 리뷰를 쓸 수 있다.


## Nov 28, 2022
### 📎 아이디가 중복되면 예외를 발생시킨다.
- custom exception
- @RestControllerAdvise, @ExceptionHandler



## Nov 29, 2022
### 📎 회원가입 기능
- `POST /api/v1/users` 로 아이디와 패스워드를 받으면 패스워드는 암호화되어서 저장된다.