package com.example.demo.question;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.answer.Answer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;	
import jakarta.persistence.GeneratedValue;	
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;	
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
	@Id//기본키
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//속성값 자동 증가 
	private Integer Id;	// 기본키에 값을 넣을 때 마다 기본키 값이 1씩 증가
	
	@Column(length = 200)	//열의 세부 설정
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	//mappedBy는 참조 엔티티의 속성명(Question)을 정의한다.
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) //1:N 관계
	private List<Answer> answerList;	//질문 하나에 답변에 여러 개 이므로 Answer 속성은 리스트로 표현
	// 질문에서 답변을 참조하려면 question.getAnswerList()를 호출하면 된다.
}
