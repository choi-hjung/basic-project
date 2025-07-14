package com.example.basicproject.task.dto;

import java.time.LocalDate;

public class TaskDto {
    // 속성
    private String title;
    private String content;
    private LocalDate dueDate;

    // 생성자
    public TaskDto(String title, String content, LocalDate dueDate) {
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;
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
}
