package com.emazon.api_user.domain.model;

public class UserAuth {
    private String email;
    private String role;

    public UserAuth() {
    }

    public UserAuth(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}