package com.example.basicproject.member.dto;

public class MemberDto {
    // 속성
    private String email;
    private String userName;

    // 생성자
    public MemberDto(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }

    // 기능
    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }
}
