/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* Copyright (c) 2019 , Inc. All Rights Reserved.* * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*/
package redis.demo.com.repository;

import java.util.Map;

import redis.demo.com.model.Movie;

/**
 * This class is used for
 * 
 * @author Vakeel.Ahamad
 *
 */
public interface MovieRepository {

	Map<String, Object> findAll();

	void save(Movie movie);

	void delete(String id);

	String findMovie(String id);
}
