package com.example.basicproject.member.dto;

public class LoginResponseDto {
    // 속성
    private int status;
    private String userName;
    private String message;
    private String jwtToken;

    // 생성자
    public LoginResponseDto(int status, String userName, String message, String jwtToken) {
        this.status = status;
        this.userName = userName;
        this.message = message;
        this.jwtToken = jwtToken;
    }

    // 기능
    public int getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
