package com.piggy.coffee.gojs.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("shape")
public class ShapeController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String shape(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) {

		return "shape";
	}

}
