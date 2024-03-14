package com.sbs.springsecurityexample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controller1 {

	
	
	@GetMapping("/person")
	public String m1() {
		
	return "success";	
	}
	
}
