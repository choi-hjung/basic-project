package com.example.basicproject.task.controller;

import com.example.basicproject.task.dto.*;
import com.example.basicproject.task.service.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    // 속성
    private final TaskService taskService;

    // 생성자
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 기능
    // 태스크 생성
    @PostMapping
    public TaskCreateResponseDto taskCreateAPI(
            @RequestBody TaskCreateRequestDto requestDto
    ) {
        TaskCreateResponseDto responseDto = taskService.taskCreate(requestDto);

        return responseDto;
    }

    // 태스크 단건 조회
    @GetMapping("{taskId}")
    public TaskDetailResponseDto taskDetailAPI(@PathVariable Long taskId) {
        TaskDetailResponseDto responseDto = taskService.taskDetail(taskId);

        return responseDto;
    }

    // 태스크 다건 조회 - 리스트
    @GetMapping
    public TaskListResponseDto taskListAPI() {
        TaskListResponseDto listResponseDto = taskService.taskList();

        return listResponseDto;
    }

    // 태스크 수정
    @PatchMapping("{taskId}")
    public TaskUpdateResponseDto taskUpdateAPI(
            @PathVariable Long taskId,
            @RequestBody TaskUpdateRequestDto requestDto
    ) {
        TaskUpdateResponseDto responseDto = taskService.taskUpdate(taskId, requestDto);

        return responseDto;
    }

    // 태스크 삭제
    @DeleteMapping("{taskId}")
    public TaskDeleteResponseDto taskDeleteAPI(
            @PathVariable Long taskId,
            @RequestBody TaskDeleteRequestDto requestDto
    ) {
        TaskDeleteResponseDto responseDto = taskService.taskDelete(taskId, requestDto);

        return responseDto;
    }
}
