package com.maersk.containerbooking.server;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "server")
public class ServerProperties {

	private String availabilityUrl;

	public String getAvailabilityUrl() {
		return availabilityUrl;
	}

	public void setAvailabilityUrl(String availabilityUrl) {
		this.availabilityUrl = availabilityUrl;
	}

}
