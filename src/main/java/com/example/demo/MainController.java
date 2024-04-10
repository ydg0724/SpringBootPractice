package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class MainController {
	@GetMapping("/sbb")
	@ResponseBody
	public String index() {
		System.out.println("hello world!!!");
		return "안녕하세요 sbb에 오신 것을 환영합니다.";
	}
	
	@GetMapping("/")
	public String root() {
		return "redirect:/question/list";	//리다이렉트할 URL -> root로 접속해서 해당 주소로 이동
	}
}
