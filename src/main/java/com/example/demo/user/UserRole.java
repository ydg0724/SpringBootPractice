//권한을 주는 클래스

package com.example.demo.user;

import lombok.Getter;


@Getter
public enum UserRole {	//열거 자료형
    ADMIN("ROLE_ADMIN"),	//관리자
    USER("ROLE_USER");		//사용자

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}