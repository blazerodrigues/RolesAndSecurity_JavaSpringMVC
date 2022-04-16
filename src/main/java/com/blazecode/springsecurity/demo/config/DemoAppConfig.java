package com.blazecode.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc //this is analogous to <mvc:annotation-driven> in xml.
@ComponentScan(basePackages="com.blazecode.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties") //this will read the properties file. // src/main/resources files are automatically copied to classpath during Maven build
public class DemoAppConfig {
	//this class is analogous to spring-mvc-demo-servlet.xml
	
	
	//set up an Environment variable to hold the properties that are read from the properties file
	//Environment is a special helper class from the spring framework
	@Autowired
	private Environment env;
	
	//set up a logger for diagnostics //can optionally use standart print statements
	private Logger logger = Logger.getLogger(getClass().getName());
	
	

	//define a bean for the VIEW RESOLVER
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	
	//define a bean for the security datasource //using pure java configuration, NO xml
	@Bean
	public DataSource securityDataSource() {
		
		//create connection pool //datasource
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		//set the JDBC driver class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		}catch(PropertyVetoException exc) {
			throw new RuntimeException(exc); //throw an unchecked exception for debugging purposes.
		}
		
		//log the connection properties for sanity sake
		//just to make sure that we are really reading data from the properties file
		logger.info(">> jdbc.url="+env.getProperty("jdbc.url"));
		logger.info(">> jdbc.user="+env.getProperty("jdbc.user"));
		
		//set database connection properties
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		//set connection pool properties
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}
	
	//Helper method to read environment property and convert it to from String to Int
	private int getIntProperty(String propName) {
		String propVal = env.getProperty(propName);
		//Convert String to Int
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}
	
	
}
