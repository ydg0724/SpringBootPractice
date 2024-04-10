package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@GetMapping("/jump")
	@ResponseBody
	public String jump() {
		return "점프 투 스프링 부트";
	}
}
