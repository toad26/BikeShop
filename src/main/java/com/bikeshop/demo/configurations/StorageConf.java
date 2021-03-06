package com.bikeshop.demo.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("storage")
@PropertySource(value = { "classpath:application.properties" })
public class StorageConf {

	/**
	 * Folder location for storing files
	 */
	@Value("${file.upload-dir}")
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}