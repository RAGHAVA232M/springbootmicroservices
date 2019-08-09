package com.photo.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.photo.users.service.UsersService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	private Environment environment;
	private UsersService usersService;
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	@Autowired
	public WebSecurity(Environment environment, UsersService usersService,
			BCryptPasswordEncoder bcryptPasswordEncoder) {
		super();
		this.environment = environment;
		this.usersService = usersService;
		this.bcryptPasswordEncoder = bcryptPasswordEncoder;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/users/**").permitAll()
		.antMatchers("/test/**").permitAll()
		.antMatchers(environment.getProperty("api.h2console.url.path")).permitAll()
		.and()
		.addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();
		
	}
	


	private AuthenticationFilter getAuthenticationFilter() throws Exception
	{
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(usersService, environment, authenticationManager());
		//authenticationFilter.setAuthenticationManager(authenticationManager()); 
		authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
		return authenticationFilter;
	}

	@Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(usersService).passwordEncoder(bcryptPasswordEncoder);
	    }
	 
	 

}
