package com.example.basicproject.member.dto;
// 보여주는 응답
public class MemberCreateResponseDto {
    // 속성
    private Long id;
    private String email;
    private String userName;

    // 생성자
    public MemberCreateResponseDto(Long id, String email, String userName) {
        this.id = id;
        this.email = email;
        this.userName = userName;
    }

    // 기능
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }
}
