package com.wip.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wip.model.Device;
import com.wip.repository.DeviceRepository;

@Service
public class MonitoringSummaryService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Map<String, Object> getSummary() {

        List<Device> devices = deviceRepository.findAll();

        int total = devices.size();
        int up = 0;
        int down = 0;

        for (Device device : devices) {

            if ("UP".equalsIgnoreCase(device.getStatus())) {
                up++;
            } else if ("DOWN".equalsIgnoreCase(device.getStatus())) {
                down++;
            }
        }

        Map<String, Object> summary = new HashMap<>();

        summary.put("totalDevices", total);
        summary.put("upDevices", up);
        summary.put("downDevices", down);
        summary.put("lastUpdated", LocalDateTime.now().toString());

        return summary;
    }
}