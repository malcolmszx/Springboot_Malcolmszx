package com.kingdee.abc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class EmailProperties {
	
	private String host;
	
	private String username;

	private String password;
	
	private String protocol;
	
	private String from;
	
	
}
