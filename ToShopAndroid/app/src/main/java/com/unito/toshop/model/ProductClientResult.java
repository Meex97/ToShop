package com.unito.toshop.model;

import java.io.Serializable;

public class ProductClientResult extends ProductInfoResult implements Serializable {
/*
    private int status;

    public ProductClientResult(ProductInfoResult productInfo){
        this.setStatus(0);
        this.setProductName(productInfo.getProductName());
        // this.setProductId(getProductId());
        this.setProductStatus(getProductStatus());
        this.setProductStock(getProductStock());
        this.setProductDescription(getProductDescription());
        this.setProductIcon(getProductIcon());
        //this.setProductimage(getProductimage());
        this.setProductPrice(getProductPrice());
        this.setIdUtente(productInfo.getIdUtente());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


 */
private int status;

    public ProductClientResult() {
    }

    public ProductClientResult(ProductInfoResult productInfo){
        this.setStatus(0);
        this.setProductName(productInfo.getProductName());
        // this.setProductId(getProductId());
        this.setProductStatus(getProductStatus());
        this.setProductStock(getProductStock());
        this.setProductDescription(getProductDescription());
        this.setProductIcon(getProductIcon());
        //this.setProductimage(getProductimage());
        this.setProductPrice(getProductPrice());
        this.setIdUtente(productInfo.getIdUtente());
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
