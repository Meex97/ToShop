package com.unito.toshop_back_v3.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequestSupplier extends SignupRequest {


    @NotBlank
    @Size(min = 11, max = 11)
    private String pIVA;

    @NotBlank
    @Size(min = 3, max = 20)
    private String address;

    @NotBlank
    @Size(min = 3, max = 20)
    private String shopName;

    public String getpIVA() {
        return pIVA;
    }

    public void setpIVA(String pIVA) {
        this.pIVA = pIVA;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
