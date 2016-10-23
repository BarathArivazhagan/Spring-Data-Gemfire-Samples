package com.barath.bookstore.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.ReplicatedRegionFactoryBean;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;


@Configuration
public class GemfireConfiguration {
	
	
	private static final String REGION_NAME="BOOK";
	private static final String HOST="192.168.1.39";
	private static final int PORT=10334;
	
	
	@Bean
	public ClientCache clientCache(){
		
		ClientCache c = new ClientCacheFactory().addPoolLocator(HOST, PORT).create();
		
		 Region<Object, Object> region = c.createClientRegionFactory(ClientRegionShortcut.PROXY).create("BOOK");
		//System.out.println("Region ===> "+r);
		return c;
	}
	
//	@Bean
//	public ClientRegionFactoryBean<Long, Book> bookRegion(){
//		ClientRegionFactoryBean<Long, Book> bookRegion=new ClientRegionFactoryBean<Long, Book>();
//		bookRegion.setCache(clientCache());
//		bookRegion.setShortcut(ClientRegionShortcut.PROXY);
//		return bookRegion;
//	}
	
//	@Bean
//	public ReplicatedRegionFactoryBean<Long, Book> bookRegion(){
//		ReplicatedRegionFactoryBean<Long, Book> bookRegion=new ReplicatedRegionFactoryBean<Long, Book>();
//		return bookRegion;
//	}

}
