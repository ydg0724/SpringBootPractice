package com.example.demo.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//CRUD작업을 처리하는 메서드들 내장하고 있는 인터페이스
public interface QuestionRepository extends JpaRepository<Question, Integer> {	
	Question findBySubject(String subject); 
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);
}
