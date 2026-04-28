package com.wip.controller;

import com.wip.model.Device;
import com.wip.model.LoginRequest;
import com.wip.service.AuthService;
import com.wip.service.MonitoringSummaryService;
import com.wip.service.NetconfMonitoringService;
import com.wip.service.NetconfService;
import com.wip.service.SingleDeviceMonitoringService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NetconfController {

    @Autowired
    private NetconfService netconfService;

    @Autowired
    private NetconfMonitoringService monitoringService;

    @Autowired
    private AuthService authService;
   
    @Autowired
    private SingleDeviceMonitoringService singleDeviceMonitoringService;
   
    @Autowired
    private MonitoringSummaryService monitoringSummaryService;


    // ============================
    // AUTHENTICATION APIs
    // ============================

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        boolean success = authService.login(request.getUsername(), request.getPassword());

        if (success) {
            return "Login successful. Admin access granted.";
        } else {
            return "Invalid username or password.";
        }
    }

    @PostMapping("/logout")
    public String logout() {
        authService.logout();
        return "Logged out successfully.";
    }


    // ============================
    // DEVICE CRUD APIs
    // ============================

    // ADMIN ONLY
    @PostMapping("/devices")
    public Object addDevice(@RequestBody Device device) {

        if (!authService.isAdminLoggedIn()) {
            return "Access denied. Admin login required.";
        }

        return netconfService.addDevice(device);
    }


    // PUBLIC (anyone can view)
    @GetMapping("/devices")
    public List<Device> getAllDevices() {
        return netconfService.getAllDevices();
    }


    // PUBLIC
    @GetMapping("/devices/{id}")
    public Device getDeviceById(@PathVariable Long id) {
        return netconfService.getDeviceById(id);
    }


    // ADMIN ONLY
    @PutMapping("/devices/{id}")
    public Object updateDevice(@PathVariable Long id, @RequestBody Device device) {

        if (!authService.isAdminLoggedIn()) {
            return "Access denied. Admin login required.";
        }

        return netconfService.updateDevice(id, device);
    }


    // ADMIN ONLY
    @DeleteMapping("/devices/{id}")
    public Object deleteDevice(@PathVariable Long id) {

        if (!authService.isAdminLoggedIn()) {
            return "Access denied. Admin login required.";
        }

        netconfService.deleteDevice(id);
        return "Device deleted successfully";
    }


    // ============================
    // MONITORING API
    // ============================

    @GetMapping("/monitor/run")
    public Object runMonitoring() {

        if (!authService.isAdminLoggedIn()) {
            return "Access denied. Admin login required.";
        }
        return monitoringService.runMonitoring();
    }
   
    //single monitoring
    @GetMapping("/monitor/{id}")
    public Device monitorSingleDevice(@PathVariable Long id) {
        return singleDeviceMonitoringService.monitorDevice(id);
    }
   
    //summary dashboardS
    @GetMapping("/monitor/summary")
    public Object getMonitoringSummary() {
        return monitoringSummaryService.getSummary();
    }
}
