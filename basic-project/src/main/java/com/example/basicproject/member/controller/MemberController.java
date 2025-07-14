package com.example.basicproject.member.controller;

import com.example.basicproject.member.dto.*;
import com.example.basicproject.member.service.JwtService;
import com.example.basicproject.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/members")
public class MemberController {
    // 속성
    private final MemberService memberService;
    private final JwtService jwtService;

    // 생성자
    public MemberController(MemberService memberService, JwtService jwtService) {
        this.memberService = memberService;
        this.jwtService = jwtService;
    }

    // 기능
    // 회원생성
    @PostMapping
//    public MemberCreateResponseDto createAPI(@RequestBody MemberCreateRequestDto requestDto) {
//        MemberCreateResponseDto responseDto = memberService.create(requestDto);
//
//        return responseDto;
//    }
    public SignUpResponseDto signUpAPI(@RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = memberService.signUpService(requestDto);

        return responseDto;
    }

    // 로그인
    @PostMapping("/login")
    public LoginResponseDto loginAPI(@RequestBody LoginRequestDto requestDto) {
        LoginResponseDto responseDto = memberService.loginService(requestDto);

        return responseDto;
    }

    // 단건 조회
    @GetMapping("/{id}")
    public MemberDetailResponseDto memberDetailAPI(@PathVariable Long id) {
        MemberDetailResponseDto responseDto = memberService.getMemberDetail(id);

        return responseDto;
    }
    // 다건 조회
    @GetMapping
    public MemberListResponseDto memberListAPI() {
        MemberListResponseDto listResponseDto = memberService.getMemberList();

        return listResponseDto;
    }
    // 회원 정보 수정
    @PatchMapping("/{id}")
    public MemberUpdateResponseDto memberUpdateAPI(
            HttpServletRequest request,
            @PathVariable Long id,
            @RequestBody MemberUpdateRequestDto requestDto
    ) {
        // 1. 헤더에서 토큰 추출
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);

        // 2. 토큰 검증
        Long memberId = jwtService.verifyToken(token);
        if(!id.equals(memberId)) {
            throw new RuntimeException("작성자만 수정할 수 있습니다.");
        }

        // 3. 비즈니스 로직 호출
        MemberUpdateResponseDto updateResponseDto = memberService.memberUpdate(memberId, requestDto);

        return updateResponseDto;
    }
    // 회원 탈퇴
    @DeleteMapping("/{id}")
    public MemberDeleteResponseDto memberDeleteAPI(
            HttpServletRequest request,
            @PathVariable Long id,
            @RequestBody MemberDeleteRequestDto requestDto
    ) {
        // 1. 헤더에서 토큰 추출
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);

        // 2. 토큰 검증
        Long memberId = jwtService.verifyToken(token);
        if(!id.equals(memberId)) {
            throw new RuntimeException("본인만 삭제할 수 있습니다.");
        }

        // 3. 비즈니스 로직 호출
        MemberDeleteResponseDto responseDto = memberService.memberDelete(memberId, requestDto);

        // 4. 반환
        return responseDto;
    }
}
