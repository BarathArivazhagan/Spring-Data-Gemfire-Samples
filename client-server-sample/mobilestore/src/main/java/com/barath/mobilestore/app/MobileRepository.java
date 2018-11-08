package com.barath.mobilestore.app;

import org.springframework.data.gemfire.repository.GemfireRepository;

public interface MobileRepository extends GemfireRepository<Mobile, String> {

	Mobile findByMobileName(String mobileName);
	
}
