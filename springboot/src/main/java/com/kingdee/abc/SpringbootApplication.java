package com.kingdee.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.kingdee.abc.annotation.EnableEncrypt;
import com.kingdee.abc.annotation.EnableRedissonLock;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;

@EnableRedissonLock
@EnableScheduling
@SpringBootApplication
@EnableEncrypt
public class SpringbootApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				SpringApplication.run(SpringbootApplication.class, args);
	}
	
	/**
     * @author malcolmszx
     * @date 2017-3-17
     * @describe 优化tomcat线程数目
     */
    class FangddTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
        @Override
        public void customize(Connector connector) {
            Http11NioProtocol protocol = (Http11NioProtocol) connector
                    .getProtocolHandler();
            // 设置最大连接数
            protocol.setMaxConnections(2000);
            // 设置最大线程数
            protocol.setMaxThreads(2000);
            protocol.setConnectionTimeout(30000);
        }
    }
}
