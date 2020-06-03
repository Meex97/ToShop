package unito.progetto.esame.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class ProductInOrder implements  Comparable<ProductInOrder>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderMain orderMain;


    //private String productId;
    private String productId;

    /**
     * 名字.
     */
    @NotEmpty
    private String productName;

    /**
     * 描述.
     */
    @NotNull
    private String productDescription;

    /**
     * 小图.
     */
    private String productIcon;
    private byte[] productimage;

    /**
     * 类目编号.
     */
    @NotNull
    private Integer categoryType;

    /**
     * 单价.
     */
    @NotNull
    private BigDecimal productPrice;

    /**
     * 库存.
     */
    @Min(0)
    private Integer productStock;

    @Min(1)
    private Integer count;

    private String nameUtente;

    private Long idUtente;



    public ProductInOrder(ProductInfo productInfo, Integer quantity) {
        this.productId = productInfo.getProductId();
        this.productName = productInfo.getProductName();
        this.productDescription = productInfo.getProductDescription();
        this.productIcon = productInfo.getProductIcon();
        this.productimage = productInfo.getProductimage();
        this.categoryType = productInfo.getCategoryType();
        this.productPrice = productInfo.getProductPrice();
        this.productStock = productInfo.getProductStock();
        this.count = quantity;
        //Aggiunto
        this.nameUtente= productInfo.getNameUtente();
        this.idUtente = productInfo.getIdUtente();

    }

    /* @Override
     public String toString() {
         return "ProductInOrder{" +
                 "id=" + id +
                 ", productId='" + productId + '\'' +
                 ", productName='" + productName + '\'' +
                 ", productDescription='" + productDescription + '\'' +
                 ", productIcon='" + productIcon + '\'' +
                 ", categoryType=" + categoryType +
                 ", productPrice=" + productPrice +
                 ", productStock=" + productStock +
                 ", count=" + count +
                 '}';
     }*/
    public String toString() {
        return "ProductInOrder{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", idUtente='" + idUtente + '\'' +

                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductInOrder that = (ProductInOrder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(productDescription, that.productDescription) &&
                Objects.equals(productIcon, that.productIcon) &&
                // Objects.equals(productimage, that.productimage) &&
                Objects.equals(categoryType, that.categoryType) &&
                //Aggiunto
                Objects.equals(nameUtente, that.nameUtente) &&
                Objects.equals(productPrice, that.productPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, productId, productName, productDescription, productimage, categoryType, productPrice);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public OrderMain getOrderMain() {
        return orderMain;
    }

    public void setOrderMain(OrderMain orderMain) {
        this.orderMain = orderMain;
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

    public byte[] getProductimage() {
        return productimage;
    }

    public void setProductimage(byte[] productimage) {
        this.productimage = productimage;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

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

    @Override
    public int compareTo(ProductInOrder o) {
        return this.idUtente.intValue() - o.idUtente.intValue();
    }
}
