package com.wip.service;

import org.springframework.stereotype.Service;
import com.wip.model.Device;

@Service
public class NetconfXmlService {

    // Generate simulated NETCONF XML response
    public String generateXml(Device device) {

        return "<rpc-reply>" +
                "<data>" +
                "<device>" +
                "<hostname>" + device.getHostname() + "</hostname>" +
                "<ip>" + device.getIp() + "</ip>" +
                "<os>" + device.getOs() + "</os>" +
                "<uptime>" + (int)(Math.random() * 10 + 1) + " days</uptime>" +
                "</device>" +
                "</data>" +
                "</rpc-reply>";
    }

    // Parse XML and update device fields
    public void parseAndUpdate(Device device, String xml) {

        try {
            String uptime = xml.split("<uptime>")[1].split("</uptime>")[0];
            device.setUptime(uptime);
        } catch (Exception e) {
            device.setUptime("Unknown");
        }
    }
}