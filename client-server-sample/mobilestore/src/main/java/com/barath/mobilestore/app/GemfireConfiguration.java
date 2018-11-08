package com.barath.mobilestore.app;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class GemfireConfiguration {
	
	@Value("${gemfire.region.name:MOBILES}")
	private String regionName;
	
	
	
	@Value("${gemfire.locator.host:localhost}")
	private String gemfireHost;
	
	@Value("${gemfire.locator.port:10334}")
	private int gemfirePort;
	
	
	@Bean
	public ClientCache clientCache(){
		
		ClientCache clientCache = new ClientCacheFactory().addPoolLocator(gemfireHost, gemfirePort).create();
		
		 Region<Object, Object> region = clientCache.createClientRegionFactory(ClientRegionShortcut.PROXY).create(regionName);
		
		return clientCache;
	}
	


}
