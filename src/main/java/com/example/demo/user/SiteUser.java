package com.example.demo.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity	//이 클래스가 데이터베이스에 매핑될 애너테이션
public class SiteUser {

    @Id		//기본 키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)	//기본키를 자동으로 생성할 것을 지정
    private Long id;

    @Column(unique = true)	//유일한 값만 저장가능
    private String username;

    private String password;

    @Column(unique = true)
    private String email;
}