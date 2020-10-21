package com.maersk.containerbooking.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maersk.containerbooking.controller.model.ContainerBookingAvailability;
import com.maersk.containerbooking.controller.model.ContainerBookingAvailabilityParams;

@Service
public class ContainerBookingServiceImpl implements ContainerBookingService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private com.maersk.containerbooking.server.ServerProperties serverProperties;

	@Override
	public List<ContainerBookingAvailability> getContainerAvailability(ContainerBookingAvailabilityParams params) {
		List<ContainerBookingAvailability> containerBookingAvailabilityList = Collections.emptyList();
		String requestJson = null;
		try {
			requestJson = objectMapper.writeValueAsString(params);
		} catch (JsonProcessingException e) {
			return containerBookingAvailabilityList;
		}

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, requestHeaders);

		String availabilityUrl = serverProperties.getAvailabilityUrl();

		try {
			ResponseEntity<Object> responseEntity = restTemplate.exchange(availabilityUrl, HttpMethod.POST,
					requestEntity, Object.class);

			// Logic to check availability
			// availableSpace -> expected output from
			// https://maersk.com/api/bookings/checkAvailable

			if (responseEntity.getBody().availableSpace > 0) {
				ContainerBookingAvailability isAvaialable = new ContainerBookingAvailability();
				isAvaialable.setAvailable(true);
				containerBookingAvailabilityList.add(isAvaialable);
			} else {
				ContainerBookingAvailability isAvaialable = new ContainerBookingAvailability();
				isAvaialable.setAvailable(true);
				containerBookingAvailabilityList.add(isAvaialable);
			}

		} catch (Exception e) {
			return containerBookingAvailabilityList;
		}
		return containerBookingAvailabilityList;
	}

}
