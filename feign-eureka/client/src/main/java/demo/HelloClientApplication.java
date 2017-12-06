package demo;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

/**
 * @author Spencer Gibb
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
@EnableZuulProxy
public class HelloClientApplication {
	@Autowired
	HelloClient client;

	@RequestMapping("/")
	public String hello() {
		return client.hello();
	}
	
	@RequestMapping("/name1")
	public String helloName(){
		return client.helloName();
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloClientApplication.class, args);
	}

	@FeignClient("HelloServer")
	interface HelloClient {
		@RequestMapping(value = "/", method = GET)
		String hello();
		
		@RequestMapping(value="/name",method=GET)
		String helloName();
	}
	
	@Bean
    public Filter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }    
}
