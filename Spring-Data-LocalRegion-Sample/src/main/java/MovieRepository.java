package com.barath.gemfire.app;

import org.springframework.data.gemfire.mapping.Region;
import org.springframework.data.gemfire.repository.GemfireRepository;


public interface MovieRepository extends GemfireRepository<Movie, Long>{
	
	
	public Movie findByMovieName(String movieName);
	
}
