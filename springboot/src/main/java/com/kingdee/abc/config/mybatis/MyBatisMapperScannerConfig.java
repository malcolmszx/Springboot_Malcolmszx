package com.kingdee.abc.config.mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AutoConfigureAfter(MybatisConfiguration.class)
public class MyBatisMapperScannerConfig {

	@Bean
    public MapperScannerConfigurer createMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.kingdee.abc.dao");
        return mapperScannerConfigurer;
    }

}
