package com.projectspring.www.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@Component
public class LoginFailureHanlder implements AuthenticationFailureHandler{

	private String authEmail;
	private String errorMassage;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		setAuthEmail(request.getParameter("email"));
		
		if (exception instanceof BadCredentialsException || 
				exception instanceof InternalAuthenticationServiceException) {
			setErrorMassage(exception.getMessage().toString());
		}
		log.info(">> errMsg>> "+ errorMassage);
		request.setAttribute("email", getAuthEmail());
		request.setAttribute("errMsg", getErrorMassage());
		request.getRequestDispatcher("/member/login?error")
		.forward(request, response);
	}
	
	
}
