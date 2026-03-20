package com.piggy.coffee.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.piggy.coffee.common.util.context.ContextPathUtils;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ContextPathListener implements ServletContextListener {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void contextInitialized(ServletContextEvent event) {

		String contextPath = event.getServletContext().getContextPath();
		ContextPathUtils.setContextPath(contextPath);

		if (log.isDebugEnabled()) {
			log.debug("set context path {}", contextPath);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

}
