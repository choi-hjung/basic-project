package com.example.basicproject.task.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskUpdateResponseDto {
    // 속성
    private String title;
    private String content;
    private LocalDate dueDate;
    private LocalDate updatedAt;

    // 생성자
    public TaskUpdateResponseDto(String title, String content, LocalDate dueDate, LocalDate updatedAt) {
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;
        this.updatedAt = updatedAt;
    }

    // 기능
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }
}
