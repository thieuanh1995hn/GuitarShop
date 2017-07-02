package com.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class GuitarshopApplication extends SpringBootServletInitializer {
	

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return  builder.sources(GuitarshopApplication.class);
	}

	@Override
	protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		return super.createRootApplicationContext(servletContext);
	}

	@Override
	protected SpringApplicationBuilder createSpringApplicationBuilder() {
		// TODO Auto-generated method stub
		return super.createSpringApplicationBuilder();
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		super.onStartup(servletContext);
	}

	@Override
	protected WebApplicationContext run(SpringApplication application) {
		// TODO Auto-generated method stub
		return super.run(application);
	}

	public static void main(String[] args) {
		SpringApplication.run(GuitarshopApplication.class, args);	
	}
}
