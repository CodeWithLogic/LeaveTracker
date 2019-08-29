package com.login.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.authservice.dao.AuthService;
import com.login.model.User;

@Controller
public class LoginController {
	private static Logger log = Logger.getLogger(LoginController.class);
	@RequestMapping("/")
	public String init(Model model) {
		
		User user = new User();
		model.addAttribute("theUser", user);
		return "login";	
	}
	
	@RequestMapping("/validate")
    public ModelAndView validateUsr(@RequestParam("username")String username, @RequestParam("password")String password) {
        String msg = "";
        AuthService authService = new AuthService();
        boolean isValid = authService.findUser(username, password);
        log.info("Is user valid?= " + isValid);
 
        if(isValid=true) {
            msg = "Welcome " + username + "!";
        } else {
            msg = "Invalid credentials";
        }
 
        return new ModelAndView("dashboard", "output", msg);
    }
	

}
