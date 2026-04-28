package com.wip.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private boolean adminLoggedIn = false;

    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "1234";

    public boolean login(String username, String password) {

        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            adminLoggedIn = true;
            return true;
        }
        return false;
    }

    public void logout() {
        adminLoggedIn = false;
    }

    public boolean isAdminLoggedIn() {
        return adminLoggedIn;
    }
}
