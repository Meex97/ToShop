package com.unito.toshop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductListResult implements Serializable {

    @SerializedName("")
    List<ProductInfoResult> list;

    public List<ProductInfoResult> getList() {
        return list;
    }

    public void setList(List<ProductInfoResult> list) {
        this.list = list;
    }
}
