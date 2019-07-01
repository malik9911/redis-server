/*
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * * * * * * Copyright (c) 2019  Services, Inc. All Rights Reserved.* * * *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * *
 */
package redis.demo.com.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import redis.demo.com.service.MessagePublisher;
import redis.demo.com.service.MessagePublisherImpl;
import redis.demo.com.service.MessageSubscriber;

/**
 * This class is used for
 * 
 * @author Vakeel.Ahamad
 *
 */
@Configuration
@EnableAutoConfiguration
public class RedisConfig extends CachingConfigurerSupport {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName("127.0.0.1");
		jedisConnectionFactory.setPort(6379);
		return jedisConnectionFactory;
	}

	/**
	 * 
	 * This method is used for
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setExposeConnection(true);
		return redisTemplate;
	}

//   @Bean
//   public RedisCacheManager cacheManager() { 
//	   RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
//       redisCacheManager.setTransactionAware(true);
//       redisCacheManager.setLoadRemoteCachesOnStartup(false);
//       redisCacheManager.setUsePrefix(true);
//       return redisCacheManager;
//   }

	/**
	 * 
	 * This method is used for
	 * @return
	 */
	@Bean
	public MessagePublisher messagePublisher() {
		return new MessagePublisherImpl(redisTemplate(), topic());
	}

	/**
	 * This method is used for
	 * 
	 * @return
	 */
	
	@Bean
	public ChannelTopic topic() {
		// TODO Auto-generated method stub
		ChannelTopic channelTopic =new ChannelTopic("Movie");
		return channelTopic;
	}
	
	/**
	 * 
	 * This method is used for
	 * @return
	 */
	
	@Bean 
	MessageListenerAdapter messageLinstener() {
		return new MessageListenerAdapter(new MessageSubscriber());
	}

}
