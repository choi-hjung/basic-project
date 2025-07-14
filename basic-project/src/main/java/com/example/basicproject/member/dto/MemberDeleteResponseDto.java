package com.example.basicproject.member.dto;

public class MemberDeleteResponseDto {
    // 속성
    private int status;
    private String message;

    // 생성자
    public MemberDeleteResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // 기능
    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
}
