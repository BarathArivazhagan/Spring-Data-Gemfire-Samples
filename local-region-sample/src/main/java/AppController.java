package com.barath.gemfire.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping("/")
	public String handleHome(){
		return "Welcome to Movie Application";
	}
	
	@RequestMapping(value="/addMovie",method=RequestMethod.POST)
	public String addMovie(@RequestBody Movie movie){
		if(movie !=null){
			movieService.addMovie(movie);
			return "Movie is added successfully";
		}
		return "Movie is not  added successfully. Check the logs for error ";
	}
	
	
	@RequestMapping("/getMovie")
	public Movie getMovie(@RequestParam("id") long movieId){
		
		return movieService.getMovie(movieId);
	}
	
	@RequestMapping("/getMovieByName")
	public Movie getMovie(@RequestParam("name") String movieName){
		return movieService.getMovie(movieName);
	}
	
	@RequestMapping("/updateMovie")
	public String updateMovie(@RequestBody Movie movie){
		if(movie !=null){
			movieService.updateMovie(movie);
		}
		return "Movie is updated successfully";
	}
	
	@RequestMapping("findAll")
	public List<Movie> findAllMovies(){		
		
		return movieService.findAllMovies();
	}
	@ExceptionHandler(Exception.class)
	public String handleeError(Exception ex){
		return ex.getMessage();
	}
	
	
	
	
	


}
