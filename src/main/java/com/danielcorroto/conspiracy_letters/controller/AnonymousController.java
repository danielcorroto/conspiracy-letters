package com.danielcorroto.conspiracy_letters.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AnonymousController {
	@RequestMapping(value = { "/", "/login" })
	public String index() {
		return "/index";
	}
}
