package com.barath.bookstore.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.ReplicatedRegionFactoryBean;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.beans.factory.annotation.Value;



@Configuration
public class GemfireConfiguration {
	
	
	private static final String REGION_NAME="BOOKS";
	
	
	
	@Value("${gemfire.locator.host:localhost}")
	private String gemfireHost;
	
	@Value("${gemfire.locator.port:10334}")
	private int gemfirePort;
	
	
	@Bean
	public ClientCache clientCache(){
		
		ClientCache clientCache = new ClientCacheFactory().addPoolLocator(gemfireHost, gemfirePort).create();
		
		 Region<Object, Object> region = clientCache.createClientRegionFactory(ClientRegionShortcut.PROXY).create(REGION_NAME);
		
		return clientCache;
	}
	


}
