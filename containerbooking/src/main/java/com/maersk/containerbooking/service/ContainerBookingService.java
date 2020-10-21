package com.maersk.containerbooking.service;

import java.util.List;

import com.maersk.containerbooking.controller.model.ContainerBookingAvailability;
import com.maersk.containerbooking.controller.model.ContainerBookingAvailabilityParams;

public interface ContainerBookingService {
	
	public List<ContainerBookingAvailability> getContainerAvailability(ContainerBookingAvailabilityParams params);

}
