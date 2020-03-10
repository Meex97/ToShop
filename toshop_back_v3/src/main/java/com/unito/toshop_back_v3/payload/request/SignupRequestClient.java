package com.unito.toshop_back_v3.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequestClient extends SignupRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

