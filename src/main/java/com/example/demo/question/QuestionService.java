package com.example.demo.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.example.demo.DataNotFoundException;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service	//서비스 애너테이션
public class QuestionService {

		private final QuestionRepository questionRepository;	//롬북으로 만들어진 생성자에 의해 객체가 자동으로 생성된다.
		
		//Pageable 객체를 입력받아 Page<Question>타입의 객체를 리턴하는 findAll 매서드 
		public Page<Question> getList(int page){	
			List<Sort.Order> sorts = new ArrayList<>();
			sorts.add(Sort.Order.desc("createDate")); //desc 내리차순, asc 오름차순
			Pageable pageable = PageRequest.of(page, 10,Sort.by(sorts)); //page: 조회할 페이즈 번호, 10: 한페이지에 보여줄 게시물 수
			return this.questionRepository.findAll(pageable);
		}
		
		public List<Question> getList(){
			return this.questionRepository.findAll();
		}
		
		public Question getQuestion(Integer id) {
			Optional<Question> question = this.questionRepository.findById(id);
			if(question.isPresent()) {
				return question.get();
			}else {
				throw new DataNotFoundException("question not found");
			}
		}
		public void create(String subject, String content) {
	        Question q = new Question();
	        q.setSubject(subject);
	        q.setContent(content);
	        q.setCreateDate(LocalDateTime.now());
	        this.questionRepository.save(q);
	    }
}
