package com.cc.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import freemarker.template.TemplateModelException;

@Configuration
@EnableConfigurationProperties(ConnConfig.class)
public class FreeMarkerConfig {

	@Autowired
	private freemarker.template.Configuration configuration;
	@Autowired
	private ConnConfig connConfig;

	@PostConstruct
	public int init() throws TemplateModelException {
		this.configuration.setSharedVariable("__rootpath__", this.connConfig.getRootPath());
		return 0;
	}

}
