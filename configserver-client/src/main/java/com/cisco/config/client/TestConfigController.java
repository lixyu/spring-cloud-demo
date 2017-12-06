package com.cisco.config.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*@author Lee Email:xinyul@cisco.com
*@date   2017-11-27
*@desc   
*
*/
@RestController
@ConfigurationProperties(prefix="wordConfig")
public class TestConfigController {
	
	private String myWord;

	@RequestMapping("/configWord")
	public String showRemoteConfig() {
		return "Hi,"+myWord;
	}

	public String getMyWord() {
		return myWord;
	}

	public void setMyWord(String myWord) {
		this.myWord = myWord;
	}
	
}
