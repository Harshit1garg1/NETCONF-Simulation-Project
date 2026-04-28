package com.wip.repository;

import com.wip.model.DeviceConfigHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceConfigHistoryRepository
        extends JpaRepository<DeviceConfigHistory, Long> {

    List<DeviceConfigHistory> findByDeviceId(Long deviceId);

    int countByDeviceId(Long deviceId);
}