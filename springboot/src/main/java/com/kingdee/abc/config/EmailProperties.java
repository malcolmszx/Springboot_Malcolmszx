package com.kingdee.abc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class EmailProperties {
	
	private String host; // #发送邮件服务器
	
	private String username; // #QQ邮箱

	private String password; // #客户端授权码
	
	private String protocol; // #发送邮件协议
	
	private String from; // # 邮件发送方、与上面的username保持一致
	
	
}
