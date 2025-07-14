package com.example.basicproject.task.dto;

import java.util.List;

public class TaskListResponseDto {
    // 속성
    private List<TaskDto> taskList;

    // 생성자
    public TaskListResponseDto(List<TaskDto> taskList) {
        this.taskList = taskList;
    }

    // 기능
    public List<TaskDto> getTaskList() {
        return taskList;
    }
}
