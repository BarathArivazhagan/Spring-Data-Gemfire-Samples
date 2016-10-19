package com.barath.gemfire.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MovieService {
	
	private MovieRepository movieRep; 
	
	
	@Autowired
	public MovieService(MovieRepository movieRep){
		this.movieRep=movieRep;
	}
	
	public void addMovie(Movie movie){
		movieRep.save(movie);
	}
	
	public Movie getMovie(long movieId){
		Movie movie=null;
		if(movieRep.exists(movieId)){
			movie=movieRep.findOne(movieId);
		}
		
		return movie;
	}

	public void updateMovie(Movie movie){
		if(isMovieExists(movie)){
			Movie movieFound=getMovie(movie.getMovieId());
			movieFound.setMovieId(movie.getMovieId());
			movieFound.setMovieName(movie.getMovieName());
			movieRep.save(movieFound);
		}
	}
	public void deleteMovie(long movieId){
		if(isMovieExists(movieId)){
			movieRep.delete(movieId) ;
		}
	}
	public void deleteMovie(Movie movie){
		if(isMovieExists(movie)){
			movieRep.delete(movie) ;
		}
	}
	
	public boolean isMovieExists(long movieId){
		return movieRep.exists(movieId);
	}
	
	public boolean isMovieExists(Movie movie){
		if(movie != null){
			return movieRep.exists(movie.getMovieId());
		}
		return false;
	}

	public Movie getMovie(String movieName) {
		
		return movieRep.findByMovieName(movieName);
	}

	
	

}
