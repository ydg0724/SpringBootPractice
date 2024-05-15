package com.example.demo.question;

import com.example.demo.answer.AnswerForm;
import com.example.demo.user.SiteUser;
import com.example.demo.user.UserService;
import java.util.List;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor; //final이 붙은 속송을 포함하는 생성자를 자동으로 만들어주는 역할

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

		private final QuestionService questionService; //서비스 선언
		private final UserService userService;
		
		
		//매개변수로 Model을 지정하면 객체가 자동으로 생성된다.
		//RequestParam : GET 방식으로 요청된 URL를 표현하기 위한 어노테이션
		@GetMapping("/list")
	    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
	            @RequestParam(value = "kw", defaultValue = "") String kw) {
	        Page<Question> paging = this.questionService.getList(page, kw);
	        model.addAttribute("paging", paging);
	        model.addAttribute("kw", kw);
	        return "question_list";
	    }
		
		@GetMapping(value="/detail/{id}")
		public String detail(Model model, @PathVariable("id") Integer id,AnswerForm answerForm) {	//변하는 id값을 얻을 때는 PathVariable애너테이션 사용
			Question question = this.questionService.getQuestion(id);
			model.addAttribute("question",question);
			return "question_detail";
		}
		@PreAuthorize("isAuthenticated()")	//로그인을 안한 상태로 글을 쓰면 로그인 페이지로 이동한다
		@GetMapping("/create")
	    public String questionCreate(QuestionForm questionForm) {
	        return "question_form";
	    }
		
		
		@PreAuthorize("isAuthenticated()")	//로그인을 안한 상태로 글을 쓰면 로그인 페이지로 이동한다
		@PostMapping("/create")
	    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult,
	    		Principal principal) {
	        if (bindingResult.hasErrors()) {
	            return "question_form";
	        }
	        SiteUser siteUser = this.userService.getUser(principal.getName());
	        this.questionService.create(questionForm.getSubject(), questionForm.getContent(),siteUser);
	        return "redirect:/question/list";
	    }
		
		//질문 수정
		@PreAuthorize("isAuthenticated()")
	    @GetMapping("/modify/{id}")
	    public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
	        Question question = this.questionService.getQuestion(id);
	        if(!question.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	        }
	        questionForm.setSubject(question.getSubject());
	        questionForm.setContent(question.getContent());
	        return "question_form";
	    }
		
		@PreAuthorize("isAuthenticated()")
	    @PostMapping("/modify/{id}")
	    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult, 
	            Principal principal, @PathVariable("id") Integer id) {
	        if (bindingResult.hasErrors()) {
	            return "question_form";
	        }
	        Question question = this.questionService.getQuestion(id);
	        if (!question.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	        }
	        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
	        return String.format("redirect:/question/detail/%s", id);
	    }
		
		@PreAuthorize("isAuthenticated()")
	    @GetMapping("/delete/{id}")
	    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
	        Question question = this.questionService.getQuestion(id);
	        if (!question.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
	        }
	        this.questionService.delete(question);
	        return "redirect:/";
	    }
		
		@PreAuthorize("isAuthenticated()")
	    @GetMapping("/vote/{id}")
	    public String questionVote(Principal principal, @PathVariable("id") Integer id) {
	        Question question = this.questionService.getQuestion(id);
	        SiteUser siteUser = this.userService.getUser(principal.getName());
	        this.questionService.vote(question, siteUser);
	        return String.format("redirect:/question/detail/%s", id);
	    }
}
