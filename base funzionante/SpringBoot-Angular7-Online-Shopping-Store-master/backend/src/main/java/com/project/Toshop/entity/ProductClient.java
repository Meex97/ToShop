package com.project.Toshop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
public class ProductClient extends ProductInfo {

    private boolean approved;

    public ProductClient() {
    }

    public ProductClient(ProductInfo productInfo){
        this.setApproved(false);
        this.setProductName(productInfo.getProductName());
       // this.setProductId(getProductId());
        this.setProductStatus(getProductStatus());
        this.setProductStock(getProductStock());
        this.setProductDescription(getProductDescription());
        this.setProductIcon(getProductIcon());
        this.setProductPrice(getProductPrice());
        this.setIdUtente(productInfo.getIdUtente());
    }


    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
