package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.demo.question.QuestionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.answer.Answer;
import com.example.demo.answer.AnswerRepository;
import com.example.demo.question.Question;
import com.example.demo.question.QuestionRepository;

@SpringBootTest
class SbbApplicationTests {

	
	@Autowired
	private QuestionService questionService;
	
	@Test
	void testJpa() {
		for(int i=1;i<=300;i++) {
			String subject = String.format("테스트 데이터입니다.:[%03d]", i);
			String content = "내용 없음";
			this.questionService.create(subject, content);
		}
	/*@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	
	@Transactional
	@Test
	void testJpa() {
		
		 //데이터 저장
	 	Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		//q1.setId(2019150028);
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("스프링 부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);*/
		
		/*
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList();
		
		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());*/

		
		
		/* 답변 데이터 조회 및 연결된 질문 확인
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2,a.getQuestion().getId());
		*/
		
		/*answer 테이블 삽입
		//질문 데이터 조회
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		
		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
		*/
		
		/*
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());*/
		
		/*	update
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());	//괄호안 값 참 확인
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
		*/
		
		
		/* findBySubjectLike
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());*/
		
		/* findBySubjectAndContent
		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertEquals(1, q.getId());*/
		
		/* findBySubject
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());*/
		
		
		/* findById
		Optional<Question> oq = this.questionRepository.findById(1);
		if(oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}
		
		/* findAll
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());
		
		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());*/
		
		
		
	}

}
