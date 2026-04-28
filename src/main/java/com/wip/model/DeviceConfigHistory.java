package com.wip.model;

import jakarta.persistence.*;

@Entity
@Table(name = "device_config_history")
public class DeviceConfigHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long deviceId;

	@Column(columnDefinition = "TEXT")
	private String configXml;

	private String status;

	private String failureReason;

	private int version;

	private String createdAt;

	public DeviceConfigHistory() {
	}

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getConfigXml() {
		return configXml;
	}

	public void setConfigXml(String configXml) {
		this.configXml = configXml;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}