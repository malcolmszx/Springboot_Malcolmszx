package com.kingdee.abc.config;


import org.springframework.context.annotation.Configuration;
import com.kingdee.abc.filter.SignAuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


/**
 * 自定义配置项类，该类中和存入拦截器、过滤器等配置项信息
 * @author malcolmszx
 */
@Configuration
public class CustemConfigurerAdapter {


	private static final String[] URLS = {"/user/*"};

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
    public FilterRegistrationBean filterRegistrationBean(){

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new SignAuthFilter());
        filterRegistrationBean.addUrlPatterns(URLS);
        filterRegistrationBean.setName("SignAuthFilte");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }


}
 
