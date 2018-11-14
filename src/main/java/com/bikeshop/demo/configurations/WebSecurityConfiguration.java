package com.bikeshop.demo.configurations;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import com.bikeshop.demo.service.KorisnikService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	 @Autowired 
	 private AuthenticationSuccessHandler authenticationSuccessHandler;
	 
	  @Autowired
	  KorisnikService korisnikService;
	  

	    
		@Value("${spring.queries.korisnik-query}")
		private String korisnikQuery;
		
		@Value("${spring.queries.permisije-query}")
		private String permisijeQuery;
		
	    @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity.authorizeRequests()
	                .antMatchers("/", "/css/**", "/js/**", "/images/**","/fonts/**","/vendors/**","/vendors").permitAll()
	                .antMatchers("/").permitAll()
					.antMatchers("/login").permitAll()
					.antMatchers("/home").permitAll()
					.antMatchers("/users/delete").permitAll()
					.antMatchers("/registration").permitAll()
//					.antMatchers("/bicikle").permitAll()
					
	                .antMatchers("/user/**").hasAnyRole("admin", "user")
	                .antMatchers("/admin/**").hasRole("admin")
	                
	                .anyRequest()
	                .authenticated().and().csrf().disable().formLogin()
					.loginPage("/login").failureUrl("/login?error=true")
					.successHandler(authenticationSuccessHandler)
					.and().logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/").and().exceptionHandling()
					.accessDeniedPage("/access-denied");
	    }

	    @Autowired
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	auth.userDetailsService(new UserDetailsService() {
	    		
	    		public UserDetails loadUserByUsername(String username) 
	    			throws UsernameNotFoundException {
	    			com.bikeshop.demo.entities.Korisnik activeUserInfo = korisnikService.findByUsername(username);
	    				GrantedAuthority authority = new SimpleGrantedAuthority("admin");
	    				UserDetails userDetails = (UserDetails)new User(activeUserInfo.getKorisnickoIme(),
	    						activeUserInfo.getSifra(), Arrays.asList(authority));
	    				
	    				return userDetails;
	    		}
	    	});
	    }
	    
	    @Bean
		public BCryptPasswordEncoder passwordEncoder() {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			return bCryptPasswordEncoder;
		}
		
}
