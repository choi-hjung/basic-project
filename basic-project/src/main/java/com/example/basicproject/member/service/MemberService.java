package com.example.basicproject.member.service;

import com.example.basicproject.global.config.PasswordEncoder;
import com.example.basicproject.member.domain.Member;
import com.example.basicproject.member.dto.*;
import com.example.basicproject.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    // 속성
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // 생성자
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // 기능
    @Transactional
    // 회원생성
//    public MemberCreateResponseDto create(MemberCreateRequestDto requestDto) {
//        // 1. 데이터 준비
//        String email = requestDto.getEmail();
//        String password = requestDto.getPassword();
//        String userName = requestDto.getUserName();
//
//        // 2. 엔티티 만들기(저장하려고)
//        Member newMember = new Member(email, password, userName);
//
//        // 3. 저장
//        Member savedMember = memberRepository.save(newMember);
//
//        // 4. 응답 dto 만들기
//        MemberCreateResponseDto responseDto = new MemberCreateResponseDto(
//                savedMember.getId(),
//                savedMember.getEmail(),
//                savedMember.getUserName()
//        );
//
//        // 5. dto 반환하기
//        return responseDto;
//    }
    // 회원 가입
    public SignUpResponseDto signUpService(SignUpRequestDto requestDto) {
        // 1. 데이터 준비
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();
        String checkPassword = requestDto.getCheckPassword();
        String userName = requestDto.getUserName();

        // 1-2. 검증로직
        // 이메일 중복 (repository에 있는 이메일과 requestDto에서 받아온 이메일이 중복되는지 확인하기)
        boolean existMemberEmail = memberRepository.existsByEmail(email);
        if (existMemberEmail) {
            throw new RuntimeException("이메일이 중복 되었습니다.");
        }
        // 이메일 유효성 검사
//        if(!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$")) {
//            throw new RuntimeException("이메일 형식이 잘못되었습니다.");
//        }
        // 비밀번호 검증 (8~15자리, 대문자랑 특수문자 포함)
//        ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^\w\s])[\S]{8,15}$
//        if (!(8 < password.length() && password.length() < 15)) {
//            throw new RuntimeException("비밀번호는 8자리부터 15자리 까지 입니다.");
//        }
        if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s])[\\S]{8,15}$")) {
            throw new RuntimeException("비밀번호는 영문자, 숫자, 특수문자를 조합하여 8자리에서 15자리로 작성해야 합니다.");
        }
        // 비밀번호 일치 확인
        if(!password.equals(checkPassword)) {
            throw new RuntimeException("입력한 비밀번호가 서로 일치하지 않습니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 2. 엔티티 만들기 (저장하려고)
        Member member = new Member(email, encodedPassword, userName);

        // 3. 저장
        Member savedMember = memberRepository.save(member);

        // 4. 응답 dto 만들기
        SignUpResponseDto responseDto = new SignUpResponseDto(200, "회원가입에 성공하셨습니다.", savedMember.getId(), savedMember.getEmail(), savedMember.getUserName(), savedMember.getCreatedAt());

        // 5. dto 반환
        return responseDto;
    }
    // 로그인
    public LoginResponseDto loginService(LoginRequestDto requestDto) {
        // 1. 데이터 준비
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        // 2. 조회
        // 이메일로 회원 확인
        Member findMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("이 이메일을 사용하는 회원이 없습니다."));

        // 3. 검증
        // 비밀번호 일치하면 로그인, 일치안하면 X
        boolean matchedPassword = passwordEncoder.matches(password, findMember.getPassword());
        if (!matchedPassword) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        // 4. 로그인 성공시 토큰 발급하기
        String jwtToken = jwtService.createJwt(findMember.getId());

        // 5. 응답 dto 만들기
        LoginResponseDto responseDto = new LoginResponseDto(
                200, findMember.getUserName(), "님이 로그인 하셨습니다.", jwtToken
        );

        // 6. dto 반환하기
        return responseDto;
    }

    // 회원 단건 조회
    public MemberDetailResponseDto getMemberDetail(Long id) {
        // 1. 데이터 준비
        Long memberId = id;

        // 2. 조회 - 삭제된 데이터 조회 안되게
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("member not found"));

        // 3. 응답 dto 만들기
        MemberDetailResponseDto responseDto = new MemberDetailResponseDto (
                findMember.getEmail(),
                findMember.getUserName()
        );

        // 4. dto 반환하기
        return responseDto;
    }

    // 회원 다건 조회
    public MemberListResponseDto getMemberList() {
        // 1. 데이터 준비 = 필요없음.

        // 2. 리스트 조회
        List<Member> memberList = memberRepository.findAll();

        // 3. 응답 dto 만들기
        List<MemberDto> memberDtoList = memberList.stream()
                .map(member -> new MemberDto(
                        member.getEmail(), member.getUserName()))
                .collect(Collectors.toList());

        MemberListResponseDto memberListResponseDto = new MemberListResponseDto(memberDtoList);

        return memberListResponseDto;
    }



    @Transactional
    // 회원 정보 수정
    public MemberUpdateResponseDto memberUpdate(Long id, MemberUpdateRequestDto requestDto) {
        // 1. 데이터 준비
        Long memberId = id;
        String password = requestDto.getPassword();
        String userName = requestDto.getUserName();

        // 2. 조회
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("member not found"));

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 3. 업데이트
        Member updateMember = findMember.updateEmailAndPasswordAndUsername(encodedPassword, userName);

        // 4. 응답 dto 만들기
        MemberUpdateResponseDto updateResponseDto = new MemberUpdateResponseDto(
                updateMember.getEmail(),
                updateMember.getUserName()
        );

        // 5. dto 반환
        return updateResponseDto;
    }

    @Transactional
    // 회원 탈퇴
    public MemberDeleteResponseDto memberDelete(Long id, MemberDeleteRequestDto requestDto) {
        // 1. 데이터 준비
        Long memberId = id;
        String password = requestDto.getPassword();

        // 2. 조회
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("member not found"));

        // 3. 삭제
        if (!passwordEncoder.matches(password, findMember.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        findMember.softDelete();

        // 4. 응답 dto 만들기
        MemberDeleteResponseDto deleteResponseDto = new MemberDeleteResponseDto(200, "회원 탈퇴가 완료되었습니다.");

        // 5. dto 반환
        return deleteResponseDto;
    }
}
