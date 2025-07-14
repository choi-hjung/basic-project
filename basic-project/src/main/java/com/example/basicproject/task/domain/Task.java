package com.example.basicproject.task.domain;

import com.example.basicproject.global.common.BaseEntity;
import com.example.basicproject.member.domain.Member;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {
    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 연관된 Member (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    // 할 일 완료했는지
    @Column(name = "is_done")
    private boolean isDone = false;

    // 마감 기한
    @Column(name = "due_date")
    private LocalDate dueDate;

    // softDelete
    private boolean isDeleted = false;

    // 생성자
    public Task(Member member, String title, String content, LocalDate dueDate) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;
    }

    public Task() {}


    // 기능
    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isDone() {
        return isDone;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    // 업데이트
    public Task updateTaskInfo(String title, String content, LocalDate dueDate) {
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;

        return this;
    }
    // 삭제
    public void softDelete() {
        this.isDeleted = true;
    }
}
