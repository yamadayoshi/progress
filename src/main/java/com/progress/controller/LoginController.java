package com.progress.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class LoginController {

	@GetMapping("/home")
	public String getLogin() {
		return "login";
	}	
	
	@GetMapping("/") 
	public String postLogin(){
		return "redirect:/client/";
	}
}
