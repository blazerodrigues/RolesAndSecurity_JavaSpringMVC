package com.blazecode.springsecurity.demo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	//this class is analogous to web.xml

	//NO root config classes in our example
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[] { DemoAppConfig.class }; //DemoAppConfig is the config class that we created previously
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] { "/" };
	}

}
