package com.balaji.xml.domain;

/**
 *
 *
 * @author Balaji Nagarajan
 * @version 1.0
 */
public class Product {

    private Long id;
    private Long upc;
    private String itemDesc;
    private Double unitPrice;
    private String category ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUpc() {
        return upc;
    }

    public void setUpc(Long upc) {
        this.upc = upc;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", upc=" + upc +
                ", itemDesc='" + itemDesc + '\'' +
                ", unitPrice=" + unitPrice +
                ", category='" + category + '\'' +
                '}';
    }
}
