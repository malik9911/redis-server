/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* Copyright (c) 2019  Services, Inc. All Rights Reserved.* * * * * * * * * * * * * *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*/
package redis.demo.com.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

/**
 * This class is used for
 * 
 * @author Vakeel.Ahamad
 *
 */
@Service
public class MessageSubscriber implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageSubscriber.class);
	public static List<String> messageList = new ArrayList<>();

	@Override
	public void onMessage(final Message message, byte[] pattern) {
		messageList.add(message.toString());
		LOGGER.info("message received {}", new String(message.getBody()));
	}
}
