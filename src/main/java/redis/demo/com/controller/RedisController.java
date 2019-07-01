/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* Copyright (c) 2019 , Inc. All Rights Reserved.* * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*/
package redis.demo.com.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.demo.com.dto.CreateMovieDto;
import redis.demo.com.model.Movie;
import redis.demo.com.repository.MovieRepository;

/**
 *This class is used for 
 * @author Vakeel.Ahamad
 *
 */
@RestController
@RequestMapping ("/movie")
public class RedisController {

	
	private static final Logger LOGGER=LoggerFactory.getLogger(RedisController.class);
	
	@Autowired
	MovieRepository movieRepository;
	
	@PostMapping(value = "/add")
	public ResponseEntity<Object> addMovie(@RequestBody CreateMovieDto createMovieDto){
	  Movie movie=new Movie(createMovieDto.getId(), createMovieDto.getName());
	  
	  LOGGER.info("data saving from  controller");  
	  movieRepository.save(movie);
	  return new ResponseEntity<>("Movie saved successfully",HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<Object> getMovie(@PathVariable(value = "id") String id){
		LOGGER.info("movie id {}",id);  
	  String movie=movieRepository.findMovie(id);
	  Map<String,Object> body=new HashMap<>();
	  body.put("message","movie fetch successfully");
	  body.put("movie", movie);
	  return new ResponseEntity<>(body,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<Object> getAllMovie(){
	  Map<String,Object> movies=movieRepository.findAll();
	  Map<String,Object> body=new HashMap<>();
	  body.put("message"," All movie fetch successfully");
	  body.put("movie", movies);
	  return new ResponseEntity<>(body,HttpStatus.OK);
	}
	
	
}
