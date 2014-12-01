package com.w.profile.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.w.profile.controller.form.LoginFormBean;

@Controller
public class LoginController {

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("loginForm") LoginFormBean loginFormBean) {

		ModelAndView model = new ModelAndView("login.htm");		
		model.addObject("loginForm", loginFormBean);
		return model;

	}
}
