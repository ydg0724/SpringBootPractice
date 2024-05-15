package com.example.demo.user;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

//인터페이스
public interface UserRepository extends JpaRepository<SiteUser, Long> {
	Optional<SiteUser> findByusername(String name);	//사용자 ID 조회
}

