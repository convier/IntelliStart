package com.inl.rst;

import org.modelmapper.ModelMapper;
import org.springdoc.core.GroupedOpenApi;
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

	@Bean
	public GroupedOpenApi publicApiV1() {
		return GroupedOpenApi.builder().group("api-1.0").pathsToMatch("/v1/orders/**", "/orders**").build();
	}

	@Bean
	public GroupedOpenApi publicApiV2() {
		return GroupedOpenApi.builder().group("api-2.0").pathsToMatch("/v2/orders/**", "/orders**").build();
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}