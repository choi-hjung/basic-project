package com.example.basicproject.task.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskDetailResponseDto {
    // 속성
    private Long id;
    private String title;
    private String content;
    private LocalDate dueDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    // 생성자
    public TaskDetailResponseDto(Long id, String title, String content, LocalDate dueDate, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 기능
    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }
}
