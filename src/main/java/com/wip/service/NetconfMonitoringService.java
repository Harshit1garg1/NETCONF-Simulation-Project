package com.wip.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wip.model.Device;
import com.wip.model.DeviceConfigHistory;
import com.wip.repository.DeviceConfigHistoryRepository;
import com.wip.repository.DeviceRepository;

@Service
public class NetconfMonitoringService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private NetconfConnectionService connectionService;

    @Autowired
    private NetconfXmlService xmlService;
    
    @Autowired
    private DeviceConfigHistoryRepository historyRepository;

    public List<Device> runMonitoring() {

        List<Device> devices = deviceRepository.findAll();

        for (Device device : devices) {

            boolean connected = connectionService.connect(device);

            String xml = null;
            String failureReason = null;

            if (connected) {

                xml = xmlService.generateXml(device);
                xmlService.parseAndUpdate(device, xml);

                device.setStatus("UP");

            } else {

                device.setStatus("DOWN");
                failureReason = "NETWORK_OR_AUTH_FAILURE";
            }

            device.setLastChecked(java.time.LocalDateTime.now().toString());

            deviceRepository.save(device);

            // ==============================
            // SNAPSHOT SAVE LOGIC
            // ==============================

            DeviceConfigHistory history = new DeviceConfigHistory();

            history.setDeviceId(device.getId());
            history.setConfigXml(xml);
            history.setStatus(device.getStatus());
            history.setFailureReason(failureReason);

            // version number
            int version = historyRepository.countByDeviceId(device.getId()) + 1;
            history.setVersion(version);

            history.setCreatedAt(java.time.LocalDateTime.now().toString());

            historyRepository.save(history);
        }

        return devices;
    }
}