package com.example.basicproject.member.dto;

public class MemberUpdateRequestDto {
    // 속성
    private String password;
    private String userName;

    // 생성자
    public MemberUpdateRequestDto(String password, String userName) {
        this.password = password;
        this.userName = userName;
    }

    // 기능
    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
