package com.example.basicproject.member.domain;

import com.example.basicproject.global.common.BaseEntity;
import jakarta.persistence.*;


@Entity
@Table(name = "members")
public class Member extends BaseEntity {
    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 사용자아이디(이메일)
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    // 사용자명
    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    // isDelete - ture : 삭제, false : 삭제안됨
    private boolean isDeleted = false;

    // 생성자
    // 기본 생성자 => protected
    protected Member() {}

//    public Member(String email, String password, String userName) {
//        this.email = email;
//        this.password = password;
//        this.userName = userName;
//    }

    public Member(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    // 게터
    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getUserName() {
        return userName;
    }

    // 기능
    public Member updateEmailAndPasswordAndUsername(String password, String userName) {
        this.password = password;
        this.userName = userName;

        return this;
    }

    public void softDelete() {
        this.isDeleted = true;
    }
}
