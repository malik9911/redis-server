/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* Copyright (c) 2019 , Inc. All Rights Reserved.* * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*/
package redis.demo.com.repository;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import redis.demo.com.controller.RedisController;
import redis.demo.com.model.Movie;

/**
 * This class is used for
 * 
 * @author Vakeel.Ahamad
 *
 */
@Repository
public class MovieRepositoryImpl implements MovieRepository {

	private static final Logger LOGGER=LoggerFactory.getLogger(RedisController.class);

	private static final String KEY = "Movie";
		
	RedisTemplate<String,Object> redisTemplate;

	private HashOperations hashOperation;

	
	@Autowired
	public MovieRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init(){
		hashOperation = redisTemplate.opsForHash();
	}

	@Override
	public void delete(String id) {
		hashOperation.delete(KEY, id);
	}

	@Override
	public Map<String, Object> findAll() {
		return hashOperation.entries(KEY);
	}

	@Override
	public String findMovie(String id) {
		LOGGER.info("id {}  {} ",id, hashOperation.get(KEY, id));
		return (String) hashOperation.get(KEY, id);
	}

	//@Override
	public void save(Movie movie) {
		hashOperation.put(KEY, movie.getId(), movie.getName());
	}

}
