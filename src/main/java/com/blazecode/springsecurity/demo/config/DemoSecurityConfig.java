package com.blazecode.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//add a reference to our security DATA SOURCE
	//field injection using @Autowired, it will look for the DataSource bean in DemoAppConfig.java
	@Autowired
	private DataSource securityDataSource;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//		//add our users for in-memory authentication
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		
//		auth.inMemoryAuthentication()
//			.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
//			.withUser(users.username("mary").password("test123").roles("EMPLOYEE","MANAGER"))
//			.withUser(users.username("susan").password("test123").roles("EMPLOYEE","ADMIN"));
		
	
		
		//use JDBC authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
		/* Password (stored in DB using bcrypt) test123 */
		
		
	}
	
	
	//configure security of web-paths in the application, Login, Logout, etc.
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests() //restricts access based on the HttpServletRequest
			.antMatchers("/").permitAll() // allow public access to the root landing page
			.antMatchers("/employees").hasRole("EMPLOYEE") //Employee is allowed access to root path "/"
			.antMatchers("/leaders/**").hasRole("MANAGER") //Manager is allowed access to /leaders and subdirectories 
			.antMatchers("/systems/**").hasRole("ADMIN") //Admin is allowed access to /systems and subdirectories
//				.anyRequest().authenticated() //any request to the app must be authenticated (ie. be logged in) //Commenting this line because now we want to restrict access based on roles using antmatchers
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage") //requestmapping in the controller to return our custom form jsp
				.loginProcessingUrl("/authenticateTheUser") //login form should submit(POST) data to this URL for processing (check username and password)
				.permitAll() //allow everyone to see the login page. No need to be logged in initially.
			.and()
			.logout().logoutSuccessUrl("/").permitAll() //add logout support. //after logout, return to the landing page(root)
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied"); //custom access denied page - controller mapping

	}
	

}
