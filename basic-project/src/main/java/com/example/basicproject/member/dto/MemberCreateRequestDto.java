package com.example.basicproject.member.dto;

public class MemberCreateRequestDto {
    // 속성
    private String email;
    private String password;
    private String userName;

    // 생성자
    public MemberCreateRequestDto(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    // 기능
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
