package com.example.basicproject.member.dto;

public class MemberDeleteRequestDto {
    // 속성
    private String password;

    // 생성자
    public MemberDeleteRequestDto(String password) {
        this.password = password;
    }

    // 기능
    public String getPassword() {
        return password;
    }
}
