package com.example.basicproject.task.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskUpdateRequestDto {
    // 속성
    private Long memberId;
    private String title;
    private String content;
    private LocalDate dueDate;

    // 생성자
    public TaskUpdateRequestDto(Long memberId, String title, String content, LocalDate dueDate) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;
    }

    // 기능
    public Long getMemberId() {
        return memberId;
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

}
