package com.wip.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wip.model.Device;
import com.wip.model.DeviceConfigHistory;
import com.wip.repository.DeviceRepository;
import com.wip.repository.DeviceConfigHistoryRepository;

@Service
public class SingleDeviceMonitoringService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private NetconfConnectionService connectionService;

    @Autowired
    private NetconfXmlService xmlService;

    @Autowired
    private DeviceConfigHistoryRepository historyRepository;

    public Device monitorDevice(Long id) {

        Device device = deviceRepository.findById(id).orElse(null);

        if (device == null) {
            return null;
        }

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

        device.setLastChecked(LocalDateTime.now().toString());

        deviceRepository.save(device);

        // Save snapshot
        DeviceConfigHistory history = new DeviceConfigHistory();

        history.setDeviceId(device.getId());
        history.setConfigXml(xml);
        history.setStatus(device.getStatus());
        history.setFailureReason(failureReason);

        int version = historyRepository.countByDeviceId(device.getId()) + 1;
        history.setVersion(version);

        history.setCreatedAt(LocalDateTime.now().toString());

        historyRepository.save(history);

        return device;
    }
}