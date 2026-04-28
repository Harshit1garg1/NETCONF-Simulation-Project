package com.wip.service;

import com.wip.model.Device;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;

import org.w3c.dom.Document;


import java.io.ByteArrayInputStream;

import java.util.List;

import com.wip.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class NetconfService {
	@Autowired
	private DeviceRepository deviceRepository;
	
	public Device addDevice(Device device) {
	    return deviceRepository.save(device);
	}
	
	public List<Device> getAllDevices() {
	    return deviceRepository.findAll();
	}
	
	public Device getDeviceById(Long id) {
	    return deviceRepository.findById(id).orElse(null);
	}
	
	public Device updateDevice(Long id, Device updatedDevice) {

	    Device device = deviceRepository.findById(id).orElse(null);

	    if (device != null) {
	        device.setHostname(updatedDevice.getHostname());
	        device.setIp(updatedDevice.getIp());
	        device.setOs(updatedDevice.getOs());
	        device.setUptime(updatedDevice.getUptime());
	        device.setUsername(updatedDevice.getUsername());
	        device.setPassword(updatedDevice.getPassword());
	        device.setDeviceType(updatedDevice.getDeviceType());
	        device.setStatus(updatedDevice.getStatus());
	        device.setLastChecked(updatedDevice.getLastChecked());

	        return deviceRepository.save(device);
	    }

	    return null;
	}
	
	public void deleteDevice(Long id) {
	    deviceRepository.deleteById(id);
	}
	
	public Device getDeviceData() {
		 try {
			String xmlResponse =
			         "<rpc-reply>" +
			         "<data>" +
			         "<device>" +
			         "<hostname>Router1</hostname>" +
			         "<ip>192.168.1.1</ip>" +
			         "<os>IOS-XE</os>" +
			         "<uptime>5 days</uptime>" +
			         "</device>" +
			         "</data>" +
			         "</rpc-reply>";

			 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			 DocumentBuilder builder = factory.newDocumentBuilder();

			 Document doc = builder.parse(
			         new ByteArrayInputStream(xmlResponse.getBytes()));
			 
			 doc.getDocumentElement().normalize();
			 
			 String hostname = doc.getElementsByTagName("hostname").item(0).getTextContent();
			 String ip = doc.getElementsByTagName("ip").item(0).getTextContent();
			 String os = doc.getElementsByTagName("os").item(0).getTextContent();
			 String uptime = doc.getElementsByTagName("uptime").item(0).getTextContent();

			 return new Device(hostname, ip, os, uptime);
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		 } 
         
	}

}
