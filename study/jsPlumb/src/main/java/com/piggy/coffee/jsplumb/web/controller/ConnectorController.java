package com.piggy.coffee.jsplumb.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("c")
public class ConnectorController {

	@RequestMapping(value = "/dragable", method = RequestMethod.GET)
	public String drag(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) {

		return "connector/dragable";
	}

	@RequestMapping(value = "/param", method = RequestMethod.GET)
	public String param(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) {

		return "connector/param";
	}

	@RequestMapping(value = "/dynamic", method = RequestMethod.GET)
	public String dynamic(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) {

		return "connector/dynamic";
	}

	@RequestMapping(value = "/resize", method = RequestMethod.GET)
	public String resize(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) {

		return "connector/resize";
	}

}
