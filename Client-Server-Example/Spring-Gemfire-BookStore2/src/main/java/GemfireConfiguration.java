package com.barath.bookstore.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.ReplicatedRegionFactoryBean;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;


@Configuration
public class GemfireConfiguration {
	
	
	private static final String REGION_NAME="BOOK";
	
	
	
	@Value("${gemfire.locator.host:localhost}")
	private String gemfireHost;
	
	@Value("${gemfire.locator.port:10334}")
	private int gemfirePort;
	
	
	@Bean
	public ClientCache clientCache(){
		
		ClientCache clientCache = new ClientCacheFactory().addPoolLocator(gemfireHost, gemfirePort).create();
		
		 Region<Object, Object> region = clientCache.createClientRegionFactory(ClientRegionShortcut.PROXY).create("BOOK");
		
		return clientCache;
	}
	


}
