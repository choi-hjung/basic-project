package com.example.basicproject.task.dto;

public class TaskDeleteRequestDto {
    // 속성
    private Long memberId;
    private String password;

    // 생성자
    public TaskDeleteRequestDto(Long memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }

    // 기능
    public Long getMemberId() {
        return memberId;
    }

    public String getPassword() {
        return password;
    }
}
