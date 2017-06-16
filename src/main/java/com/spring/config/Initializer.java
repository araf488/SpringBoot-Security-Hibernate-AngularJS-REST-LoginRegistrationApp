package com.spring.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Initializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		//Register one or more annotated classes to be processed.
		ctx.register(WebAppConfig.class);
		//Set the ServletContext for this web application context.
		ctx.setServletContext(servletContext);
		//Adds the servlet with the given name and class type to this servlet context.
		Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		//Adds a servlet mapping with the given URL patterns for the Servlet represented by this ServletRegistration.
		dynamic.addMapping("/");
		//Sets the loadOnStartup priority on the Servlet represented by this dynamic ServletRegistration.
		dynamic.setLoadOnStartup(1);

	}

}