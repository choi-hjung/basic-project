package com.example.basicproject.member.dto;

import java.util.List;

public class MemberListResponseDto {
    // 속성
    private List<MemberDto> memberList;

    // 생성자
    public MemberListResponseDto(List<MemberDto> memberList) {
        this.memberList = memberList;
    }

    // 기능
    public List<MemberDto> getMemberList() {
        return memberList;
    }
}
