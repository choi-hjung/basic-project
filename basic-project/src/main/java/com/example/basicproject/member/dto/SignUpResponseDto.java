package com.example.basicproject.member.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SignUpResponseDto {
    // 속성
    private int status;
    private String message;
    private Long id;
    private String email;
    private String userName;
    private LocalDate createdAt;

    // 생성자
    public SignUpResponseDto(int status, String message, Long id, String email, String userName, LocalDate createdAt) {
        this.status = status;
        this.message = message;
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.createdAt = createdAt;
    }
    // 기능
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
