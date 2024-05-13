package com.example.demo.user;


import org.springframework.data.jpa.repository.JpaRepository;

//인터페이스
public interface UserRepository extends JpaRepository<SiteUser, Long> {
}

