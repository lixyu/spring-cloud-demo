package demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Spencer Gibb
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class HelloServerApplication {
	@Autowired
	DiscoveryClient client;
	
	@Value("${myword}")
	String words;

	@RequestMapping("/")
	public String hello() {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		return "Hello World: "+ localInstance.getServiceId()+":"+localInstance.getHost()+":"+localInstance.getPort();
	}

	@RequestMapping("/name")
	public String helloName() {
		String[] wordArray = StringUtils.split(words, ",");
		int i = (int) Math.round(Math.random() * (wordArray.length - 1));
		return "Hello,"+wordArray[i]+" "+i;
		
	}
	public static void main(String[] args) {
		SpringApplication.run(HelloServerApplication.class, args);
	}
}
