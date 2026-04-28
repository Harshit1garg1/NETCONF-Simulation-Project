package com.wip.service;

import org.springframework.stereotype.Service;
import com.wip.model.Device;

@Service
public class NetconfConnectionService {
	
	private boolean authenticate(Device device) {

	    // expected credentials (simulate real device login)
	    String expectedUsername = "admin";
	    String expectedPassword = "admin123";

	    if (device.getUsername().equals(expectedUsername)
	            && device.getPassword().equals(expectedPassword)) {

	        return true;
	    }

	    return false;
	}

	public boolean connect(Device device) {

	    // Step 1: authentication check
	    boolean authSuccess = authenticate(device);

	    if (!authSuccess) {
	        System.out.println("Authentication failed for device: " + device.getHostname());
	        return false;
	    }

	    // Step 2: network connection simulation (80% success)
	    double random = Math.random();

	    if (random < 0.8) {
	        return true;
	    } else {
	        System.out.println("Network unreachable for device: " + device.getHostname());
	        return false;
	    }
	}
}