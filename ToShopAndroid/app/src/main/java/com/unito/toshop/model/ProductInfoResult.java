package com.unito.toshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductInfoResult implements Serializable, Comparable<ProductInfoResult> {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;


    private String productDescription;


    private String productIcon;

    private String productimage;


    // 0: on-sale 1: off-sale

    private Integer productStatus;



    private Integer categoryType;

    private Date createTime;
    private Date updateTime;

    private Long idUtente;

    private String nameUtente;

    // 1 nuovo, 2 Usato
    private Integer type;

    public String getNameUtente() {
        return nameUtente;
    }

    public void setNameUtente(String nameUtente) {
        this.nameUtente = nameUtente;
    }

    public Long getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
    }

    public ProductInfoResult() {
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public int compareTo(ProductInfoResult o) {
        return this.idUtente.intValue() - o.idUtente.intValue();
    }

}
