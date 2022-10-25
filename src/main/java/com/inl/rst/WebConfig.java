package com.inl.rst;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
public class WebConfig {

	@Bean
	public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilter() {
		FilterRegistrationBean<ShallowEtagHeaderFilter> filterRegistrationBean = new FilterRegistrationBean<ShallowEtagHeaderFilter>(
				new ShallowEtagHeaderFilter());
		filterRegistrationBean.addUrlPatterns("/orders/*");
		filterRegistrationBean.setName("eTagFilter");
		return filterRegistrationBean;
	}
}