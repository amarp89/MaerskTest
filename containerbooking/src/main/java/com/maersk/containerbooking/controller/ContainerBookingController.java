package com.maersk.containerbooking.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maersk.containerbooking.controller.model.ContainerBookingAvailability;
import com.maersk.containerbooking.controller.model.ContainerBookingAvailabilityParams;
import com.maersk.containerbooking.server.Constants;
import com.maersk.containerbooking.service.ContainerBookingService;

@RestController
public class ContainerBookingController {

	@Autowired
	private ContainerBookingService containerBookingService;

	@PostMapping(value = "/getAvailability", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getAllocations(HttpServletRequest request,
			@RequestBody ContainerBookingAvailabilityParams requestAvailabilityParams) {
		try {
			// Validation of RequestBody
			if (requestAvailabilityParams.getContainerSize() != 0 || requestAvailabilityParams.getQuantity() != 0
					|| null != requestAvailabilityParams.getContainerType()
					|| null != requestAvailabilityParams.getOrigin()
					|| null != requestAvailabilityParams.getDestination()) {
				return new ResponseEntity<Object>(Constants.ERROR_MESSAGE_BAD_REQUEST, HttpStatus.BAD_REQUEST);
			}

			// Pass request body to fetch availability.
			List<ContainerBookingAvailability> containerAvailability = containerBookingService
					.getContainerAvailability(requestAvailabilityParams);

			return new ResponseEntity<>(containerAvailability, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(Constants.ERROR_MESSAGE_INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
