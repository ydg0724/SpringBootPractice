package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor

public class HelloLombok {
	private String hello;
	private int lombok;
	
	public static void main(String[] args) {
		HelloLombok hello = new HelloLombok();
		hello.setHello("헬포");
		hello.setLombok(5);
		
		System.out.println(hello.getHello());
		System.out.println("양동균");
	}
}
