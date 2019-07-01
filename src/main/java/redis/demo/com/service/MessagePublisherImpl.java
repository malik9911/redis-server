/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* Copyright (c) 2019 , Inc. All Rights Reserved.* * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*/
package redis.demo.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

/**
 * This class is used for send message.
 * 
 * @author Vakeel.Ahamad
 *
 */
@Service
public class MessagePublisherImpl implements MessagePublisher {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private ChannelTopic channelTopic;

	//@Autowired
	public MessagePublisherImpl(RedisTemplate<String, Object> redisTemplate, ChannelTopic channelTopic) {
		super();
		this.channelTopic = channelTopic;
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void publish(String message) {
		redisTemplate.convertAndSend(channelTopic.getTopic(), message);
	}

}
