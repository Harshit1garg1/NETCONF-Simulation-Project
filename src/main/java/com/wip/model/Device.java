package com.wip.model;

import jakarta.persistence.*;

@Entity
@Table(name = "devices")
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String hostname;
	private String ip;
	private String os;
	private String uptime;

	private String username;
	private String password;
    
	@Column(name = "device_type")
	private String deviceType;
	
	private String status; // UP / DOWN	
	
	@Column(name = "last_checked")
	private String lastChecked;

	public Device() {
	}

	
	 public Device(String hostname, String ip, String os, String uptime) {
	 this.hostname = hostname; 
	 this.ip = ip;
	 this.os = os; 
	 this.uptime = uptime;
	  
	 
	  }
	 

	// Full constructor
	public Device(Long id, String hostname, String ip, String os, String uptime, String username, String password,
			String deviceType, String status, String lastChecked) {
		this.id = id;
		this.hostname = hostname;
		this.ip = ip;
		this.os = os;
		this.uptime = uptime;
		this.username = username;
		this.password = password;
		this.deviceType = deviceType;
		this.status = status;
		this.lastChecked = lastChecked;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastChecked() {
		return lastChecked;
	}

	public void setLastChecked(String lastChecked) {
		this.lastChecked = lastChecked;
	}
}