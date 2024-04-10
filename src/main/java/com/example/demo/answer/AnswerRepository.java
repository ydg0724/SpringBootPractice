package com.example.demo.answer;

import org.springframework.data.jpa.repository.JpaRepository;

//CRUD작업을 처리하는 메서드들 내장하고 있는 인터페이스
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
