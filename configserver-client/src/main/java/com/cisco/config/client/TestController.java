package com.cisco.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*@author Lee Email:xinyul@cisco.com
*@date   2017-11-27
*@desc   
*
*/
@RestController
@RefreshScope
public class TestController {
	
	@Value("${wordConfig.myWord}")
	private String myWord;

	@RequestMapping("/myWord")
	public String showRemoteConfig() {
		return "Hello,"+myWord;
	}
}
