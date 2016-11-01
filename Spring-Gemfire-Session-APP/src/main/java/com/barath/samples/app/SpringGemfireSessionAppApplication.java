package com.barath.samples.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.citi.gct.eclipse.framework.gemfire.session.EnableWOAGemfireHttpSession;

@SpringBootApplication
@EnableWOAGemfireHttpSession
public class SpringGemfireSessionAppApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringGemfireSessionAppApplication.class, args);
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver(){
		return new InternalResourceViewResolver();
	}
}
