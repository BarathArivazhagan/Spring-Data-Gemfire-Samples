package com.citi.gct.eclipse.framework.gemfire.session;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;




@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Documented
@Import(WOAGemfireSessionConfiguration.class)
@Configuration
public @interface EnableWOAGemfireHttpSession {
	
	int maxInactiveIntervalInSeconds() default 1800;

}
