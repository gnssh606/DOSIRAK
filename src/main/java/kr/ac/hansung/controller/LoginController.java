package kr.ac.hansung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	// Login 시
	@RequestMapping("/login")
	public String showLogin(@RequestParam(value = "error", required = false) String error, Model model) {
		
		if (error != null)
			model.addAttribute("errorMsg", "아이디 혹은 비밀번호가 올바르지 않습니다.");
		
		return "login";
		
	}

}
