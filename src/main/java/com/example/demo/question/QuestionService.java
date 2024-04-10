package com.example.demo.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.demo.DataNotFoundException;


@RequiredArgsConstructor
@Service	//서비스 애너테이션
public class QuestionService {

		private final QuestionRepository questionRepository;	//롬북으로 만들어진 생성자에 의해 객체가 자동으로 생성된다.
		
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
