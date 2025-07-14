package com.example.basicproject.member.dto;

public class MemberUpdateResponseDto {
    // 속성
    private String email;
    private String username;

    // 생성자
    public MemberUpdateResponseDto(String email, String username) {
        this.email = email;
        this.username = username;
    }

    // 기능
    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
