package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Contact;
import com.example.demo.entity.User;
import com.example.demo.helper.Message;

import jakarta.servlet.http.HttpSession;
@Controller
public class HomeController {
	@Autowired
	private UserRepository userRepository;
	
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
			model.addAttribute("user",new User());
			return "signup";
		}		
		@RequestMapping(value="/do_register",method=RequestMethod.POST)
		public String registerUser(@Valid @ModelAttribute("user")User user,BindingResult result1,@RequestParam(value="agreement",defaultValue="false")boolean agreement,Model model
				,HttpSession session)
{	
			try {
				if(!agreement) {
					System.out.println("you have not register");
					throw new Exception("you have not register");
				}
				
				if(result1.hasErrors())
				{
					System.out.println("ERROR "+result1.toString());
					model.addAttribute("user",user);
					return "signup";
				}
				
				user.setRole("ROLE_USER");
				user.setEnabled(true);
				
				System.out.println("Agreement "+agreement);
				System.out.println("User "+user);
				
				User result=this.userRepository.save(user);
				model.addAttribute("user",new User());
				session.setAttribute("message",new Message("Successfuly Registered","alert-success"));
				return "signup";
			}catch(Exception e) {
				e.printStackTrace();
				model.addAttribute("user",user);
				session.setAttribute("message",new Message("Something went Wrong"+e.getMessage(),"alert-danger"));
				return "signup";

			}
			
			
}
}
