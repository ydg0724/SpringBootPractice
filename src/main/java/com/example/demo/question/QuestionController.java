package com.example.demo.question;

import com.example.demo.answer.AnswerForm;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor; //final이 붙은 속송을 포함하는 생성자를 자동으로 만들어주는 역할

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

		private final QuestionService questionService; //서비스 선언
		
		@GetMapping("/list")
		//@ResponseBody
		public String list(Model model) {	//매개변수로 Model을 지정하면 객체가 자동으로 생성된다.
			List<Question> questionList = this.questionService.getList();	//서비스로 questionList(리포지터리) 호출
			model.addAttribute("questionList", questionList);
			return "question_list";
		}
		
		@GetMapping(value="/detail/{id}")
		public String detail(Model model, @PathVariable("id") Integer id,AnswerForm answerForm) {	//변하는 id값을 얻을 때는 PathVariable애너테이션 사용
			Question question = this.questionService.getQuestion(id);
			model.addAttribute("question",question);
			return "question_detail";
		}
		
		@GetMapping("/create")
	    public String questionCreate(QuestionForm questionForm) {
	        return "question_form";
	    }
		
		@PostMapping("/create")
	    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	            return "question_form";
	        }
	        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
	        return "redirect:/question/list";
	    }
}
