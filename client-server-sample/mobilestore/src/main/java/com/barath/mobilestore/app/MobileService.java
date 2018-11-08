package com.barath.mobilestore.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.query.FunctionDomainException;
import org.apache.geode.cache.query.NameResolutionException;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryInvocationTargetException;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.SelectResults;
import org.apache.geode.cache.query.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class MobileService {
	
	private Region<String, String> mobileRegion; 
	
	private ClientCache clientCache;
	
	private final MobileRepository mobileRepository;
	
	
	@Value("${gemfire.region.name:MOBILES}")
	private String regionName;
	
	private static final String SELECT_QUERYSTRING="select * from /MOBILES";
	
	public MobileService(ClientCache clientCache, MobileRepository mobileRepository){
		this.mobileRegion=clientCache.getRegion(regionName);
		this.clientCache=clientCache;
		this.mobileRepository = mobileRepository;
	}
	
	public Mobile addMobile(Mobile mobile){
		
		return this.mobileRepository.save(mobile);
	}
	
	public Mobile getMobile(String mobileId){
		Mobile mobile=null;
		//if(isMobileExists(mobileId)){
			String mobileName=mobileRegion.get(mobileId);
			if(!StringUtils.isEmpty(mobileName)){
				mobile=new Mobile(mobileId, mobileName);
			}
			System.out.println(mobile.toString());
		//}
		
		return mobile;
	}

	public void updateMobile(Mobile mobile){
		//if(isMobileExists(mobile)){
			Mobile mobileFound=getMobile(mobile.getMobileId());
			mobileFound.setMobileId(mobile.getMobileId());
			mobileFound.setMobileName(mobile.getMobileName());
			mobileRegion.put(mobileFound.getMobileId(),mobileFound.getMobileName());
		//}
	}
	public void deleteMobile(String mobileId){
		if(isMobileExists(mobileId)){
			mobileRegion.remove(mobileId) ;
		}
	}
	public void deleteMobile(Mobile mobile){
		if(isMobileExists(mobile)){
			mobileRegion.remove(mobile) ;
		}
	}
	
	public boolean isMobileExists(String mobileId){
		return mobileRegion.containsKey(mobileId);
	}
	
	public boolean isMobileExists(Mobile mobile){
		if(mobile != null){
			return mobileRegion.containsKey(mobile.getMobileId());
		}
		return false;
	}

//	public Mobile getMobile(String mobileName) {
//		
//		if(!StringUtils.isEmpty(mobileName)){
//			return this.findByMobileName(mobileName);
//		}
//		return null;
//	}
//	
//	private Mobile findByMobileName(String mobileName) {
//		if(isMobileExists(mobile))
//	}

	public Map<String, String> getAllMobiles(Collection<String> keys) {
			
		
		System.out.println(keys);
		Map<String, String> mobiles=mobileRegion.getAll(keys);
		System.out.println(mobiles);
	
		 mobiles.entrySet().stream().forEach(System.out::println);
		 return mobiles;
	}
	
	public List<String> getAllMobileNames() throws FunctionDomainException, TypeMismatchException, NameResolutionException, QueryInvocationTargetException {
			
			
		QueryService queryService = this.clientCache.getQueryService();	
		Query query = queryService.newQuery(SELECT_QUERYSTRING);			
		SelectResults<Object> results = (SelectResults<Object>)query.execute();
		System.out.println("Results "+results);
		System.out.println("SIZE "+results.size());	
		List<String> mobileNames=new ArrayList<String>(results.size());
		results.stream().forEach(System.out::println);
		 return mobileNames;
	}

}
