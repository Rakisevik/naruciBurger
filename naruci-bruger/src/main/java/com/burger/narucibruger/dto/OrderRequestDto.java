package com.burger.narucibruger.dto;


import java.util.List;

public class OrderRequestDto {
    private String altAddress;
    private String altPhone;
    private List<BurgerDto> burgers;

    public OrderRequestDto() {
    }

    public OrderRequestDto(String altAddress, String altPhone, List<BurgerDto> burgers) {
        this.altAddress = altAddress;
        this.altPhone = altPhone;
        this.burgers = burgers;
    }

    public String getAltAddress() {
        return altAddress;
    }

    public void setAltAddress(String altAddress) {
        this.altAddress = altAddress;
    }

    public String getAltPhone() {
        return altPhone;
    }

    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }

    public List<BurgerDto> getBurgers() {
        return burgers;
    }

    public void setBurgers(List<BurgerDto> burgers) {
        this.burgers = burgers;
    }
}


