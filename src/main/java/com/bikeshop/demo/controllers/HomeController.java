package com.bikeshop.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@Autowired
	HttpSession session;

	@GetMapping("/")
	public String readForm(Model model) {

		return "home2";
	}

	@GetMapping("/about")
	public String aboutUs(Model model) {
		return "about";

	}
}
