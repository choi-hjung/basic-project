package com.example.basicproject.task.dto;

import com.example.basicproject.member.domain.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskCreateResponseDto {
    // 속성
    private Long id;
    private String title;
    private String content;
    private LocalDate dueDate;
    private LocalDate createdAt;

    // 생성자
    public TaskCreateResponseDto(Long id,  String title, String content, LocalDate dueDate, LocalDate createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
    }
    // 기능
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
