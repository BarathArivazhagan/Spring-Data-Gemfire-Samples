package com.barath.gemfire.app;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import com.gemstone.gemfire.cache.GemFireCache;
import com.gemstone.gemfire.management.GemFireProperties;

@Configuration
@EnableGemfireRepositories
public class GemfireConfiguration {
	
		@Bean
	    Properties gemfireProperties() {
	        Properties gemfireProperties = new Properties();
	        gemfireProperties.setProperty("name", "SpringDataGemfireLocalSample");
	        gemfireProperties.setProperty("mcast-port", "0");
	        gemfireProperties.setProperty("log-level", "config");
	       
	        return gemfireProperties;
	    }
	

//	@Bean
//    public GemFireProperties gemfireProperties() {
//        Properties gemfireProperties = new GemFireProperties();
//        gemfireProperties.setProperty("name", "DataGemFireApplication");
//        gemfireProperties.setProperty("mcast-port", "0");
//        gemfireProperties.setProperty("log-level", "config");
//        return gemfireProperties;
//    }
//	
	

	    @Bean
	    public  CacheFactoryBean gemfireCache() {
	        CacheFactoryBean gemfireCache = new CacheFactoryBean();
	        gemfireCache.setClose(true);
	        gemfireCache.setProperties(gemfireProperties());
	        return gemfireCache;
	    }

	    @Bean
	    public LocalRegionFactoryBean<String, Movie> movieRegion(final GemFireCache cache) {
	        LocalRegionFactoryBean<String, Movie> movieRegion = new LocalRegionFactoryBean<>();
	        movieRegion.setCache(cache);
	        movieRegion.setClose(false);
	        movieRegion.setName("movie");
	        movieRegion.setPersistent(false);
	        return movieRegion;
	    }

}
