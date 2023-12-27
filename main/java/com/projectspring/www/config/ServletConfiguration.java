package com.projectspring.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = {"com.projectspring.www.controller","com.projectspring.www.handler"})
public class ServletConfiguration  implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///D:\\_myweb\\_java\\fileupload\\");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		InternalResourceViewResolver viewRsolver = 
				new InternalResourceViewResolver();
		viewRsolver.setViewClass(JstlView.class);
		viewRsolver.setPrefix("/WEB-INF/views/");
		viewRsolver.setSuffix(".jsp");
		registry.viewResolver(viewRsolver);
	}
	
	@Bean(name = "multipartResolver")
	public MultipartResolver getMultipartResolver() {
		StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
		return multipartResolver;
	}

	
}
