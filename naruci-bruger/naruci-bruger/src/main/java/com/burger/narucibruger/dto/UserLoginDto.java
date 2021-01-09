package com.burger.narucibruger.dto;

public class UserLoginDto {

    private String status;

    public UserLoginDto(String status) {
        this.status = status;
    }

    public UserLoginDto() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
