package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Contact;
import com.example.demo.entity.User;
@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title","home-Smart Contact Manager");
		return "home";
}
	@RequestMapping("/about")
	public String about(Model model)
	{
		model.addAttribute("title","About-Smart Contact Manager");
		return "about";
	}
		
		@RequestMapping("/signup")
		public String signup(Model model)
		{
			model.addAttribute("title","Register-Smart Contact Manager");
			return "signup";
}
}
