package com.barath.mobilestore.app;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.geode.cache.query.FunctionDomainException;
import org.apache.geode.cache.query.NameResolutionException;
import org.apache.geode.cache.query.QueryInvocationTargetException;
import org.apache.geode.cache.query.TypeMismatchException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobiles")
public class MobileController {
	
	private final MobileService mobileService;
	
	public MobileController(MobileService mobileService) {
		super();
		this.mobileService = mobileService;
	}

	@GetMapping
	public List<String> findAllMobiles(@RequestBody List<String> keys){
		System.out.println(keys);
		return mobileService.getAllMobiles(keys).values().stream().collect(Collectors.toList());
	}
	
	@GetMapping("/names")
	public Object findAllMobileNames() throws FunctionDomainException, TypeMismatchException, NameResolutionException, QueryInvocationTargetException{
		
		return mobileService.getAllMobileNames();
	}
	
	@PostMapping("/new")
	public String addMobile(@RequestBody Mobile mobile){
		
		mobileService.addMobile(mobile);
		return "Mobile is added successfully ";
	}
	
	@GetMapping("/byId/{mobileId}")
	public Mobile addMobile(@PathVariable("mobileId") String mobileId){
		
		
		return mobileService.getMobile(mobileId);
		
	}
}
