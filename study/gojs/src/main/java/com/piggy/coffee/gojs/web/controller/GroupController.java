package com.piggy.coffee.gojs.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("group")
public class GroupController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String group(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) {

		return "group";
	}

}
