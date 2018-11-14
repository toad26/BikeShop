package com.bikeshop.demo.configurations;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	private static final SimpleGrantedAuthority ADMIN_AUTHORITY = new SimpleGrantedAuthority("admin");
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		if (authorities.contains(ADMIN_AUTHORITY)) {
			redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/admin");
		} else {
			redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/admin");
		}
	}
}
