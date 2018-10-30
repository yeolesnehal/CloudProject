package edu.sjsu.cloud.springbootstarter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@Controller
public class UserController {
	
	
@Autowired
UserService userService;

@RequestMapping(value="/signuppage", method = RequestMethod.POST)
@ModelAttribute
public String addUser(@RequestPart(value="firstName", required=true) String firstName, @RequestPart(value="lastName", required=true) String lastName,
		@RequestPart(value="passwd", required=true) String passwd, @RequestPart(value="emailId", required=true) String emailId, HttpSession session, Model model, HttpServletRequest request) {
		/*@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("passwd") String passwd, 
		@RequestParam("emailId") String emailId, HttpSession session) {*/
	
		User u = new User();
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setPasswd(passwd);
		u.setEmailId(emailId);
		
		
		userService.addUser(u);
		session.setAttribute("loggeduser", u);
		return "redirect:/index.html";
		
	}
	

/*@RequestMapping(value="/signuppage" , method=RequestMethod.POST)
public String showSignUpPage() {
	return "redirect:/index.html";
	
}
*/
}