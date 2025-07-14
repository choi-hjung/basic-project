package com.example.basicproject.member.dto;

public class SignUpRequestDto {
    // 속성
    private String email;
    private String password;
    private String checkPassword;
    private String userName;

    // 생성자
    public SignUpRequestDto(String email, String password, String checkPassword, String userName) {
        this.email = email;
        this.password = password;
        this.checkPassword = checkPassword;
        this.userName = userName;
    }

    // 기능
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public String getUserName() {
        return userName;
    }
}
