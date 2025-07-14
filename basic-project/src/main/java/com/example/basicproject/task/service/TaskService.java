package com.example.basicproject.task.service;

import com.example.basicproject.member.domain.Member;
import com.example.basicproject.member.repository.MemberRepository;
import com.example.basicproject.task.domain.Task;
import com.example.basicproject.task.dto.*;
import com.example.basicproject.task.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    // 속성
    private final TaskRepository taskRepository;
    private final MemberRepository memberRepository;

    // 생성자
    public TaskService(TaskRepository taskRepository, MemberRepository memberRepository) {
        this.taskRepository = taskRepository;
        this.memberRepository = memberRepository;
    }

    // 기능
    // 태스크 생성
    @Transactional
    public TaskCreateResponseDto taskCreate(TaskCreateRequestDto requestDto) {
        // 1. 데이터 준비
        Long memberId = requestDto.getMemberId();
        String title = requestDto.getTitle();
        String content = requestDto.getContent();
        LocalDate dueDate = requestDto.getDueDate();

        // 1-2. 멤버 찾기(memberId 불러오기)
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("member is not found"));

        // 2. 엔티티 만들기(저장하기 위한)
        Task newTask = new Task(findMember, title, content, dueDate);

        // 3. 저장
        Task saveTask = taskRepository.save(newTask);

        // 4. 응답 dto 만들기
        TaskCreateResponseDto responseDto = new TaskCreateResponseDto(
                saveTask.getId(),
                saveTask.getTitle(),
                saveTask.getContent(),
                saveTask.getDueDate(),
                saveTask.getCreatedAt()
        );

        // 5. dto 반환하기
        return responseDto;
    }

    // 태스크 단건 조회
    public TaskDetailResponseDto taskDetail(Long taskId) {
        // 1. 데이터 준비
        Long taskID = taskId;

        // 2. 조회
        Task findTask = taskRepository.findById(taskID)
                .orElseThrow(() -> new RuntimeException("task is not found"));

        // 3. 응답 dto 만들기
        TaskDetailResponseDto responseDto = new TaskDetailResponseDto(
                findTask.getId(),
                findTask.getTitle(),
                findTask.getContent(),
                findTask.getDueDate(),
                findTask.getCreatedAt(),
                findTask.getUpdatedAt()
        );

        // 4. dto 반환하기
        return responseDto;
    }

    // 태스크 다건 조회 - 리스트
    public TaskListResponseDto taskList() {
        // 1. 데이터 준비 = 필요없음

        // 2. 리스트 조회
        List<Task> taskList = taskRepository.findAll();

        // 3. 응답 dto 만들기
        List<TaskDto> taskDtoList = taskList.stream()
                .map(task -> new TaskDto(
                        task.getTitle(),
                        task.getContent(),
                        task.getDueDate()))
                .collect(Collectors.toList());

        TaskListResponseDto listResponseDto = new TaskListResponseDto(taskDtoList);

        // 4. dto 반환하기
        return listResponseDto;
    }

    // 태스크 수정
    @Transactional
    public TaskUpdateResponseDto taskUpdate(Long id, TaskUpdateRequestDto requestDto) {
        // 1. 데이터 준비
        Long taskId = id;
        Long memberId = requestDto.getMemberId();
        String title = requestDto.getTitle();
        String content = requestDto.getContent();
        LocalDate dueDate = requestDto.getDueDate();

        // 2. 조회
        Task findTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("task is not found"));

        Member taskMember = findTask.getMember();
        Long taskMemberId = taskMember.getId();

        // 3. 업데이트
        // memberId와 taskMemberId가 같아야 task 수정이 가능하게 할 수 있다.
        if (!memberId.equals(taskMemberId)) {
            System.out.println("다른 사람입니다.");
            throw new RuntimeException("본인이 작성한 태스크만 수정할 수 있습니다.");
        }
        Task updateTask = findTask.updateTaskInfo(title, content, dueDate);

        System.out.println("같은 사람입니다.");

        // 4. 응답 dto 만들기
        TaskUpdateResponseDto responseDto = new TaskUpdateResponseDto(
                updateTask.getTitle(),
                updateTask.getContent(),
                updateTask.getDueDate(),
                updateTask.getUpdatedAt()
        );

        // 5. dto 반환하기
        return responseDto;
    }

    // 태스크 삭제
    @Transactional
    public TaskDeleteResponseDto taskDelete(Long id, TaskDeleteRequestDto requestDto) {
        // 1. 데이터 준비
        Long taskId = id;
        Long memberId = requestDto.getMemberId();
        String password = requestDto.getPassword();

        // 2. 조회
        Task findTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("task is not found"));

        Member taskMember = findTask.getMember();
        Long taskMemberId = taskMember.getId();
        String taskMemberPassword = taskMember.getPassword();

        // 3. 삭제
        if(!password.equals(taskMemberPassword)) {
           throw new RuntimeException("사용자 비밀번호가 일치하지 않습니다.");
        }
        if(!memberId.equals(taskMemberId)) {
            throw new RuntimeException("본인이 작성한 태스크만 삭제할 수 있습니다.");
        }
        findTask.softDelete();

        // 4. 응답 dto 만들기
        TaskDeleteResponseDto responseDto = new TaskDeleteResponseDto(200, "태스크 삭제를 완료했습니다.");

        // 5. dto 반환하기
        return responseDto;
    }
}
