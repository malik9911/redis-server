package redis.demo.com;

import java.util.regex.Pattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.regex.Matcher;


@SpringBootApplication
public class RedisConfigAppDemoApplication {

	
	//https://dzone.com/articles/intro-to-redis-with-spring-boot
	public static void main(String[] args) {
		SpringApplication.run(RedisConfigAppDemoApplication.class, args);
		
		
//		String regex="^[6-9+10]+$";
//		
//		Pattern pattern=Pattern.compile(regex);
//		Matcher matcher=pattern.matcher("1066666");
//		System.out.println(matcher.matches());
	}

	
	
	
}
